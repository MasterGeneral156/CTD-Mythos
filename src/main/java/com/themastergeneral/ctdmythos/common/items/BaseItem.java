package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.themastergeneral.ctdcore.item.CTDItem;
import com.themastergeneral.ctdmythos.CTDMythos;

public class BaseItem extends CTDItem {
	public BaseItem(String name, String modid) {
		super(name, modid);
		this.setCreativeTab(CTDMythos.creativeTab);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
			int itemSlot, boolean isSelected) {
		// Blindness effect with grief
		if (stack.getItem() == ModItems.crystal_grief) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
					MobEffects.BLINDNESS, 20, 0, true, false));
		}
		// Nausea effect with memory
		if (stack.getItem() == ModItems.crystal_memory) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
					MobEffects.NAUSEA, 100, 0, true, false));
		}
		// Slowness effect with Woe
		if (stack.getItem() == ModItems.crystal_woe) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
					MobEffects.SLOWNESS, 20, 0, true, false));
		}
		// Extinguish fire with Fire
		if (stack.getItem() == ModItems.crystal_fire) {
			((EntityLivingBase) entityIn).extinguish();
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		//Crystalized Memory to repair the item in the user's offhand, at
		//the cost of one Crystalized Memory.
		if (mainhand.getItem() == ModItems.crystal_memory)
		{
			if (offhand != ItemStack.EMPTY)
			{
				if (offhand.isItemDamaged())
				{
					offhand.setItemDamage(0);
					mainhand.shrink(1);
				}
			}
		}
		//Crystal oath + book in offhand to get a tome of XP
		if (mainhand.getItem() == ModItems.crystal_oath)
		{
			if (offhand != ItemStack.EMPTY)
			{
				if (offhand.getItem() == Items.BOOK)
				{
					if (playerIn.experienceLevel >= 5)
					{
						playerIn.addExperienceLevel(-5);
						offhand.shrink(1);
						mainhand.shrink(1);
						playerIn.dropItem(new ItemStack(ModItems.xptome), false);
					}
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}
}
