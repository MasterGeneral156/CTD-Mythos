package com.themastergeneral.ctdmythos.common.items.tools;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import com.themastergeneral.ctdcore.item.CTDBow;
import com.themastergeneral.ctdmythos.CTDMythos;

public class LongBowItem extends CTDBow {

	public LongBowItem(String name, int drawspeed, int maxdurability) {
		super(name, CTDMythos.MODID, drawspeed, maxdurability);
		this.setCreativeTab(CTDMythos.creativeTab);
	}
}
