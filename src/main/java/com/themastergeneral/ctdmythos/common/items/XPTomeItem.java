package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class XPTomeItem extends BaseItem {

	public XPTomeItem(String name, String modid) {
		super(name, modid);
		this.maxStackSize = 1;
		this.setNoRepair();
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		if (mainhand.getItem() == ModItems.xptome)
		{
			playerIn.addExperienceLevel(5);
			mainhand.shrink(1);
			playerIn.dropItem(new ItemStack(Items.BOOK), false);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

}
