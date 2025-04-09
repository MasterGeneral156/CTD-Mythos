package mastergeneral156.ctdmythos.blocks;

import com.themastergeneral.ctdcore.block.CTDBlock;
import mastergeneral156.ctdmythos.blocks.blockentity.MythosPylonBlockEntity;
import mastergeneral156.ctdmythos.items.mythos.MythosItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.Properties;

public class MythosPylonBlock extends CTDBlock implements EntityBlock {

    public MythosPylonBlock() {
        super(Properties.of().destroyTime(100F));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MythosPylonBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (level0, pos0, state0, blockEntity) -> ((MythosPylonBlockEntity) blockEntity).tick(level0, pos0, state0,  ((MythosPylonBlockEntity) blockEntity));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS; // Let server handle logic

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof MythosPylonBlockEntity pylon)) return InteractionResult.PASS;

        ItemStack handItem = player.getItemInHand(hand);
        ItemStack slotItem = pylon.getItemHandler().getStackInSlot(0);

        // If hand is empty and pylon has an item, give it to the player
        if (handItem.isEmpty() && !slotItem.isEmpty()) {
            player.setItemInHand(hand, slotItem.copy());
            pylon.getItemHandler().setStackInSlot(0, ItemStack.EMPTY);
            level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1.0F, 1.0F);
            return InteractionResult.CONSUME;
        }

        // If hand has MythosItem and pylon is empty, insert it
        if (!handItem.isEmpty() && handItem.getItem() instanceof MythosItem && slotItem.isEmpty()) {
            pylon.getItemHandler().setStackInSlot(0, handItem.copyWithCount(1));
            handItem.shrink(1);
            level.playSound(null, pos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        level.updateNeighbourForOutputSignal(pos, this);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MythosPylonBlockEntity charger) {
            ItemStack stack = charger.getItemHandler().getStackInSlot(0);
            if (!stack.isEmpty() && stack.getItem() instanceof MythosItem item) {
                float ratio = (float) item.getCurrentMythos() / (float) item.getMaxMythos();
                return Math.round(ratio * 15);
            }
        }
        return 0;
    }


}
