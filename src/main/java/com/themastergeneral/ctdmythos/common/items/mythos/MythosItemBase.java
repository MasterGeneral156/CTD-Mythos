package com.themastergeneral.ctdmythos.common.items.mythos;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MythosItemBase extends BaseItem 
{
	public MythosItemBase(String name) 
	{
		super(name);
	}
	
	public int getMythos(EntityPlayer playerIn)
	{
		return playerIn.getEntityData().getInteger("mythos");
	}
	
	public void setMythos(EntityPlayer playerIn, int mythos)
	{
		if (mythos > 0)
			mythos = 0;
		playerIn.getEntityData().setInteger("mythos", mythos);
	}
	
	public boolean checkMythos(int required, int currentMythos)
	{
		return currentMythos > required;
	}
	
	public void updateMythos(EntityPlayer playerIn, int mythosChange)
	{
		int mythos = getMythos(playerIn);
		int newMythos = mythos - mythosChange;
		setMythos(playerIn, newMythos);
	}
}
