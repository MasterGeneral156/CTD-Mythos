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
        poseStack.translate(0.5D, 1.1D, 0.5D);

        // Rotate to face the player
        double dx = player.getX() - (pedestal.getBlockPos().getX() + 0.5);
        double dz = player.getZ() - (pedestal.getBlockPos().getZ() + 0.5);
        float yaw = (float) (Math.toDegrees(Math.atan2(dz, dx)) - 90);
        poseStack.mulPose(Axis.YP.rotationDegrees(-yaw));

        // Optional: Add a slow rotation around Y axis
        float rotation = (System.currentTimeMillis() / 30L) % 360;
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        // Scale down the item for a nice floaty effect
        poseStack.scale(0.6f, 0.6f, 0.6f);

        // Render the item
        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer, pedestal.getLevel(), 0);

        poseStack.popPose();
    }
}

