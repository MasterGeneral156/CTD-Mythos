package com.themastergeneral.ctdmythos.common.items.mythos;

import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class MythosFlightWand extends MythosItemBase 
{
	private int changeSize;
	public MythosFlightWand(String name, int maxStorage, int changesize) 
	{
		super(name, maxStorage, changesize);
		this.changeSize = changesize;
	}
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if (!worldIn.isRemote)
        {
			int playerMythos = BaseItem.getMythos(playerIn);
            ItemStack mainhand = playerIn.getHeldItem(handIn);
            if (playerIn.isSneaking())
            {
            	if (playerMythos >= changeSize)
            	{
            		if ((getCurrentPool(mainhand) + changeSize) <= (getMaxPool(mainhand)))
            		{
	        			addToPool(mainhand,changeSize);
	        			removeMythos(playerIn,changeSize);
	        			worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.PLAYERS, 1.0F, 1.0F);
            		}
            	}
            	playerIn.sendStatusMessage(new TextComponentString("" + getCurrentPool(mainhand) + "/" + getMaxPool(mainhand)), true);
            }
            else
            {
            	if (getCurrentPool(mainhand) >= changeSize)
            	{
            		removeFromPool(mainhand,changeSize);
            		worldIn.playSound(playerIn, playerIn.getPosition(), ModSounds.flight_wand, SoundCategory.PLAYERS, 1.0F, 1.0F);
            		playerIn.getCooldownTracker().setCooldown(this, 60);
            		playerIn.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 60, 5, true, false));
            		playerIn.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 90, 128, true, false));
            		playerIn.sendStatusMessage(new TextComponentString("" + getCurrentPool(mainhand) + "/" + getMaxPool(mainhand)), true);
            	}
            	else
            	{
            		playerIn.sendStatusMessage(new TextComponentString("You need " + changeSize + " Mythos to use this wand."), true);
            	}
            }
        }
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
