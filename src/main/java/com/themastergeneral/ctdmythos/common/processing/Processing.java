package com.themastergeneral.ctdmythos.common.processing;

import net.minecraft.item.ItemStack;

import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class Processing {
	public static void initProcessing() {
		Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_fire_ore),
				new ItemStack(ModItems.crystal_fire), 0F);
		Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_woe_ore),
				new ItemStack(ModItems.crystal_woe), 0F);
		Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_memory_ore),
				new ItemStack(ModItems.crystal_memory), 0F);
		Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_grief_ore),
				new ItemStack(ModItems.crystal_grief), 0F);
		Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_oath_ore),
				new ItemStack(ModItems.crystal_oath), 0F);
	}

}
