package com.themastergeneral.ctdmythos.common.blocks;

import net.minecraft.block.material.Material;

import com.themastergeneral.ctdcore.block.RegisterBlock;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class ModBlocks extends RegisterBlock {
	public static OreBlock crystal_fire_ore;
	public static OreBlock crystal_grief_ore;
	public static OreBlock crystal_memory_ore;
	public static OreBlock crystal_oath_ore;
	public static OreBlock crystal_woe_ore;
	
	public static void loadBlocks()
	{
		crystal_fire_ore = register(new OreBlock(Material.SAND,
				"crystal_fire_ore", CTDMythos.MODID, ModItems.crystal_fire));
		crystal_grief_ore = register(new OreBlock(Material.SAND,
				"crystal_grief_ore", CTDMythos.MODID, ModItems.crystal_grief));
		crystal_memory_ore = register(new OreBlock(Material.SAND,
				"crystal_memory_ore", CTDMythos.MODID, ModItems.crystal_memory));
		crystal_oath_ore = register(new OreBlock(Material.SAND,
				"crystal_oath_ore", CTDMythos.MODID, ModItems.crystal_oath));
		crystal_woe_ore = register(new OreBlock(Material.SAND,
				"crystal_woe_ore", CTDMythos.MODID, ModItems.crystal_woe));
	}
}