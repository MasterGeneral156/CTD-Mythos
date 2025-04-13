package mastergeneral156.ctdmythos.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mastergeneral156.ctdmythos.MythosReborn;
import mastergeneral156.ctdmythos.blocks.blockentity.MythosAltarBlockEntity;
import mastergeneral156.ctdmythos.blocks.blockentity.MythosPedestalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class MythosAltarRenderer implements BlockEntityRenderer<MythosAltarBlockEntity> {

    private final ItemRenderer itemRenderer;

    public MythosAltarRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(MythosAltarBlockEntity altar, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        ItemStack stack = altar.getItem();
        if (stack.isEmpty()) return;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;

        poseStack.pushPose();

        // Position the item above the altar
        poseStack.translate(0.5D, 1.1D, 0.5D);

        // Rotation logic
        float rotation;

        if (!altar.isRitualActive()) {
            // Face the player when idle
            double dx = player.getX() - (altar.getBlockPos().getX() + 0.5);
            double dz = player.getZ() - (altar.getBlockPos().getZ() + 0.5);
            float yaw = (float) (Math.toDegrees(Math.atan2(dz, dx)) - 90);
            poseStack.mulPose(Axis.YP.rotationDegrees(-yaw));
        } else {
            // Spin faster as the ritual progresses
            float baseSpeed = 1.0f; // Base multiplier (you can tune this)
            float maxSpeed = 5000.0f;  // Max speed multiplier at 100% progress

            float progress = altar.ritualTime > 0 ? (float) altar.ritualProgress / altar.ritualTime : 0f;
            float speedMultiplier = baseSpeed + (float)Math.pow(progress, 2) * (maxSpeed - baseSpeed);

            rotation = ((System.currentTimeMillis() / 20L) % 360) * speedMultiplier;
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        }

        // Scale down the item for a nice floaty effect
        poseStack.scale(0.6f, 0.6f, 0.6f);

        // Render the item
        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer, altar.getLevel(), 0);

        poseStack.popPose();
    }

}

