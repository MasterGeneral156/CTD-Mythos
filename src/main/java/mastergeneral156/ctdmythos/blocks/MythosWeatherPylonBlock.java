package mastergeneral156.ctdmythos.blocks;

import com.themastergeneral.ctdcore.block.CTDBlock;
import mastergeneral156.ctdmythos.MythosReborn;
import mastergeneral156.ctdmythos.blocks.blockentity.MythosPylonBlockEntity;
import mastergeneral156.ctdmythos.blocks.blockentity.MythosWeatherPylonBlockEntity;
import mastergeneral156.ctdmythos.items.ItemConstants;
import mastergeneral156.ctdmythos.items.mythos.MythosItem;
import mastergeneral156.ctdmythos.items.mythos.MythosItemStorage;
import mastergeneral156.ctdmythos.utils.PlayerUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import java.util.Random;

public class MythosWeatherPylonBlock extends CTDBlock implements EntityBlock {

    public MythosWeatherPylonBlock() {
        super(Properties.of().destroyTime(100F).dynamicShape());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MythosWeatherPylonBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS; // Let server handle logic

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof MythosWeatherPylonBlockEntity pylon)) return InteractionResult.PASS;

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

        // If hand is not empty and pylon is not empty
        if (!handItem.isEmpty() && !slotItem.isEmpty()) {
            if (slotItem.getItem() instanceof MythosItemStorage storage)
            {
                //We're changing weather and time
                if (storage.getCurrentMythos(slotItem) >= 512F)
                {
                    //Crystal fire = Thunderstorm
                    if (handItem.getItem() == ItemConstants.crystal_fire) {
                        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                            serverLevel.setWeatherParameters(0, new Random().nextInt(600, 24000), true, true);
                            PlayerUtils.grantAdvancement((ServerPlayer) player, new ResourceLocation("ctdmythos", "weather_pylon"));
                        }
                        handItem.shrink(1);
                        storage.extractMythos(512F, slotItem);
                    }
                    //Crystal woe = rain
                    if (handItem.getItem() == ItemConstants.crystal_woe) {
                        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                            serverLevel.setWeatherParameters(0, new Random().nextInt(600, 24000), true, false);
                            PlayerUtils.grantAdvancement((ServerPlayer) player, new ResourceLocation("ctdmythos", "weather_pylon"));
                        }
                        handItem.shrink(1);
                        storage.extractMythos(512F, slotItem);
                    }

                    //Crystal memory = sun
                    if (handItem.getItem() == ItemConstants.crystal_memory) {
                        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                            serverLevel.setWeatherParameters(new Random().nextInt(600, 24000), 0, false, false);
                            PlayerUtils.grantAdvancement((ServerPlayer) player, new ResourceLocation("ctdmythos", "weather_pylon"));
                        }
                        handItem.shrink(1);
                        storage.extractMythos(512F, slotItem);
                    }

                    //Crystal oath = sun dawn
                    if (handItem.getItem() == ItemConstants.crystal_oath) {
                        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                            serverLevel.setDayTime(0L);
                            PlayerUtils.grantAdvancement((ServerPlayer) player, new ResourceLocation("ctdmythos", "weather_pylon"));
                        }
                        handItem.shrink(1);
                        storage.extractMythos(512F, slotItem);
                    }

                    //Crystal grief = moon rise
                    if (handItem.getItem() == ItemConstants.crystal_grief) {
                        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                            serverLevel.setDayTime(13000L);
                            PlayerUtils.grantAdvancement((ServerPlayer) player, new ResourceLocation("ctdmythos", "weather_pylon"));
                        }
                        handItem.shrink(1);
                        storage.extractMythos(512F, slotItem);
                    }
                }
            }
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
