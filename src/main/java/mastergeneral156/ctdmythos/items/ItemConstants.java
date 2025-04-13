package mastergeneral156.ctdmythos.items;

import com.themastergeneral.ctdcore.item.CTDItem;

import mastergeneral156.ctdmythos.blocks.BlockConstants;
import mastergeneral156.ctdmythos.items.mythos.MythosItemGeneratorSolar;
import mastergeneral156.ctdmythos.items.mythos.MythosItemGeneratorStormer;
import mastergeneral156.ctdmythos.items.mythos.MythosItemStorage;
import mastergeneral156.ctdmythos.items.mythos.socketable.SolsticeLegionStaffItem;
import net.minecraft.world.item.Item.Properties;

public class ItemConstants {

	public static CTDItem crystal_woe = new CTDItem(new Properties());
	public static CTDItem crystal_oath = new CTDItem(new Properties());
	public static CTDItem crystal_grief = new CTDItem(new Properties());
	public static CTDItem crystal_memory = new CTDItem(new Properties());
	public static CTDItem crystal_fire = new CTDItem(new Properties());
	public static CTDItem crystal_mythos = new CTDItem(new Properties());
	
	//Non mythos tools
	public static MythosDiviner mythos_diviner = new MythosDiviner();
	
	//Mythos Items
	public static MythosItemStorage mythos_battery = new MythosItemStorage(1024, 8F);
	public static MythosItemStorage mythos_chakra = new MythosItemStorage(1024*2.25F, 8*4.5F);
	public static MythosItemGeneratorSolar mythos_refractor = new MythosItemGeneratorSolar(1024, 0.25F);
	public static MythosItemGeneratorStormer mythos_conductor = new MythosItemGeneratorStormer(1024, 1.25F);

	//Unique items
	public static SolsticeLegionStaffItem solstice_legion_staff = new SolsticeLegionStaffItem(512F);
	
	//Ore Blocks
	public static MythosBlockItem ore_crystal_fire = new MythosBlockItem(BlockConstants.ore_crystal_fire);
	public static MythosBlockItem ore_crystal_woe = new MythosBlockItem(BlockConstants.ore_crystal_woe);
	public static MythosBlockItem ore_crystal_memory = new MythosBlockItem(BlockConstants.ore_crystal_memory);
	public static MythosBlockItem ore_crystal_grief = new MythosBlockItem(BlockConstants.ore_crystal_grief);
	public static MythosBlockItem ore_crystal_oath = new MythosBlockItem(BlockConstants.ore_crystal_oath);

	public static MythosBlockItem crystal_fire_brick = new MythosBlockItem(BlockConstants.crystal_fire_brick);
	public static MythosBlockItem crystal_woe_brick = new MythosBlockItem(BlockConstants.crystal_woe_brick);
	public static MythosBlockItem crystal_memory_brick = new MythosBlockItem(BlockConstants.crystal_memory_brick);
	public static MythosBlockItem crystal_grief_brick = new MythosBlockItem(BlockConstants.crystal_grief_brick);
	public static MythosBlockItem crystal_oath_brick = new MythosBlockItem(BlockConstants.crystal_oath_brick);

	//Mythos blocks
	public static MythosBlockItem mythos_pylon = new MythosBlockItem(BlockConstants.mythos_pylon);
	public static MythosBlockItem mythos_weather_pylon = new MythosBlockItem(BlockConstants.mythos_weather_pylon);
	public static MythosBlockItem mythos_altar = new MythosBlockItem(BlockConstants.mythos_altar);
	public static MythosBlockItem mythos_pedestal = new MythosBlockItem(BlockConstants.mythos_pedestal);
}
