package com.themastergeneral.ctdmythos.common.items.base;


public class SingleStackItem extends BaseItem {

	public SingleStackItem(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

}
