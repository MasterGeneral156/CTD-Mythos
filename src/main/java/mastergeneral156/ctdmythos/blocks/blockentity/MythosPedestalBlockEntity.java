package mastergeneral156.ctdmythos.blocks.blockentity;

import mastergeneral156.ctdmythos.MythosReborn;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Optional;

public class MythosPedestalBlockEntity extends BlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1);
    private static final int SCAN_RADIUS = 8;
    private boolean active = false;

    public MythosPedestalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.mythos_pedestal.get(), pos, state);
        MythosReborn.LOGGER.info("Pedestal block entity created at " + pos);
    }

    public void dropContents() {
        Containers.dropContents(level, worldPosition, new SimpleContainer(itemHandler.getStackInSlot(0)));
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        setChanged(); // marks server-side change
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3); // tells client to re-render
        }
    }

    public ItemStack getItem() {
        return this.itemHandler.getStackInSlot(0); // or however you store items
    }

    public void setItem(ItemStack stack)
    {
        this.itemHandler.setStackInSlot(0, stack);
    }

    public InteractionResult onInteract(Player player, InteractionHand hand) {
        ItemStack held = player.getItemInHand(hand);
        if (!held.isEmpty()) {
            if (itemHandler.getStackInSlot(0).isEmpty()) {
                itemHandler.setStackInSlot(0, held.split(1));
                markUpdated();
                return InteractionResult.CONSUME;
            }
        } else {
            if (!itemHandler.getStackInSlot(0).isEmpty()) {
                player.setItemInHand(hand, itemHandler.extractItem(0, 1, false));
                markUpdated();
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    public ItemStack getCatalyst() {
        return itemHandler.getStackInSlot(0);
    }

    private void markUpdated() {
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
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
        tag.putBoolean("Active", active);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("ItemHandler")) {
            itemHandler.deserializeNBT(tag.getCompound("ItemHandler"));
        }
        if (tag.contains("Active")) {
            active = tag.getBoolean("Active");
        }
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }

    public Optional<MythosAltarBlockEntity> getLinkedAltar() {
        BlockPos center = this.getBlockPos();
        Level level = this.getLevel();
        if (level == null) return Optional.empty();

        return BlockPos.betweenClosedStream(center.offset(-SCAN_RADIUS, -3, -SCAN_RADIUS), center.offset(SCAN_RADIUS, 3, SCAN_RADIUS))
                .map(level::getBlockEntity)
                .filter(be -> be instanceof MythosAltarBlockEntity altar && altar.isRitualActive())
                .findFirst()
                .map(be -> (MythosAltarBlockEntity) be);
    }
}
