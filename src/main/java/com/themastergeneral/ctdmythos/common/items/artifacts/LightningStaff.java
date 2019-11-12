package com.themastergeneral.ctdmythos.common.items.artifacts;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class LightningStaff extends BaseItem {

	public LightningStaff(String name) {
		super(name);
		this.setMaxStackSize(1);
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
			int playerMythos = getMythos(attackerr);
			if (checkMythos(playerMythos, ModConfig.mythosCostLightingStaff))
			{
				removeMythos(attackerr, ModConfig.mythosCostLightingStaff);
				worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, target.posX, target.posY, target.posZ, false));
				target.attackEntityFrom(DamageSource.LIGHTNING_BOLT, Float.MAX_VALUE);
				target.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
				if (ModConfig.ovk_dmg)
				{
					target.attackEntityFrom(DamageSource.GENERIC, Float.MAX_VALUE);
					target.attackEntityFrom(DamageSource.WITHER, Float.MAX_VALUE);
					target.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
					target.attackEntityFrom(DamageSource.STARVE, Float.MAX_VALUE);
					target.attackEntityFrom(DamageSource.FLY_INTO_WALL, Float.MAX_VALUE);
					target.attackEntityFrom(DamageSource.ANVIL, Float.MAX_VALUE);
				}
			}
			else
			{
				attackerr.sendStatusMessage(new TextComponentTranslation(
		                "You need at least " + ModConfig.mythosCostLightingStaff + " mythos to use the Lightning Staff."),true);
			}
		}
		else
		{
			worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, target.posX, target.posY, target.posZ, false));
			target.attackEntityFrom(DamageSource.LIGHTNING_BOLT, Float.MAX_VALUE);
			target.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);
			if (ModConfig.ovk_dmg)
			{
				target.attackEntityFrom(DamageSource.GENERIC, Float.MAX_VALUE);
				target.attackEntityFrom(DamageSource.WITHER, Float.MAX_VALUE);
				target.attackEntityFrom(DamageSource.MAGIC, Float.MAX_VALUE);
				target.attackEntityFrom(DamageSource.STARVE, Float.MAX_VALUE);
				target.attackEntityFrom(DamageSource.FLY_INTO_WALL, Float.MAX_VALUE);
				target.attackEntityFrom(DamageSource.ANVIL, Float.MAX_VALUE);
			}
		}
		return true;
    }
}
