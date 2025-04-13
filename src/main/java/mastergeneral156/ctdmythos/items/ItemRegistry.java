package mastergeneral156.ctdmythos.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "ctdmythos");
	
	public static final RegistryObject<Item> crystal_fire = ITEMS.register("crystal_fire", () -> ItemConstants.crystal_fire);
	public static final RegistryObject<Item> crystal_grief = ITEMS.register("crystal_grief", () -> ItemConstants.crystal_grief);
	public static final RegistryObject<Item> crystal_oath = ITEMS.register("crystal_oath", () -> ItemConstants.crystal_oath);
	public static final RegistryObject<Item> crystal_memory = ITEMS.register("crystal_memory", () -> ItemConstants.crystal_memory);
	public static final RegistryObject<Item> crystal_woe = ITEMS.register("crystal_woe", () -> ItemConstants.crystal_woe);
	public static final RegistryObject<Item> crystal_mythos = ITEMS.register("crystal_mythos", () -> ItemConstants.crystal_mythos);

	//Non mythos tools, Tier 0
	public static final RegistryObject<Item> mythos_diviner = ITEMS.register("mythos_diviner", () -> ItemConstants.mythos_diviner);

	//Mythos Items, Tier 1
	public static final RegistryObject<Item> mythos_refractor = ITEMS.register("mythos_refractor", () -> ItemConstants.mythos_refractor);
	public static final RegistryObject<Item> mythos_conductor = ITEMS.register("mythos_conductor", () -> ItemConstants.mythos_conductor);
	public static final RegistryObject<Item> mythos_battery = ITEMS.register("mythos_battery", () -> ItemConstants.mythos_battery);

	//Crystal Socketable, Tier 2
	public static final RegistryObject<Item> solstice_legion_staff = ITEMS.register("solstice_legion_staff", () -> ItemConstants.solstice_legion_staff);
	public static final RegistryObject<Item> mythos_chakra = ITEMS.register("mythos_chakra", () -> ItemConstants.mythos_chakra);

	//Ore Blocks
	public static final RegistryObject<Item> ore_crystal_fire = ITEMS.register("ore_crystal_fire", () -> ItemConstants.ore_crystal_fire);
	public static final RegistryObject<Item> ore_crystal_woe = ITEMS.register("ore_crystal_woe", () -> ItemConstants.ore_crystal_woe);
	public static final RegistryObject<Item> ore_crystal_grief = ITEMS.register("ore_crystal_grief", () -> ItemConstants.ore_crystal_grief);
	public static final RegistryObject<Item> ore_crystal_memory = ITEMS.register("ore_crystal_memory", () -> ItemConstants.ore_crystal_memory);
	public static final RegistryObject<Item> ore_crystal_oath = ITEMS.register("ore_crystal_oath", () -> ItemConstants.ore_crystal_oath);

	public static final RegistryObject<Item> mythos_pylon = ITEMS.register("mythos_pylon", () -> ItemConstants.mythos_pylon);
	public static final RegistryObject<Item> mythos_weather_pylon = ITEMS.register("mythos_weather_pylon", () -> ItemConstants.mythos_weather_pylon);
	public static final RegistryObject<Item> mythos_altar = ITEMS.register("mythos_altar", () -> ItemConstants.mythos_altar);
	public static final RegistryObject<Item> mythos_pedestal = ITEMS.register("mythos_pedestal", () -> ItemConstants.mythos_pedestal);
}