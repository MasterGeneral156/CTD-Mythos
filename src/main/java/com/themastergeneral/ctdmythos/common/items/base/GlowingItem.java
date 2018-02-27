package com.themastergeneral.ctdmythos.common.items.base;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GlowingItem extends BaseItem {

	public GlowingItem(String name) {
		super(name);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		par1ItemStack.setTagInfo("ench", new NBTTagList());
		return true;
	}
}
