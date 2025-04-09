package mastergeneral156.ctdmythos.items;

import com.themastergeneral.ctdcore.item.CTDItem;

import mastergeneral156.ctdmythos.blocks.BlockConstants;
import mastergeneral156.ctdmythos.items.mythos.MythosItemGeneratorSolar;
import mastergeneral156.ctdmythos.items.mythos.MythosItemGeneratorStormer;
import net.minecraft.world.item.Item.Properties;

public class ItemConstants {

	public static CTDItem crystal_woe = new CTDItem(new Properties());
	public static CTDItem crystal_oath = new CTDItem(new Properties());
	public static CTDItem crystal_grief = new CTDItem(new Properties());
	public static CTDItem crystal_memory = new CTDItem(new Properties());
	public static CTDItem crystal_fire = new CTDItem(new Properties());
	
	//Non mythos tools
	public static MythosDiviner mythos_diviner = new MythosDiviner();
	
	//Mythos Items
	public static MythosItemGeneratorSolar mythos_refractor = new MythosItemGeneratorSolar();
	public static MythosItemGeneratorStormer mythos_conductor = new MythosItemGeneratorStormer();
	
	//Ore Blocks
	public static MythosBlockItem ore_crystal_fire = new MythosBlockItem(BlockConstants.ore_crystal_fire);
	public static MythosBlockItem ore_crystal_woe = new MythosBlockItem(BlockConstants.ore_crystal_woe);
	public static MythosBlockItem ore_crystal_memory = new MythosBlockItem(BlockConstants.ore_crystal_memory);
	public static MythosBlockItem ore_crystal_grief = new MythosBlockItem(BlockConstants.ore_crystal_grief);
	public static MythosBlockItem ore_crystal_oath = new MythosBlockItem(BlockConstants.ore_crystal_oath);
	public static MythosBlockItem mythos_pylon = new MythosBlockItem(BlockConstants.mythos_pylon);
}
