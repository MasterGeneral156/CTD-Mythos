package com.themastergeneral.ctdmythos.common.items;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.base.WandItemBase;
import com.themastergeneral.ctdmythos.common.processing.ModSounds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WandItemFlight extends WandItemBase {

	private static int flighttime = 0;
	private static int resist = 0;
	private static int flightmod = 0;

	public WandItemFlight(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		if ((mainhand.getItem() == ModItems.flight_wand) && (playerIn.onGround)) {
			if (offhand.getItem() == Items.IRON_INGOT) {
				flighttime = 80;
				resist = 120;
			}

			else if (offhand.getItem() == Items.GOLD_INGOT) {
				flighttime = 40;
				resist = 70;
				flightmod = 3;
			}

			else if (offhand.getItem() == Items.DIAMOND) {
				flighttime = 300;
				resist = 360;
				flightmod = 1;
			}

			else if (offhand.getItem() == Items.EMERALD) {
				flighttime = 400;
				resist = 480;
			}
			if (flighttime > 0) {
				playerIn.addPotionEffect(new PotionEffect(
						MobEffects.LEVITATION, flighttime, flightmod, true,
						false));
				playerIn.addPotionEffect(new PotionEffect(
						MobEffects.RESISTANCE, resist, 11, true, false));
				mainhand.damageItem(1, playerIn);
				worldIn.playSound(playerIn, playerIn.getPosition(), ModSounds.flight_wand, SoundCategory.PLAYERS, 1.0F, 1.0F);
				offhand.shrink(1);
				playerIn.getCooldownTracker().setCooldown(this, flighttime);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

}
