package com.themastergeneral.ctdmythos.common.items.artifacts;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.processing.ModSounds;

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
import net.minecraft.world.World;

public class HumanEffigyItem extends BaseItem {

	public HumanEffigyItem(String name) {
		super(name);
		this.maxStackSize = 1;
		this.setMaxDamage(ModConfig.DurabilityHumanEffigy - 1);
		this.setNoRepair();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {

		ItemStack ItemStack = playerIn.getHeldItem(handIn);
		if (!worldIn.isRemote) {
			playerIn.setHealth(playerIn.getHealth() + 10F);
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SATURATION,
					20, 0, true, false));
			ItemStack.damageItem(1, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, 20);
		}
		worldIn.playSound(playerIn, playerIn.getPosition(),
				ModSounds.human_effigy, SoundCategory.PLAYERS, 1.0F, 1.0F);
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
			int itemSlot, boolean isSelected) {
		((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
				MobEffects.REGENERATION, 20, 0, true, false));
	}
}
