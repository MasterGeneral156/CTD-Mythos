package com.themastergeneral.ctdmythos.common.items.artifacts;

import java.util.Random;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class TalismanItemEvasion extends BaseItem
{

    protected int doheal = 0;

    public TalismanItemEvasion(String name)
    {
        super(name);
        this.setMaxDamage(5);
        this.setMaxStackSize(1);
    }

    // 15% chance to give player regeneration when they're hit.
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
            int itemSlot, boolean isSelected)
    {
    	if (entityIn instanceof EntityPlayer)
    	{
    		EntityPlayer playerIn = (EntityPlayer) entityIn;
		        if (!worldIn.isRemote)
		        {
		            if ((playerIn.hurtResistantTime != 0) && (doheal == 0))
		            {
		            	if (checkMythos(getMythos(playerIn), ModConfig.mythosCostTalisman))
		        		{
		        			removeMythos(playerIn, ModConfig.mythosCostTalisman);
			                Random randomGenerator = new Random();
			                int actualheal = randomGenerator.nextInt(100);
			                if (actualheal <= 15)
			                {
			                    double d0 = playerIn.posX;
			                    double d1 = playerIn.posY;
			                    double d2 = playerIn.posZ;
			
			                    for (int i = 0; i < 16; ++i)
			                    {
			                        double d3 = playerIn.posX
			                                + (((EntityLivingBase) playerIn).getRNG()
			                                        .nextDouble() - 0.5D) * 16.0D;
			                        double d4 = MathHelper.clamp(playerIn.posY
			                                + (double) (((EntityLivingBase) playerIn)
			                                        .getRNG().nextInt(16) - 8), 0.0D,
			                                (double) (worldIn.getActualHeight() - 1));
			                        double d5 = playerIn.posZ
			                                + (((EntityLivingBase) playerIn).getRNG()
			                                        .nextDouble() - 0.5D) * 16.0D;
		
			                        if (playerIn.isRiding())
			                        {
			                        	playerIn.dismountRidingEntity();
			                        }
			
			                        if (((EntityLivingBase) playerIn).attemptTeleport(d3,
			                                d4, d5))
			                        {
			                            worldIn.playSound((EntityPlayer) null, d0, d1, d2,
			                                    SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT,
			                                    SoundCategory.PLAYERS, 1.0F, 1.0F);
			                            playerIn.playSound(
			                                    SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT,
			                                    1.0F, 1.0F);
			                            break;
			                        }
			                    }
			                    doheal = 1;
			                }
		        		}
		            }
		            	if ((doheal == 1) && (entityIn.hurtResistantTime == 0))
		            		doheal = 0;
	        	}
    		}
    	}
    }
