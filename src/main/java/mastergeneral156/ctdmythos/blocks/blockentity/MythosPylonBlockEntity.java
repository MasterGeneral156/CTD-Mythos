package mastergeneral156.ctdmythos.blocks.blockentity;

import mastergeneral156.ctdmythos.items.mythos.MythosItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

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
                    level.addParticle(ParticleTypes.ENCHANT, x, y, z, 0.0, 0.5, 0.0);
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

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.put("ItemHandler", itemHandler.serializeNBT());
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        if (tag.contains("ItemHandler")) {
            itemHandler.deserializeNBT(tag.getCompound("ItemHandler"));
        }
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }

}
