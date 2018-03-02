package com.themastergeneral.ctdmythos.common.items.crystals;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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

public class CrystallizedOath extends BaseItem {

	public CrystallizedOath(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		// Crystal oath + book in offhand to get a tome of XP
		if (MainOffhandCrafting.instance().getRecipeResult(mainhand) != null) {
			if (MainOffhandCrafting.instance().getRecipeOffhand(mainhand)
					.getItem() == offhand.getItem()) {
				if (!worldIn.isRemote) {
					if (playerIn.experienceLevel >= ModConfig.StoredLevels) {
						playerIn.addExperienceLevel(-ModConfig.StoredLevels);
						offhand.shrink(1);
						mainhand.shrink(1);
						worldIn.playSound(playerIn, playerIn.getPosition(),
								ModSounds.spell_complete,
								SoundCategory.PLAYERS, 1.0F, 1.0F);
						worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX,
								playerIn.posY, playerIn.posZ, MainOffhandCrafting
										.instance().getRecipeResult(mainhand)));
					}
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}
}
