package mastergeneral156.ctdmythos.blocks;

import com.themastergeneral.ctdcore.block.CTDBlock;
import net.minecraft.world.level.block.Block;

public class BlockConstants {

	public static MythosOreBlock ore_crystal_fire = new MythosOreBlock();
	public static MythosOreBlock ore_crystal_grief = new MythosOreBlock();
	public static MythosOreBlock ore_crystal_oath = new MythosOreBlock();
	public static MythosOreBlock ore_crystal_memory = new MythosOreBlock();
	public static MythosOreBlock ore_crystal_woe = new MythosOreBlock();

	public static CTDBlock crystal_fire_brick = new CTDBlock(Block.Properties.of().requiresCorrectToolForDrops());
	public static CTDBlock crystal_grief_brick = new CTDBlock(Block.Properties.of().requiresCorrectToolForDrops());
	public static CTDBlock crystal_memory_brick = new CTDBlock(Block.Properties.of().requiresCorrectToolForDrops());
	public static CTDBlock crystal_oath_brick = new CTDBlock(Block.Properties.of().requiresCorrectToolForDrops());
	public static CTDBlock crystal_woe_brick = new CTDBlock(Block.Properties.of().requiresCorrectToolForDrops());

	public static MythosPylonBlock mythos_pylon = new MythosPylonBlock();
	public static MythosWeatherPylonBlock mythos_weather_pylon = new MythosWeatherPylonBlock();
	public static MythosAltarBlock mythos_altar = new MythosAltarBlock();
	public static MythosPedestalBlock mythos_pedestal = new MythosPedestalBlock();
}
