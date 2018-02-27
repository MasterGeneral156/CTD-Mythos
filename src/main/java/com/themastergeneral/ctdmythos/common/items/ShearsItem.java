package com.themastergeneral.ctdmythos.common.items;

import java.util.Random;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.base.BaseItem;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ShearsItem extends BaseItem {

	public ShearsItem(String name) {
		super(name);
		this.maxStackSize = 1;
		this.setMaxDamage(ModConfig.DurabilityShears - 1);
		this.setNoRepair();
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target,
			EntityLivingBase attacker) {
		EntityPlayer player = (EntityPlayer) attacker;
		target.setHealth(target.getHealth() - 10F);
		if (target instanceof EntityPlayer || target instanceof EntityVillager) {
			// Random Number Generator!
			Random randomGenerator = new Random();
			int itemdrop = randomGenerator.nextInt(100);
			if (itemdrop <= 20)
				player.dropItem(new ItemStack(ModItems.humansoul), true);
		}
		if (target instanceof EntityEnderman) {
			// Random Number Generator!
			Random randomGenerator = new Random();
			int itemdrop = randomGenerator.nextInt(100);
			if (attacker.getHeldItemOffhand().getItem() == ModItems.crystal_grief) {
				if (itemdrop <= 5)
					player.dropItem(new ItemStack(ModItems.crystal_ender), true);
			}
		}
		// Ethereal mob drop. Would be phantoms in 1.13 tho
		if (target instanceof EntitySpider) {
			// Random Number Generator!
			Random randomGenerator = new Random();
			int itemdrop = randomGenerator.nextInt(100);
			if (itemdrop <= 10)
				player.dropItem(new ItemStack(ModItems.ethereal_fiber), true);
		}
		// Mule's Kick drop chance...
		if (target instanceof EntitySpider) {
			// Random Number Generator!
			Random randomGenerator = new Random();
			int itemdrop = randomGenerator.nextInt(100);
			if (itemdrop <= 2)
				player.dropItem(new ItemStack(ModItems.muleskick), true);
		}
		stack.damageItem(1, attacker);
		return false;
	}
}
