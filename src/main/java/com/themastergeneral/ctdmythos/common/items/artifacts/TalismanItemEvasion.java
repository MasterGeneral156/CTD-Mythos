package com.themastergeneral.ctdmythos.common.items.artifacts;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

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
        if (!worldIn.isRemote)
        {
            if ((entityIn.hurtResistantTime != 0) && (doheal == 0))
            {
                Random randomGenerator = new Random();
                int actualheal = randomGenerator.nextInt(100);
                if (actualheal <= 15)
                {
                    double d0 = entityIn.posX;
                    double d1 = entityIn.posY;
                    double d2 = entityIn.posZ;

                    for (int i = 0; i < 16; ++i)
                    {
                        double d3 = entityIn.posX
                                + (((EntityLivingBase) entityIn).getRNG()
                                        .nextDouble() - 0.5D) * 16.0D;
                        double d4 = MathHelper.clamp(entityIn.posY
                                + (double) (((EntityLivingBase) entityIn)
                                        .getRNG().nextInt(16) - 8), 0.0D,
                                (double) (worldIn.getActualHeight() - 1));
                        double d5 = entityIn.posZ
                                + (((EntityLivingBase) entityIn).getRNG()
                                        .nextDouble() - 0.5D) * 16.0D;

                        if (entityIn.isRiding())
                        {
                            entityIn.dismountRidingEntity();
                        }

                        if (((EntityLivingBase) entityIn).attemptTeleport(d3,
                                d4, d5))
                        {
                            worldIn.playSound((EntityPlayer) null, d0, d1, d2,
                                    SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT,
                                    SoundCategory.PLAYERS, 1.0F, 1.0F);
                            entityIn.playSound(
                                    SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT,
                                    1.0F, 1.0F);
                            break;
                        }
                    }
                    doheal = 1;
                    stack.damageItem(1, (EntityLivingBase) entityIn);
                }
            }
            if ((doheal == 1) && (entityIn.hurtResistantTime == 0))
                doheal = 0;
        }
    }
}
