package mastergeneral156.ctdmythos.items.mythos;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MythosPickaxeItem extends MythosItem {
    private final float mythosCostPerBlock;

    public MythosPickaxeItem(float mythosCostPerBlock, float storage) {
        super(storage);
        this.mythosCostPerBlock = mythosCostPerBlock;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
        if (!world.isClientSide && state.getDestroySpeed(world, pos) > 0) {
            float currentMythos = getMythos(stack);
            if (currentMythos >= mythosCostPerBlock) {
                extractMythos(mythosCostPerBlock, stack);
                return true; // Allow mining
            } else {
                // Not enough Mythos – play fail sound or particles if needed
                return false;
            }
        }

        return super.mineBlock(stack, world, state, pos, entity);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        ItemStack stack = player.getMainHandItem();
        return getMythos(stack) >= mythosCostPerBlock; // Prevent swinging if no Mythos
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        // Let this tool behave like a pickaxe
        return state.is(BlockTags.MINEABLE_WITH_PICKAXE);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
