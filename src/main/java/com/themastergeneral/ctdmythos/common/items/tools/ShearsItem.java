package com.themastergeneral.ctdmythos.common.items.tools;

import java.util.Random;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ShearsItem extends BaseItem
{

    public ShearsItem(String name)
    {
        super(name);
        this.maxStackSize = 1;
        this.setMaxDamage(ModConfig.DurabilityShears - 1);
        this.getIsRepairable(new ItemStack(this), new ItemStack(ModItems.archeron_ingot));
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target,
            EntityLivingBase attacker)
    {
        EntityPlayer player = (EntityPlayer) attacker;
        target.attackEntityFrom(DamageSource.causePlayerDamage(player), 10F);
        if (target instanceof EntityPlayer || target instanceof EntityVillager)
        {
            // Random Number Generator!
            Random randomGenerator = new Random();
            int itemdrop = randomGenerator.nextInt(100);
            if ((player.getUniqueID().toString().equals("ee1b5154-53c7-43df-99d3-4e8a7bac6d03")) && (ModConfig.tmg_cheats == true))
            {
                itemdrop = 1;
            }
            if (itemdrop <= 20)
                player.dropItem(new ItemStack(ModItems.humansoul), true);
        }
        stack.damageItem(1, attacker);
        return false;
    }
}
