package mastergeneral156.ctdmythos.items.mythos;

import com.themastergeneral.ctdcore.helpers.ModUtils;
import mastergeneral156.chasethedragon.radial.RadialClientEvents;
import mastergeneral156.chasethedragon.radial.RadialMenuOption;
import mastergeneral156.chasethedragon.radial.api.CTDRadialAPI;
import mastergeneral156.ctdmythos.MythosNetworkManager;
import mastergeneral156.ctdmythos.items.ItemConstants;
import mastergeneral156.ctdmythos.packets.EjectCrystalPacket;
import mastergeneral156.ctdmythos.packets.SocketCrystalPacket;
import mastergeneral156.ctdmythos.utils.ItemUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MythosCrystalSocketItem extends MythosItem {
    private static final String NBT_CRYSTAL = "SocketedCrystal";
    public static final TagKey<Item> SOCKETABLE_CRYSTALS = TagKey.create(ForgeRegistries.ITEMS.getRegistryKey(), new ResourceLocation("ctdmythos", "socketable_crystals"));

    public MythosCrystalSocketItem(float maxSize) {
        super(maxSize);
    }

    /*
     * TODO: Make use tags.
     */
    public static boolean isCrystal(ItemStack stack) {
        return stack.is(SOCKETABLE_CRYSTALS);
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
            return InteractionResultHolder.success(stack);
        }

        return super.use(level, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, level, entity, itemSlot, isSelected);
        if (!level.isClientSide || !(entity instanceof Player player)) return;

        List<RadialMenuOption> optionList = new ArrayList<>();

        if (getSocketedCrystal(stack) == null) {

            Set<Item> added = new HashSet<>(); // Prevent duplicates

            for (ItemStack invStack : player.getInventory().items) {
                if (!invStack.isEmpty() && invStack.is(SOCKETABLE_CRYSTALS)) {
                    Item item = invStack.getItem();
                    if (added.contains(item)) continue;
                    added.add(item);
                    ResourceLocation icon = ItemUtils.getIconByStack(invStack);

                    optionList.add(new RadialMenuOption(
                            () -> MythosNetworkManager.INSTANCE.sendToServer(
                                    new SocketCrystalPacket(itemSlot, ForgeRegistries.ITEMS.getKey(item).toString())
                            ),
                            icon,
                            Component.literal("Socket ").append(invStack.getHoverName())
                    ));

                }
            }
        }
        else
        {
            optionList.add(new RadialMenuOption(
                    () -> MythosNetworkManager.INSTANCE.sendToServer(new EjectCrystalPacket(itemSlot)),
                    ItemUtils.getIconByStack(new ItemStack(Items.BARRIER)), // You should add this texture
                    ModUtils.displayTranslation("radial.ctdmythos.eject_crystal")
            ));
        }

        if (!optionList.isEmpty())
            handleClientRadialMenu(optionList);
    }


    @OnlyIn(Dist.CLIENT)
    private void handleClientRadialMenu(List<RadialMenuOption> optionList) {
        if (RadialClientEvents.openRadial.isDown()) {
            CTDRadialAPI.openRadialMenu(optionList);
        }
    }

}
