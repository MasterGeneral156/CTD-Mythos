package com.themastergeneral.ctdmythos.common.items.misc;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;

import com.themastergeneral.ctdcore.item.CTDItem;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.processing.ModSounds;

public class BaseItem extends CTDItem {
	public BaseItem(String name) {
		super(name, CTDMythos.MODID);
		this.setCreativeTab(CTDMythos.creativeTab);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
			int itemSlot, boolean isSelected) {
		EntityPlayer playerIn = (EntityPlayer) entityIn;
		ItemStack offhand = playerIn.getHeldItemOffhand();
		// Blindness effect with grief
		if ((stack.getItem() == ModItems.crystal_grief)
				&& (offhand.getItem() != ModItems.crystal_glove)) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
					MobEffects.BLINDNESS, 20, 0, true, false));
		}
		// Nausea effect with memory
		if ((stack.getItem() == ModItems.crystal_memory)
				&& (offhand.getItem() != ModItems.crystal_glove)) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
					MobEffects.WEAKNESS, 20, 2, true, false));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		// Crystalized Memory to repair the item in the user's offhand, at
		// the cost of one Crystalized Memory.
		if (mainhand.getItem() == ModItems.crystal_memory) {
			if (offhand != ItemStack.EMPTY) {
				if (offhand.isItemDamaged()) {
					offhand.setItemDamage(0);
					mainhand.shrink(1);
					worldIn.playSound(playerIn, playerIn.getPosition(),
							ModSounds.spell_complete, SoundCategory.PLAYERS,
							1.0F, 1.0F);
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}
}
