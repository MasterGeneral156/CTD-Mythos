package com.themastergeneral.ctdmythos.common.items.artifacts;

import net.minecraft.item.ItemStack;

import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

public class OreDoublingNode extends BaseItem {

	public OreDoublingNode(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(1);
	}

	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
		return false;
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	public boolean hasContainerItem(ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack stack = itemStack.copy();
		stack.setItemDamage(stack.getItemDamage() + 1);
		this.maxStackSize = 1;
		return stack;
	}

}
