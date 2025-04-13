package mastergeneral156.ctdmythos.blocks.blockentity;

import mastergeneral156.ctdmythos.MythosReborn;
import mastergeneral156.ctdmythos.blocks.MythosPedestalBlock;
import mastergeneral156.ctdmythos.items.mythos.MythosItem;
import mastergeneral156.ctdmythos.recipes.AltarRecipe;
import mastergeneral156.ctdmythos.recipes.RecipeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MythosAltarBlockEntity extends BlockEntity implements BlockEntityTicker<MythosAltarBlockEntity> {

    private static final int SCAN_RADIUS = 8;
    private static final int MAX_PEDESTALS = 9;
    private NonNullList<ItemStack> pedestalInputs = NonNullList.withSize(MAX_PEDESTALS, ItemStack.EMPTY);
    private ItemStack catalyst = ItemStack.EMPTY;
    private int ritualTime = 0;
    private int ritualProgress = 0;
    private AltarRecipe activeRecipe = null;
    private final ItemStackHandler itemHandler = new ItemStackHandler(1);
    private List<BlockPos> pedestalPositions = new ArrayList<>();

    public MythosAltarBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.mythos_altar.get(), pos, state);
    }

    public void dropContents() {
        Containers.dropContents(level, worldPosition, new SimpleContainer(itemHandler.getStackInSlot(0)));
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

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, MythosAltarBlockEntity be) {
        if (level == null || level.isClientSide) return;

        if (activeRecipe == null) {
            tryStartRitual();
        } else {
            continueRitual();
        }
    }

    private void tryStartRitual() {
        if (getItem().isEmpty()) return;

        List<BlockPos> pedestalPositions = BlockPos.betweenClosedStream(
                        worldPosition.offset(-SCAN_RADIUS, -3, -SCAN_RADIUS),
                        worldPosition.offset(SCAN_RADIUS, 3, SCAN_RADIUS))
                .filter(posD -> {
                    BlockState blockState = level.getBlockState(posD);
                    Block block = blockState.getBlock();
                    if (block instanceof MythosPedestalBlock) { // Check if it's the right block type
                        BlockEntity blockEntity = level.getBlockEntity(posD);
                        return blockEntity instanceof MythosPedestalBlockEntity;
                    }
                    return false;
                })
                .map(BlockPos::immutable)
                .limit(MAX_PEDESTALS)
                .toList();
        this.pedestalPositions = pedestalPositions;

        List<ItemStack> ingredients = pedestalPositions.stream()
                .filter(posD -> {
                    BlockEntity blockEntity = level.getBlockEntity(posD);
                    return blockEntity instanceof MythosPedestalBlockEntity;
                })
                .map(posD -> {
                    BlockEntity blockEntity = level.getBlockEntity(posD);
                    return ((MythosPedestalBlockEntity) blockEntity).getItem();
                })
                .filter(stack -> !stack.isEmpty())
                .toList();

        Optional<AltarRecipe> match = level.getRecipeManager()
                .getAllRecipesFor(RecipeRegistry.ALTAR_TYPE.get())
                .stream()
                .filter(recipe -> {
                    AltarRecipe aRecipe = (AltarRecipe) recipe;
                    return aRecipe.matches(getItem(), ingredients);
                })
                .findFirst();

        if (match.isPresent()) {
            AltarRecipe recipe = match.get();
            if (consumeMythosForRecipe(recipe)) {
                this.activeRecipe = recipe;
                this.ritualTime = recipe.getProcessingTime();
                this.ritualProgress = 0;

                this.pedestalInputs = NonNullList.create();
                this.pedestalInputs.addAll(ingredients);

                markUpdated();
            }
        }
    }

    public ItemStack getItem() {
        return this.itemHandler.getStackInSlot(0); // or however you store items
    }

    private void continueRitual() {
        if (activeRecipe == null) return;

        if (++ritualProgress >= ritualTime) {
            finishRitual();
        }

        if (level != null) {
            double x = worldPosition.getX() + 0.5;
            double y = worldPosition.getY() + 1.0;
            double z = worldPosition.getZ() + 0.5;
            level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.05, 0.0);
        }

        for (BlockPos pedestalPos : pedestalPositions) {
            double px = pedestalPos.getX() + 0.5;
            double py = pedestalPos.getY() + 1.0;
            double pz = pedestalPos.getZ() + 0.5;

            double ax = worldPosition.getX() + 0.5;
            double ay = worldPosition.getY() + 1.0;
            double az = worldPosition.getZ() + 0.5;

            // Vector from pedestal to altar
            double dx = (ax - px) / 10.0;
            double dy = (ay - py) / 10.0;
            double dz = (az - pz) / 10.0;

            // Create 10 particles along the path
            for (int i = 0; i < 10; i++) {
                level.addParticle(ParticleTypes.ENCHANT, px + dx * i, py + dy * i, pz + dz * i, 0.0, 0.0, 0.0);
            }
        }
    }

    private void finishRitual() {
        // Decrement catalyst stack in the altar
        ItemStack catalystStack = getItem();
        if (!catalystStack.isEmpty()) {
            catalystStack.shrink(1);
            itemHandler.setStackInSlot(0, catalystStack);
        }

        // Decrement pedestal input stacks
        for (BlockPos pedestalPos : pedestalPositions) {
            BlockEntity be = level.getBlockEntity(pedestalPos);
            if (be instanceof MythosPedestalBlockEntity pedestal) {
                ItemStack stack = pedestal.getItem();
                if (!stack.isEmpty()) {
                    stack.shrink(1);
                    pedestal.setChanged();
                }
            }
        }

        // Drop result item at the altar
        ItemStack result = activeRecipe.getResultItem().copy();
        Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY() + 1, worldPosition.getZ(), result);

        // Clear ritual state
        activeRecipe = null;
        ritualProgress = 0;
        ritualTime = 0;
        pedestalInputs.clear();
        markUpdated();
    }


    private boolean consumeMythosForRecipe(AltarRecipe recipe) {
        float cost = recipe.getMythosCost();
        float gathered = 0f;

        for (BlockPos pos : BlockPos.betweenClosed(worldPosition.offset(-SCAN_RADIUS, -3, -SCAN_RADIUS), worldPosition.offset(SCAN_RADIUS, 3, SCAN_RADIUS))) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof MythosPylonBlockEntity pylon) {
                ItemStack stack = pylon.getItemHandler().getStackInSlot(0);
                if (stack.getItem() instanceof MythosItem mythosItem) {
                    float available = mythosItem.getCurrentMythos(stack);
                    float toExtract = Math.min(cost - gathered, available);
                    mythosItem.extractMythos(toExtract, stack);
                    gathered += toExtract;
                    if (gathered >= cost) break;
                }
            }
        }

        return gathered >= cost;
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
