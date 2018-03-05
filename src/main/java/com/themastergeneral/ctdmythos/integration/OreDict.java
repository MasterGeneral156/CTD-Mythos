package com.themastergeneral.ctdmythos.integration;

import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.items.ModItems;

import net.minecraftforge.oredict.OreDictionary;

public class OreDict {
	public static final void init() {
		OreDictionary.registerOre("gemCrystallized", ModItems.crystal_ender);
		OreDictionary.registerOre("gemCrystallized", ModItems.crystal_woe);
		OreDictionary.registerOre("gemCrystallized", ModItems.crystal_fire);
		OreDictionary.registerOre("gemCrystallized", ModItems.crystal_grief);
		OreDictionary.registerOre("gemCrystallized", ModItems.crystal_memory);
		OreDictionary.registerOre("gemCrystallized", ModItems.crystal_oath);

		OreDictionary.registerOre("gemCrystallizedEnder",
				ModItems.crystal_ender);
		OreDictionary.registerOre("gemCrystallizedWoe", ModItems.crystal_woe);
		OreDictionary.registerOre("gemCrystallizedFire", ModItems.crystal_fire);
		OreDictionary.registerOre("gemCrystallizedGrief",
				ModItems.crystal_grief);
		OreDictionary.registerOre("gemCrystallizedMemory",
				ModItems.crystal_memory);
		OreDictionary.registerOre("gemCrystallizedOath", ModItems.crystal_oath);

		OreDictionary.registerOre("ingotArcheron", ModItems.archeron_ingot);

		OreDictionary.registerOre("oreCrystallizedFire",
				ModBlocks.crystal_fire_ore);
		OreDictionary.registerOre("oreCrystallizedWoe",
				ModBlocks.crystal_woe_ore);
		OreDictionary.registerOre("oreCrystallizedGrief",
				ModBlocks.crystal_grief_ore);
		OreDictionary.registerOre("oreCrystallizedMemory",
				ModBlocks.crystal_memory_ore);
		OreDictionary.registerOre("oreCrystallizedOath",
				ModBlocks.crystal_oath_ore);

		OreDictionary.registerOre("fireCrystallizedBrick",
				ModBlocks.crystal_fire_brick);
		OreDictionary.registerOre("crystallizedBrick",
				ModBlocks.crystal_fire_brick);

		OreDictionary.registerOre("stairsCrystallizedBrick",
				ModBlocks.crystal_fire_stairs);
		OreDictionary.registerOre("crystallizedBrick",
				ModBlocks.crystal_fire_stairs);

	}
}
