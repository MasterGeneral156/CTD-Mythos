package com.themastergeneral.ctdmythos.integration;

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
		registry.addDescription(new ItemStack(ModItems.cowblooddrop), "jei.cowblooddrop.desc");
		registry.addDescription(new ItemStack(ModItems.humansoul), "jei.humansoul.desc");
	}
}
