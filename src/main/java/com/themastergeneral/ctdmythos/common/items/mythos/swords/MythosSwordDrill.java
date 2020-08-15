package com.themastergeneral.ctdmythos.common.items.mythos.swords;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.mythos.MythosSword;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class MythosSwordDrill extends MythosSword {

	private float attackDamage;

	public MythosSwordDrill() 
	{
		super("tmgdrill", 4096, 128);
		this.attackDamage = 5.25F;
	}
	
	public float getAttackDamage()
	{
		return attackDamage;
		
	}
	
	@Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		if (target instanceof EntityPlayer)
        {
            // Cast entity to player
            EntityPlayer targetted = (EntityPlayer) target;
            EntityPlayer attackerr = (EntityPlayer) attacker;           

            // Get armor items
            ItemStack boots = targetted.inventory.armorItemInSlot(0);
            ItemStack leggings = targetted.inventory.armorItemInSlot(1);
            ItemStack chest = targetted.inventory.armorItemInSlot(2);
            ItemStack helmet = targetted.inventory.armorItemInSlot(3);
            if (getCurrentPool(stack) >= ModConfig.mythosCostDrill)
            {
				if (!boots.isEmpty() || !leggings.isEmpty() || !chest.isEmpty() || !helmet.isEmpty())
	            {
		            // Delete the armor stack
		            targetted.inventory.deleteStack(boots);
		            targetted.inventory.deleteStack(leggings);
		            targetted.inventory.deleteStack(chest);
		            targetted.inventory.deleteStack(helmet);
		
		            // drop items
		            targetted.dropItem(boots, false);
		            targetted.dropItem(leggings, false);
		            targetted.dropItem(chest, false);
		            targetted.dropItem(helmet, false);
		            
		        	targetted.sendStatusMessage(new TextComponentTranslation("info.dropped.armor2"),true);
		            attackerr.sendStatusMessage(new TextComponentTranslation("info.dropped.armor"),true);
		            
		            removeFromPool(stack, ModConfig.mythosCostDrill);
	            }
            }
            else
            {
            	attackerr.sendStatusMessage(new TextComponentTranslation(
    	                "You need at least " + ModConfig.mythosCostDrill + " Mythos to disarm your opponent."),true);
            }
        }
		target.attackEntityFrom(DamageSource.GENERIC, getAttackDamage());
		return true;
    }

}
