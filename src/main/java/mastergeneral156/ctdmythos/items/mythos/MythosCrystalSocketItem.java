package mastergeneral156.ctdmythos.items.mythos;

import mastergeneral156.ctdmythos.items.ItemConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public abstract class MythosCrystalSocketItem extends MythosItem {
    private static final String NBT_CRYSTAL = "SocketedCrystal";

    public MythosCrystalSocketItem(float maxSize) {
        super(maxSize);
    }

    /*
     * TODO: Make use tags.
     */
    public static boolean isCrystal(ItemStack stack) {
        Item item = stack.getItem();
        if (item == ItemConstants.crystal_fire)
            return true;
        else if (item == ItemConstants.crystal_memory)
            return true;
        else if (item == ItemConstants.crystal_oath)
            return true;
        else if (item == ItemConstants.crystal_grief)
            return true;
        else return item == ItemConstants.crystal_woe;
    }

    public static @Nullable Item getSocketedCrystal(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains(NBT_CRYSTAL)) {
            return ForgeRegistries.ITEMS.getValue(new ResourceLocation(tag.getString(NBT_CRYSTAL)));
        }
        return null;
    }

    public static void setSocketedCrystal(ItemStack stack, Item crystalItem) {
        stack.getOrCreateTag().putString(NBT_CRYSTAL, ForgeRegistries.ITEMS.getKey(crystalItem).toString());
    }

    public static void ejectCrystal(ItemStack stack, Level level, Player player) {
        Item crystal = getSocketedCrystal(stack);
        if (crystal != null && !player.getInventory().add(new ItemStack(crystal))) {
            player.drop(new ItemStack(crystal), false);
        }
        stack.removeTagKey(NBT_CRYSTAL);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ItemStack offhand = player.getOffhandItem();

        // If player is sneaking, eject
        if (player.isShiftKeyDown()) {
            ejectCrystal(stack, level, player);
            return InteractionResultHolder.success(stack);
        }

        // If offhand holds a crystal and no socketed crystal, insert it
        if (isCrystal(offhand) && getSocketedCrystal(stack) == null) {
            setSocketedCrystal(stack, offhand.getItem());
            offhand.shrink(1);
            return InteractionResultHolder.success(stack);
        }

        return super.use(level, player, hand);
    }
}
