package com.themastergeneral.ctdmythos.common.items.artifacts;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HumanEffigyItem extends BaseItem
{

    protected int doheal = 100;

    public HumanEffigyItem(String name)
    {
        super(name);
        this.maxStackSize = 1;
        this.setNoRepair();
        this.setMaxDamage(25);
    }

    // Give the player 5 hearts, saturation and damage the item when the
    // player uses the Human Effigy.
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn,
            EntityPlayer playerIn, EnumHand handIn)
    {
        // Get mainhand item.
        ItemStack ItemStack = playerIn.getHeldItem(handIn);
        playerIn.heal(10F);
        playerIn.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 5,
                1, true, false));
        playerIn.getCooldownTracker().setCooldown(this, 20);
        ItemStack.damageItem(1, playerIn);
        // Play sound.
        worldIn.playSound(playerIn, playerIn.getPosition(),
                ModSounds.human_effigy, SoundCategory.PLAYERS, 1.0F, 1.0F);
    return new ActionResult<ItemStack>(EnumActionResult.PASS,
            playerIn.getHeldItem(handIn));
    }

    // Heal the player automatically when the Human Effigy is in their inventory
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
            int itemSlot, boolean isSelected)
    {
        // Cast entity to EntityLivingBase
        if (entityIn instanceof EntityLivingBase)
        {
            EntityLivingBase entitylive = (EntityLivingBase) entityIn;
            EntityPlayer playerIn = (EntityPlayer) entityIn;
            // If entity is hurt
            if ((entityIn.hurtResistantTime != 0) && (((EntityLivingBase) entityIn).hurtTime != 0))
            {
                doheal = 100;
            }
            if (doheal > 0)
                doheal--;
            if (doheal == 0)
            {
                entitylive.addPotionEffect(new PotionEffect(
                        MobEffects.REGENERATION, 30, 4, true, false));
                doheal = -1;
            }
        }
    }
}
