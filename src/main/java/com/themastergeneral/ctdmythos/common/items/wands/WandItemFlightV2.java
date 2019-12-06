package com.themastergeneral.ctdmythos.common.items.wands;

import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class WandItemFlightV2 extends WandItemFlight {

	int flighttime = 50;
	int resist = 120;
	public WandItemFlightV2(String name) 
	{
		super(name);
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack offhand = playerIn.getHeldItemOffhand();
        ItemStack mainhand = playerIn.getHeldItemMainhand();
        if ((mainhand.getItem() == ModItems.mythos_flight_wand) && (playerIn.onGround))
        {
        	if (getMythos(playerIn) >= ModConfig.mythosFlightWand)
        	{
        		removeMythos(playerIn, ModConfig.mythosFlightWand);
	            playerIn.addPotionEffect(
	            					new PotionEffect(
	            							MobEffects.LEVITATION, flighttime, 8, true, false));
	            playerIn.addPotionEffect(
	            					new PotionEffect(
	            							MobEffects.RESISTANCE, resist, 11, true, false));
	            worldIn.playSound(playerIn, playerIn.getPosition(), ModSounds.flight_wand, SoundCategory.PLAYERS, 1.0F, 1.0F);
	            playerIn.getCooldownTracker().setCooldown(this, flighttime);
        	}
        	else
        	{
        		playerIn.sendStatusMessage(new TextComponentString("You need " + ModConfig.mythosFlightWand + " Mythos to use this wand."), true);
        	}
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
