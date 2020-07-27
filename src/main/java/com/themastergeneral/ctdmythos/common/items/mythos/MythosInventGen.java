package com.themastergeneral.ctdmythos.common.items.mythos;

import java.text.NumberFormat;
import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MythosInventGen extends MythosItemBase {

	public int mpt;
	public MythosInventGen(String name, int poolSize, int changeSize, int mythosPerTick) 
	{
		super(name, poolSize, changeSize);
		mpt = mythosPerTick;
	}
	
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if (!worldIn.isRemote)
		{
			//Mythos generation and checks.
			if (getCurrentPool(stack) > getMaxPool(stack))
			{
				setPool(stack, getMaxPool(stack));
			}
			if (getCurrentPool(stack) < getMaxPool(stack))
			{
				//Item specific effects.
				Item item = stack.getItem();
				//Mythos Exciter works only in daytime.
				if (item == ModItems.mythos_exciter)
				{
					if (entityIn.getEntityWorld().isDaytime())
					{
						addToPool(stack, mpt);
					}
				}
			}
		}
    }
	// Add tooltip on client side.
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
            List<String> tooltip, ITooltipFlag flagIn)
    {
    	tooltip.add("Mythos: " + NumberFormat.getInstance().format(getCurrentPool(stack)) + "/" + NumberFormat.getInstance().format(getMaxPool(stack)));
    	tooltip.add("Generates " + NumberFormat.getInstance().format(mpt) + " Mythos/t");
    }

}
