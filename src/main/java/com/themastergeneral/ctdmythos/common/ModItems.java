package com.themastergeneral.ctdmythos.common;

import com.themastergeneral.ctdcore.item.RegisterItem;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.HumanEffigyItem;

public class ModItems extends RegisterItem {
	public static HumanEffigyItem humaneffigy;

	public static void registerItems() {
		humaneffigy = register(new HumanEffigyItem("humaneffigy",
				CTDMythos.MODID));
	}
}
