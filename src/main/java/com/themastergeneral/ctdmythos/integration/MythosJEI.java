package com.themastergeneral.ctdmythos.integration;

import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.items.CowBloodItem;
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
		registry.addDescription(new ItemStack(ModItems.archeron_ingot),
				"jei.archeron_ingot.desc");
		registry.addDescription(new ItemStack(ModItems.flight_wand),
				"jei.flight_wand.desc");
		registry.addDescription(new ItemStack(ModItems.teleport_wand),
				"jei.teleport_wand.desc");
		registry.addDescription(new ItemStack(ModItems.crystal_ender),
				"jei.crystal_ender.desc");
		registry.addDescription(new ItemStack(ModItems.crystal_glove),
				"jei.crystal_glove.desc");
		registry.addDescription(new ItemStack(ModItems.muleskick),
				"jei.muleskick.desc");
		registry.addDescription(new ItemStack(ModItems.evasion_talisman),
				"jei.evasion_talisman.desc");
		registry.addDescription(new ItemStack(ModBlocks.crystal_fire_brick),
				"jei.crystal_bricks.desc");
	}
}
