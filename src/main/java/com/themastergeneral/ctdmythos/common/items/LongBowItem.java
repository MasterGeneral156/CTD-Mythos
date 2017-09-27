package com.themastergeneral.ctdmythos.common.items;

import com.themastergeneral.ctdcore.item.CTDBow;
import com.themastergeneral.ctdmythos.CTDMythos;

public class LongBowItem extends CTDBow {

	public LongBowItem(String name, String modid, int drawspeed,
			int maxdurability) {
		super(name, modid, drawspeed, maxdurability);
		this.setCreativeTab(CTDMythos.creativeTab);
	}

}
