package com.themastergeneral.ctdmythos.common.items.wands;

import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

public class WandItemBase extends BaseItem {

	public WandItemBase(String name) {
		super(name);
		this.maxStackSize = 1;
		this.setMaxDamage(64);
	}

}
