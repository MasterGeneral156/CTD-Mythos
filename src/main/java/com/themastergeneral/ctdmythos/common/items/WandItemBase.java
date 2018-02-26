package com.themastergeneral.ctdmythos.common.items;

public class WandItemBase extends BaseItem {

	public WandItemBase(String name, String modid) {
		super(name, modid);
		this.maxStackSize = 1;
		this.setMaxDamage(64);
	}

}
