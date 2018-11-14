package com.themastergeneral.ctdmythos.common.items.misc;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModArmorMaterials {
	public static ArmorMaterial shulker_armor = EnumHelper.addArmorMaterial(
			"shulker_armor", "ctdmythos:shulker_helmet", 100,
			new int[] { 5, 5, 5, 5 }, 7,
			SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 9F);
}
