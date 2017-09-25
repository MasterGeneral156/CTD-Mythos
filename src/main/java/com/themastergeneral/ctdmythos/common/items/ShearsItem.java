package com.themastergeneral.ctdmythos.common.items;

import java.util.Random;

import com.themastergeneral.ctdmythos.common.config.ModConfig;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ShearsItem extends BaseItem {

	public ShearsItem(String name, String modid) {
		super(name, modid);
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
			stack.damageItem(1, attacker);
			// Random Number Generator!
			Random randomGenerator = new Random();
			int itemdrop = randomGenerator.nextInt(100);
			if (itemdrop <= 20)
				player.inventory.addItemStackToInventory(new ItemStack(
						ModItems.humansoul));
		}
		return false;
	}
}
