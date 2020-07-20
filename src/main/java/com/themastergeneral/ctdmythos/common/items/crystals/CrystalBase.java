package com.themastergeneral.ctdmythos.common.items.crystals;

import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CrystalBase extends BaseItem {
	
	public CrystalBase(String name) {
		super(name);
	}

	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
            int itemSlot, boolean isSelected)
    {
        EntityPlayer playerIn = (EntityPlayer) entityIn;
        ItemStack offhand = playerIn.getHeldItemOffhand();
        if (ModConfig.crystal_effects)
        {
	        // Blindness effect with grief
	        if ((stack.getItem() == ModItems.crystal_grief)
	                && (offhand.getItem() != ModItems.crystal_glove))
	        {
	            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
	                    MobEffects.BLINDNESS, 20, 0, true, false));
	        }
	        // Nausea effect with memory
	        if ((stack.getItem() == ModItems.crystal_memory)
	                && (offhand.getItem() != ModItems.crystal_glove))
	        {
	            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
	                    MobEffects.WEAKNESS, 20, 2, true, false));
	        }
	        // Slowness effect with Woe
	        if ((stack.getItem() == ModItems.crystal_woe)
	                && (offhand.getItem() != ModItems.crystal_glove))
	        {
	            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
	                    MobEffects.SLOWNESS, 20, 2, true, false));
	        }
        }
    }

}
