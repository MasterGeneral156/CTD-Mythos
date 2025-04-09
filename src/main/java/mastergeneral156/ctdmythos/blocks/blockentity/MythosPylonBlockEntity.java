package mastergeneral156.ctdmythos.blocks.blockentity;

import mastergeneral156.ctdmythos.items.mythos.MythosItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class MythosPylonBlockEntity extends BlockEntity implements BlockEntityTicker<MythosPylonBlockEntity> {

    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public MythosPylonBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.mythos_pylon.get(), pos, state);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, MythosPylonBlockEntity be) {
        if (level.isClientSide) {
            // Particle effect when item is charging
            ItemStack stack = be.itemHandler.getStackInSlot(0);
            if (!stack.isEmpty() && stack.getItem() instanceof MythosItem item) {
                if (item.getCurrentMythos() < item.getMaxMythos()) {
                    double x = pos.getX() + 0.5;
                    double y = pos.getY() + 1.0;
                    double z = pos.getZ() + 0.5;
                    level.addParticle(ParticleTypes.ENCHANT, x, y, z, 0.0, 0.1, 0.0);
                }
            }
            return;
        }

        // Server logic
        ItemStack stack = be.itemHandler.getStackInSlot(0);
        if (!stack.isEmpty() && stack.getItem() instanceof MythosItem mythosItem) {
            mythosItem.inventoryTick(stack, level, null, 0, false);
        }
        level.updateNeighborsAt(pos, be.getBlockState().getBlock());
    }


}
