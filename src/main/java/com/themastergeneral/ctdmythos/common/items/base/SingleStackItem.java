package com.themastergeneral.ctdmythos.common.items.base;


public class SingleStackItem extends BaseItem {

	public SingleStackItem(String name, String modid) {
		super(name, modid);
		this.setMaxStackSize(1);
	}

}
