package com.themastergeneral.ctdmythos.integration;

import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.items.ModItems;

import net.minecraft.item.ItemStack;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class MythosJEI implements IModPlugin {
	@Override
	public void register(IModRegistry registry) {
		registry.addDescription(new ItemStack(ModItems.cowblooddrop),
				"jei.cowblooddrop.desc");
		registry.addDescription(new ItemStack(ModItems.humansoul),
				"jei.humansoul.desc");
		registry.addDescription(new ItemStack(ModItems.crystal_memory),
				"jei.crystalizedmemory.desc");
		registry.addDescription(new ItemStack(ModItems.xptome),
				"jei.xptome.desc");
		registry.addDescription(new ItemStack(ModItems.teleport_wand),
				"jei.teleport_wand.desc");
		registry.addDescription(new ItemStack(ModItems.crystal_glove),
				"jei.crystal_glove.desc");
		registry.addDescription(new ItemStack(ModItems.muleskick),
				"jei.muleskick.desc");
		registry.addDescription(new ItemStack(ModItems.evasion_talisman),
				"jei.evasion_talisman.desc");
		registry.addDescription(new ItemStack(ModBlocks.crystal_fire_brick),
				"jei.crystal_bricks.desc");
		registry.addDescription(new ItemStack(ModBlocks.crystal_oath_brick),
				"jei.crystal_bricks.desc");
		registry.addDescription(new ItemStack(ModBlocks.crystal_memory_brick),
				"jei.crystal_bricks.desc");
		registry.addDescription(new ItemStack(ModBlocks.crystal_woe_brick),
				"jei.crystal_bricks.desc");
		registry.addDescription(new ItemStack(ModBlocks.crystal_grief_brick),
				"jei.crystal_bricks.desc");
		registry.addDescription(new ItemStack(ModBlocks.pedestal_block),
				"jei.pedestal.desc");
		registry.addDescription(new ItemStack(ModItems.longbow),
				"jei.longbow.desc");
	}
}
