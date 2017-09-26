package com.themastergeneral.ctdmythos.common.items;

import com.themastergeneral.ctdcore.item.CTDItem;
import com.themastergeneral.ctdmythos.CTDMythos;

public class BaseItem extends CTDItem {
	public BaseItem(String name, String modid) {
		super(name, modid);
		this.setCreativeTab(CTDMythos.creativeTab);
	}
}
