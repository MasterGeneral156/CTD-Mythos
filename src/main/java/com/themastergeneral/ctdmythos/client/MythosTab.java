package com.themastergeneral.ctdmythos.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.ModItems;

public class MythosTab extends CreativeTabs {

	public MythosTab() {
		super(CTDMythos.MODID);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.humaneffigy); // shown icon on creative
													// tab
	}

	@Override
	public boolean hasSearchBar() {
		return false;
	}

}
