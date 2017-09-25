package com.themastergeneral.ctdmythos.common.items;

public class SingleStackItem extends BaseItem {

	public SingleStackItem(String name, String modid) {
		super(name, modid);
		this.setMaxStackSize(1);
	}

}
