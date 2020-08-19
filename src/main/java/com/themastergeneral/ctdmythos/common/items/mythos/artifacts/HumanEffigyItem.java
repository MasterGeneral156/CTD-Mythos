package com.themastergeneral.ctdmythos.common.items.mythos.artifacts;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.items.mythos.MythosItemBase;

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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HumanEffigyItem extends MythosItemBase
{

    protected int doheal = 0;

    public HumanEffigyItem(String name)
    {
        super(name, 1024, 16);
        this.maxStackSize = 1;
    }

    // Heal the player automatically when the Human Effigy is in their inventory
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
            int itemSlot, boolean isSelected)
    {
        if (!worldIn.isRemote)
        {
        	if (getCurrentPool(stack) >= ModConfig.mythosCostEffigy)
        	{
		        if (entityIn instanceof EntityLivingBase)
		        {
		            EntityLivingBase entitylive = (EntityLivingBase) entityIn;
		            if ((entitylive.getHealth()) < entitylive.getMaxHealth())
		            {
		            	if (doheal == 0)
		            		if (entitylive instanceof EntityPlayer)
		            			((EntityPlayer) entitylive).getCooldownTracker().setCooldown(this, 15);
		            	doheal++;
		            	if (doheal == 15)
		            	{
		            		entitylive.heal(0.5F);
		            		removeFromPool(stack, ModConfig.mythosCostEffigy);
		            		doheal = 0;
		            	}
		            }
		        }
        	}
        }
    }
}
