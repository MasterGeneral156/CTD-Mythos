package com.themastergeneral.ctdmythos.common.items.artifacts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;

public class DebugItem extends BaseItem {

	public DebugItem(String name) {
		super(name);
	}

	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn,
			BlockPos pos, EnumHand handIn, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		validMultiblock(pos, worldIn, playerIn);
		return EnumActionResult.SUCCESS;
	}

}
