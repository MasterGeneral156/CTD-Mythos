package com.themastergeneral.ctdmythos.common.items.crystals;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;
import com.themastergeneral.ctdmythos.common.processing.ModSounds;

public class CrystallizedWoe extends BaseItem {

	public CrystallizedWoe(String name) {
		super(name);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
			int itemSlot, boolean isSelected) {
		EntityPlayer playerIn = (EntityPlayer) entityIn;
		ItemStack offhand = playerIn.getHeldItemOffhand();
		if ((stack.getItem() == ModItems.crystal_woe)
				&& (offhand.getItem() != ModItems.crystal_glove)) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
					MobEffects.SLOWNESS, 20, 2, true, false));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		// Crystal oath + book in offhand to get a tome of XP
		if (MainOffhandCrafting.instance().getRecipeResult(mainhand, offhand) != null) {
			if (MainOffhandCrafting.instance().getRecipeOffhand(mainhand, offhand)
					.getItem() == offhand.getItem()) {
				if (!worldIn.isRemote) {
					offhand.shrink(1);
					mainhand.shrink(1);
					worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX,
							playerIn.posY, playerIn.posZ, MainOffhandCrafting
									.instance().getRecipeResult(mainhand, offhand)));
				}
				worldIn.playSound(playerIn, playerIn.getPosition(),
						ModSounds.spell_complete, SoundCategory.PLAYERS, 1.0F,
						1.0F);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

}
