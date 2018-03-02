package com.themastergeneral.ctdmythos.common.items.artifacts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;

public class DebugItem extends BaseItem {

	public DebugItem(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		CTDMythos.logger.info(MainOffhandCrafting.instance().getRecipeList());
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

}
