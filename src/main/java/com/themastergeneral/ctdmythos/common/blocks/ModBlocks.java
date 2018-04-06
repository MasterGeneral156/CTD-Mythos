package com.themastergeneral.ctdmythos.common.blocks;

import net.minecraft.block.material.Material;

import com.themastergeneral.ctdcore.block.RegisterBlock;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.blocks.bricks.BrickBlocks;
import com.themastergeneral.ctdmythos.common.blocks.bricks.BricksStairs;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class ModBlocks extends RegisterBlock {
	public static OreBlock crystal_fire_ore;
	public static OreBlock crystal_grief_ore;
	public static OreBlock crystal_memory_ore;
	public static OreBlock crystal_oath_ore;
	public static OreBlock crystal_woe_ore;

	public static BrickBlocks crystal_fire_brick;
	public static BricksStairs crystal_fire_stairs;
	public static BrickBlocks crystal_oath_brick;
	public static BricksStairs crystal_oath_stairs;
	public static BrickBlocks crystal_woe_brick;
	public static BricksStairs crystal_woe_stairs;
	public static BrickBlocks crystal_memory_brick;
	public static BricksStairs crystal_memory_stairs;
	public static BrickBlocks crystal_grief_brick;
	public static BricksStairs crystal_grief_stairs;

	public static CrystalBlocks crystal_woe_block;
	public static CrystalBlocks crystal_fire_block;
	public static CrystalBlocks crystal_memory_block;
	public static CrystalBlocks crystal_oath_block;
	public static CrystalBlocks crystal_grief_block;
	
	public static PedestalBlock pedestal_block;

	// Register the blocks by calling this method.
	public static void loadBlocks() {
		crystal_fire_ore = register(new OreBlock(Material.SAND,
				"crystal_fire_ore", ModItems.crystal_fire));
		crystal_grief_ore = register(new OreBlock(Material.SAND,
				"crystal_grief_ore", ModItems.crystal_grief));
		crystal_memory_ore = register(new OreBlock(Material.SAND,
				"crystal_memory_ore", ModItems.crystal_memory));
		crystal_oath_ore = register(new OreBlock(Material.SAND,
				"crystal_oath_ore", ModItems.crystal_oath));
		crystal_woe_ore = register(new OreBlock(Material.SAND,
				"crystal_woe_ore", ModItems.crystal_woe));

		crystal_fire_brick = register(new BrickBlocks("crystal_fire_brick"));
		crystal_fire_stairs = register(new BricksStairs("crystal_fire_stairs",
				crystal_fire_brick.getDefaultState()));
		crystal_oath_brick = register(new BrickBlocks("crystal_oath_brick"));
		crystal_oath_stairs = register(new BricksStairs("crystal_oath_stairs",
				crystal_oath_brick.getDefaultState()));
		crystal_woe_brick = register(new BrickBlocks("crystal_woe_brick"));
		crystal_woe_stairs = register(new BricksStairs("crystal_woe_stairs",
				crystal_woe_brick.getDefaultState()));
		crystal_memory_brick = register(new BrickBlocks("crystal_memory_brick"));
		crystal_memory_stairs = register(new BricksStairs("crystal_memory_stairs",
				crystal_memory_brick.getDefaultState()));
		crystal_grief_brick = register(new BrickBlocks("crystal_grief_brick"));
		crystal_grief_stairs = register(new BricksStairs("crystal_grief_stairs",
				crystal_grief_brick.getDefaultState()));

		crystal_woe_block = register(new CrystalBlocks("crystal_woe_block"));
		crystal_fire_block = register(new CrystalBlocks("crystal_fire_block"));
		crystal_memory_block = register(new CrystalBlocks("crystal_memory_block"));
		crystal_oath_block = register(new CrystalBlocks("crystal_oath_block"));
		crystal_grief_block = register(new CrystalBlocks("crystal_grief_block"));
		
		pedestal_block = register(new PedestalBlock("pedestal_block"));
	}
}
