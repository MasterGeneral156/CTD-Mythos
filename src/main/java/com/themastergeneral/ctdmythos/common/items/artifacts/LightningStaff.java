package com.themastergeneral.ctdmythos.common.items.artifacts;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class LightningStaff extends BaseItem {

	public LightningStaff(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(ModConfig.lightning_staff_damage-1);
		this.setNoRepair();
	}

	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		World worldIn=target.getEntityWorld();
		if (attacker instanceof EntityPlayer)
		{
			EntityPlayer attackerr = (EntityPlayer) attacker;
			stack.damageItem(1, attackerr);
		}
		worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, target.posX, target.posY, target.posZ, false));
		target.attackEntityFrom(DamageSource.LIGHTNING_BOLT, Float.MAX_VALUE);
		//Maybe beat draconic?
		target.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
		return true;
    }
}
