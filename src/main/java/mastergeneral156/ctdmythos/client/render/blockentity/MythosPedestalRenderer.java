package mastergeneral156.ctdmythos.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mastergeneral156.ctdmythos.blocks.blockentity.MythosPedestalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.atomic.AtomicReference;

public class MythosPedestalRenderer implements BlockEntityRenderer<MythosPedestalBlockEntity> {

    private final ItemRenderer itemRenderer;

    public MythosPedestalRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(MythosPedestalBlockEntity pedestal, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        ItemStack stack = pedestal.getItem();
        if (stack.isEmpty()) return;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        poseStack.pushPose();

        // Position the item above the pedestal
        poseStack.translate(0.5D, 0.9D, 0.5D);

        float rotation = 0f;

        if (!pedestal.isActive()) {
            // Face the player when idle
            double dx = player.getX() - (pedestal.getBlockPos().getX() + 0.5);
            double dz = player.getZ() - (pedestal.getBlockPos().getZ() + 0.5);
            float yaw = (float) (Math.toDegrees(Math.atan2(dz, dx)) - 90);
            poseStack.mulPose(Axis.YP.rotationDegrees(-yaw));
        } else {
            // Try to get the linked altar and use its ritual progress
            float baseSpeed = 1.0f;
            float maxSpeed = 5000.0f;

            AtomicReference<Float> progress = new AtomicReference<>(0f);

            pedestal.getLinkedAltar().ifPresent(altar -> {
                if (altar.isRitualActive()) {
                    int max = altar.ritualTime;
                    if (max > 0) {
                        float p = (float) altar.ritualProgress / max;
                        progress.set(Math.min(1f, p));
                    }
                }
            });

            float speedMultiplier = baseSpeed + (maxSpeed - baseSpeed) * progress.get();
            rotation = ((System.currentTimeMillis() / 20L) % 360) * speedMultiplier;

            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        }

        // Scale down the item for a nice floaty effect
        poseStack.scale(0.6f, 0.6f, 0.6f);

        // Render the item
        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer, pedestal.getLevel(), 0);

        poseStack.popPose();
    }

}

