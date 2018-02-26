package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.base.BaseItem;

public class MulesKickItem extends BaseItem {

	public MulesKickItem(String name) {
		super(name, CTDMythos.MODID);
		this.maxStackSize=1;
		this.setMaxDamage(4);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack ItemStack = playerIn.getHeldItem(handIn);
		playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 4,
				true, false));
		ItemStack.damageItem(1, playerIn);
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

}
