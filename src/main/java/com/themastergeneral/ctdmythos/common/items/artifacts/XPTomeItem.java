package com.themastergeneral.ctdmythos.common.items.artifacts;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class XPTomeItem extends BaseItem {

	public XPTomeItem(String name) {
		super(name);
		this.maxStackSize = 1;
		this.setNoRepair();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		if (mainhand.getItem() == ModItems.xptome) {
			playerIn.addExperienceLevel(ModConfig.StoredLevels);
			mainhand.shrink(1);
			worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX,
					playerIn.posY, playerIn.posZ, new ItemStack(Items.BOOK)));
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn,
			List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Requires " + ModConfig.StoredLevels
				+ " levels to be created.");
	}

}
