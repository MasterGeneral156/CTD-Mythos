package com.themastergeneral.ctdmythos.common.items;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.base.MythosSwordBase;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class TMGDrill extends MythosSwordBase {

	public TMGDrill(String name) {
		super(ToolMaterial.IRON, name, 762);
		this.maxStackSize = 1;
	}
}
