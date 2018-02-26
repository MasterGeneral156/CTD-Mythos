package com.themastergeneral.ctdmythos.common.items.base;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class BaseFood extends ItemFood implements ItemModelProvider {

	protected String name; // Name of the item.

	public BaseFood(String name, int amount, float saturation,
			boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		this.name = name;
		this.setRegistryName(name);
		this.setCreativeTab(CTDMythos.creativeTab);
		this.setUnlocalizedName(name);
	}

	@Override
	public void registerItemModel(Item item) {
		CTDCore.proxy.registerItemRenderer(CTDMythos.MODID, this, 0, name);
	}

}
