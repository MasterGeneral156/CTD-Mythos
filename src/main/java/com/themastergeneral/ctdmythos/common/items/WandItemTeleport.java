package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.common.config.ModConfig;

public class WandItemTeleport extends WandItemBase {

	public WandItemTeleport(String name, String modid) {
		super(name, modid);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		// Teleport user if they have Crystallized Ender in offhand, and
		// teleport wand in main-hand.
		if (mainhand.getItem() == ModItems.teleport_wand) {
			if (offhand.getItem() == ModItems.crystal_ender) {
				mainhand.damageItem(1, playerIn);
				offhand.shrink(1);
				BlockPos blockpos = worldIn.provider.getRandomizedSpawnPoint();
				playerIn.setPositionAndUpdate(blockpos.getX(), blockpos.getY(),
						blockpos.getZ());
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

}
