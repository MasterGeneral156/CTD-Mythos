package com.themastergeneral.ctdmythos.common.items.tools;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class TMGDrill extends MythosSwordBase
{

    public TMGDrill(String name)
    {
        super(ToolMaterial.IRON, name, 762);
        this.maxStackSize = 1;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
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
	            stack.damageItem(this.getMaxDamage(stack) / 3, attacker);
            }
        }
        return true;
    }
}
