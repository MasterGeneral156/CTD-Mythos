package mastergeneral156.ctdmythos.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "ctdmythos");

	public static final RegistryObject<Block> ore_crystal_fire = BLOCKS.register("ore_crystal_fire", () -> BlockConstants.ore_crystal_fire);
	public static final RegistryObject<Block> ore_crystal_woe = BLOCKS.register("ore_crystal_woe", () -> BlockConstants.ore_crystal_woe);
	public static final RegistryObject<Block> ore_crystal_oath = BLOCKS.register("ore_crystal_oath", () -> BlockConstants.ore_crystal_oath);
	public static final RegistryObject<Block> ore_crystal_memory = BLOCKS.register("ore_crystal_memory", () -> BlockConstants.ore_crystal_memory);
	public static final RegistryObject<Block> ore_crystal_grief = BLOCKS.register("ore_crystal_grief", () -> BlockConstants.ore_crystal_grief);

	public static final RegistryObject<Block> mythos_pylon = BLOCKS.register("mythos_pylon", () -> BlockConstants.mythos_pylon);
	public static final RegistryObject<Block> mythos_weather_pylon = BLOCKS.register("mythos_weather_pylon", () -> BlockConstants.mythos_weather_pylon);
	public static final RegistryObject<Block> mythos_altar = BLOCKS.register("mythos_altar", () -> BlockConstants.mythos_altar);
	public static final RegistryObject<Block> mythos_pedestal = BLOCKS.register("mythos_pedestal", () -> BlockConstants.mythos_pedestal);

	public static final RegistryObject<Block> crystal_fire_brick = BLOCKS.register("crystal_fire_brick", () -> BlockConstants.crystal_fire_brick);
	public static final RegistryObject<Block> crystal_grief_brick = BLOCKS.register("crystal_grief_brick", () -> BlockConstants.crystal_grief_brick);
	public static final RegistryObject<Block> crystal_memory_brick = BLOCKS.register("crystal_memory_brick", () -> BlockConstants.crystal_memory_brick);
	public static final RegistryObject<Block> crystal_oath_brick = BLOCKS.register("crystal_oath_brick", () -> BlockConstants.crystal_oath_brick);
	public static final RegistryObject<Block> crystal_woe_brick = BLOCKS.register("crystal_woe_brick", () -> BlockConstants.crystal_woe_brick);
}
