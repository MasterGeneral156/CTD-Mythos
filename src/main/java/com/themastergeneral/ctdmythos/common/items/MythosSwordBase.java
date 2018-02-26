package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;

public class MythosSwordBase extends ItemSword implements ItemModelProvider {
	protected String name;	//Name of the item.

	public MythosSwordBase(ToolMaterial material, String name, int damage) {
		super(material);
		this.name=name;
		this.setRegistryName(name);
		this.setCreativeTab(CTDMythos.creativeTab);
		this.setUnlocalizedName(name);
		this.setMaxDamage(damage);
	}
	
	@Override
	public void registerItemModel(Item item) {
		CTDCore.proxy.registerItemRenderer(CTDMythos.MODID, this, 0, name);
	}
}
