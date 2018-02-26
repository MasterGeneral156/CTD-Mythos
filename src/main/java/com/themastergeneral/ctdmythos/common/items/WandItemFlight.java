package com.themastergeneral.ctdmythos.common.items;

import com.themastergeneral.ctdmythos.CTDMythos;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WandItemFlight extends WandItemBase {

	private static int flighttime = 0;
	private static int flightmod = 0;

	public WandItemFlight(String name, String modid) {
		super(name, modid);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		CTDMythos.logger.info("what");
		if (mainhand.getItem() == ModItems.flight_wand) {
			if (offhand.getItem() == Items.IRON_INGOT) {
				flighttime = 80;
				flightmod = 0;
			}

			else if (offhand.getItem() == Items.GOLD_INGOT) {
				flighttime = 40;
				flightmod = 3;
			}

			else if (offhand.getItem() == Items.DIAMOND) {
				flighttime = 300;
				flightmod = 1;
			}

			else if (offhand.getItem() == Items.EMERALD) {
				flighttime = 750;
				flightmod = 0;
			}
			else
			{
				flighttime = 0;
				flightmod = 0;
			}
			if (flighttime > 0) {
				playerIn.addPotionEffect(new PotionEffect(
						MobEffects.LEVITATION, flighttime, flightmod, true,
						false));
				playerIn.addPotionEffect(new PotionEffect(
						MobEffects.RESISTANCE, flighttime*2, 11, true, false));
				mainhand.damageItem(1, playerIn);
				offhand.shrink(1);
			}
		}
		CTDMythos.logger.info(flighttime);
		CTDMythos.logger.info(flightmod);
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

}
