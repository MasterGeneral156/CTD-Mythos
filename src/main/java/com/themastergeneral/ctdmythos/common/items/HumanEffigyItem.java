package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class HumanEffigyItem extends BaseItem {

	public HumanEffigyItem(String name, String modid) {
		super(name, modid);
		this.maxStackSize = 1;
		this.setMaxDamage(4);
		this.setNoRepair();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack ItemStack = playerIn.getHeldItem(handIn);
		playerIn.setHealth(playerIn.getHealth() + 10F);
		playerIn.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20, 0,
				true, false));
		ItemStack.damageItem(1, playerIn);
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
			int itemSlot, boolean isSelected) {
		((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
				MobEffects.REGENERATION, 20, 0, true, false));
	}
}
