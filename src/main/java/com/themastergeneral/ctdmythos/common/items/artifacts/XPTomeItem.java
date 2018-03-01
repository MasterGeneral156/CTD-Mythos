package com.themastergeneral.ctdmythos.common.items.artifacts;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class XPTomeItem extends BaseItem {

	public XPTomeItem(String name) {
		super(name);
		this.maxStackSize = 1;
		this.setNoRepair();
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		if (mainhand.getItem() == ModItems.xptome)
		{
			playerIn.addExperienceLevel(ModConfig.StoredLevels);
			mainhand.shrink(1);
			playerIn.dropItem(new ItemStack(Items.BOOK), false);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

}