package mastergeneral156.ctdmythos.blocks;

import com.themastergeneral.ctdcore.block.CTDBlock;
import mastergeneral156.ctdmythos.blocks.blockentity.MythosAltarBlockEntity;
import mastergeneral156.ctdmythos.blocks.blockentity.MythosPedestalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MythosPedestalBlock extends CTDBlock implements EntityBlock {

    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 10, 14);

    public MythosPedestalBlock() {
        super(Properties.of().destroyTime(100F).dynamicShape());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MythosPedestalBlockEntity(pos, state);
    }

    @Override
    public void onRemove(BlockState oldState, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof MythosPedestalBlockEntity altar) {
                altar.dropContents();
            }
        }
        super.onRemove(oldState, level, pos, newState, isMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof MythosPedestalBlockEntity altar) {
                return altar.onInteract(player, hand);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
