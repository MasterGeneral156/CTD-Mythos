package com.themastergeneral.ctdmythos.common.items.mythos;

import com.themastergeneral.ctdmythos.common.config.ModConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class MythosPool extends MythosItemBase 
{
	public MythosPool(String name) 
	{
		super(name);
	}
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		int mythos = getMythos(playerIn);
		playerIn.sendStatusMessage(new TextComponentTranslation(
                mythos + " / " + ModConfig.mythosMaxStorage),true);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

}
