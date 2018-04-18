package com.themastergeneral.ctdmythos.common.items.artifacts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

public class MulesKickItem extends BaseItem {

	public MulesKickItem(String name) {
		super(name);
		this.maxStackSize = 1;
		this.setMaxDamage(4);
	}

	// When the Mule's Kick is used, give the player Speed 5 for 10 Seconds.
	// Damage the item and play a sound as well.
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		if (!worldIn.isRemote) {
			ItemStack ItemStack = playerIn.getHeldItem(handIn);
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 200, 4,
					true, false));
			ItemStack.damageItem(1, playerIn);
			playerIn.getCooldownTracker().setCooldown(this, 200);
		}
		worldIn.playSound(playerIn, playerIn.getPosition(),
				ModSounds.mules_kick, SoundCategory.PLAYERS, 1.0F, 1.0F);
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

}
