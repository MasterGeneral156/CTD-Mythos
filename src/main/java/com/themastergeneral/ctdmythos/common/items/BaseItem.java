package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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
}
