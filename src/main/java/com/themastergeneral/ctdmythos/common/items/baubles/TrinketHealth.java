package com.themastergeneral.ctdmythos.common.items.baubles;

import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TrinketHealth extends BasicBauble {
	
	public TrinketHealth(String name) {
		super(name);
		this.maxStackSize=1;
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.CHARM;
	}
	
	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase entity)
	{
		EntityPlayer player = (EntityPlayer) entity;
		double hp = player.getMaxHealth()+10F;
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(hp);
	}
	
	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase entity)
	{
		EntityPlayer player = (EntityPlayer) entity;
		double hp = player.getMaxHealth()-10F;
		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(hp);
	}	

}

