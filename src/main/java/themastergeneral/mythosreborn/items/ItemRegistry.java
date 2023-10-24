package themastergeneral.mythosreborn.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "mythosreborn");
	
	public static final RegistryObject<Item> crystal_fire = ITEMS.register("crystal_fire", () -> ItemConstants.crystal_fire);
	public static final RegistryObject<Item> crystal_grief = ITEMS.register("crystal_grief", () -> ItemConstants.crystal_grief);
	public static final RegistryObject<Item> crystal_oath = ITEMS.register("crystal_oath", () -> ItemConstants.crystal_oath);
	public static final RegistryObject<Item> crystal_memory = ITEMS.register("crystal_memory", () -> ItemConstants.crystal_memory);
	public static final RegistryObject<Item> crystal_woe = ITEMS.register("crystal_woe", () -> ItemConstants.crystal_woe);
}