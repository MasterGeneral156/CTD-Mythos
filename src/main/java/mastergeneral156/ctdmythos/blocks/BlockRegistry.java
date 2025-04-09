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
}
