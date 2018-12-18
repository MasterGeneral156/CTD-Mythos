package com.themastergeneral.ctdmythos.common.items.misc;

import com.themastergeneral.ctdmythos.common.items.ModItems;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class BottleLightning extends GlowingItem {

	public BottleLightning(String name) {
		super(name);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn,
            EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		if (mainhand.getItem() == ModItems.lightning_bottle)
		{
			worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, false));
			playerIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 100000F);
			mainhand.shrink(1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
                playerIn.getHeldItem(handIn));
    }

}
