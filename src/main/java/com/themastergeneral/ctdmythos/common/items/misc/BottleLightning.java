package com.themastergeneral.ctdmythos.common.items.misc;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class BottleLightning extends BaseItem {

	public BottleLightning(String name) {
		super(name);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn,
            EntityPlayer playerIn, EnumHand handIn)
    {
		worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, false));
		playerIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 100000F);
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
                playerIn.getHeldItem(handIn));
    }

}
