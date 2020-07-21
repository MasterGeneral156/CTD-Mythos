package com.themastergeneral.ctdmythos.common.items.mythos;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MythosInventGen extends MythosItemBase {

	public int mpt;
	public MythosInventGen(String name, int mythosPerTick) {
		super(name);
		mpt = mythosPerTick;
	}
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if (entityIn instanceof EntityLivingBase)
        {
            EntityLivingBase entitylive = (EntityLivingBase) entityIn;
            EntityPlayer playerIn = (EntityPlayer) entityIn;
            addMythos(playerIn, mpt);
        }
    }
	// Add tooltip on client side.
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
            List<String> tooltip, ITooltipFlag flagIn)
    {
    	tooltip.add("Generates " + mpt + " Mythos/t");
    }

}
