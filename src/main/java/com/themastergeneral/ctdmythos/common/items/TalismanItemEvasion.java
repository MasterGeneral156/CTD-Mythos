package com.themastergeneral.ctdmythos.common.items;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.base.BaseItem;

public class TalismanItemEvasion extends BaseItem {

	protected int doheal = 0;

	public TalismanItemEvasion(String name) {
		super(name);
		this.setMaxDamage(5);
		this.setMaxStackSize(1);
	}

	//15% chance to give player regeneration when they're hit.
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
			int itemSlot, boolean isSelected) {
		if ((entityIn.hurtResistantTime != 0) && (doheal == 0)) {
			doheal = 1;
			Random randomGenerator = new Random();
			int actualheal = randomGenerator.nextInt(100);
			if (actualheal <= 15) {
				((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
						MobEffects.INSTANT_HEALTH, 20, 6, true, false));
				stack.damageItem(1, (EntityLivingBase) entityIn);
				CTDMythos.logger.info("healed.");
			}
		}
		if ((doheal == 1) && (entityIn.hurtResistantTime == 0))
			doheal = 0;
	}

}
