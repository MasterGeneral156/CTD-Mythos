package com.themastergeneral.ctdmythos.common.items;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class TMGDrill extends ItemSword implements ItemModelProvider {

	protected String name;	//Name of the item.
	public TMGDrill(String name) {
		super(ToolMaterial.IRON);
		this.maxStackSize = 1;
		this.setMaxDamage(375);
		this.name=name;
		this.setRegistryName(name);
		this.setCreativeTab(CTDMythos.creativeTab);
		this.setUnlocalizedName(name);
	}

	@Override
	public void registerItemModel(Item item) {
		CTDCore.proxy.registerItemRenderer(CTDMythos.MODID, this, 0, name);
	}

}
