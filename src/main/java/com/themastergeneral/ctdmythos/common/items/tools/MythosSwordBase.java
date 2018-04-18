package com.themastergeneral.ctdmythos.common.items.tools;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;

public class MythosSwordBase extends ItemSword implements ItemModelProvider {
	protected String name; // Name of the item.

	public MythosSwordBase(ToolMaterial material, String name, int damage) {
		super(material);
		this.name = name;
		this.setRegistryName(name);
		this.setCreativeTab(CTDMythos.creativeTab);
		this.setUnlocalizedName(name);
		this.setMaxDamage(damage);
	}

	@Override
	public void registerItemModel(Item item) {
		CTDCore.proxy.registerItemRenderer(CTDMythos.MODID, this, 0, name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		// Crystal oath + book in offhand to get a tome of XP
		if (mainhand.getItem() == ModItems.gladius_sword) {
			if (!worldIn.isRemote) {
				mainhand.damageItem(mainhand.getMaxDamage() / 3, playerIn);
				playerIn.addPotionEffect(new PotionEffect(
						MobEffects.RESISTANCE, 200, 4, true, false));
				playerIn.getCooldownTracker().setCooldown(mainhand.getItem(),
						500);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}
}
