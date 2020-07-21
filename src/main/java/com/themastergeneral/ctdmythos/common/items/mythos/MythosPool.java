package com.themastergeneral.ctdmythos.common.items.mythos;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.config.ModConfig;

import mezz.jei.util.Log;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MythosPool extends MythosItemBase 
{
	private int poolSize;
	private int changeSize;
	public MythosPool(String name, int maxStorage, int changesize) 
	{
		super(name);
		poolSize = maxStorage;
		changeSize = changesize;
	}
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		int playerMythos = getMythos(playerIn);
		//New way will shift click to store, click to withdraw.
		if (!worldIn.isRemote)
        {
            ItemStack mainhand = playerIn.getHeldItem(handIn);
            if (playerIn.isSneaking())
            {
            	if (playerMythos >= changeSize)
            	{
            		if ((getCurrentPool(mainhand) + changeSize) <= (getMaxPool(mainhand)))
            		{
	        			addToPool(mainhand,changeSize);
	        			removeMythos(playerIn,changeSize);
            		}
            	}
            }
            else
            {
            	if (getCurrentPool(mainhand) >= changeSize)
            	{
            		if ((playerMythos + changeSize) <= ModConfig.mythosMaxStorage)
            		{
	        			removeFromPool(mainhand,changeSize);
	        			addMythos(playerIn,changeSize);
            		}
            	}
            }
            FMLLog.info("Player Mythos: " + getMythos(playerIn));
            playerIn.sendStatusMessage(new TextComponentString("" + getCurrentPool(mainhand) + "/" + getMaxPool(mainhand)), true);
        }
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
	
	@Override 
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if (getCurrentPool(stack) > getMaxPool(stack))
		{
			setPool(stack, getMaxPool(stack));
		}
    }
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add("Mythos: " + getCurrentPool(stack) + "/" + getMaxPool(stack));
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) 
	{
		return true;
	}
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
    {
        return MathHelper.clamp(1.0D - ((double) getCurrentPool(stack) / (double) getMaxPool(stack)), 0.0D, 1.0D);
    }
	
	public int getCurrentPool(ItemStack stack)
	{
		 NBTTagCompound nbt = stack.getTagCompound();
         if (nbt == null)
         {
         	nbt = new NBTTagCompound();
         }
         if (!nbt.hasKey("mythos_pool"))
         {
         	nbt.setInteger("mythos_pool", 0);
         }
         stack.setTagCompound(nbt);
		return nbt.getInteger("mythos_pool");
	}
	
	public int getMaxPool(ItemStack stack)
	{
		 NBTTagCompound nbt = stack.getTagCompound();
         if (nbt == null)
         {
         	nbt = new NBTTagCompound();
         }
         if (!nbt.hasKey("mythos_pool_max"))
         {
         	nbt.setInteger("mythos_pool_max", poolSize);
         }
         stack.setTagCompound(nbt);
		return nbt.getInteger("mythos_pool_max");
	}
	
	public void addToPool(ItemStack stack, int addToPool)
	{
		int currentPool = getCurrentPool(stack);
		int maxPool = getMaxPool(stack);
		NBTTagCompound nbt = stack.getTagCompound();
		nbt.setInteger("mythos_pool", currentPool + addToPool);
		stack.setTagCompound(nbt);
	}
	
	public void setPool(ItemStack stack, int setPool)
	{
		int currentPool = getCurrentPool(stack);
		int maxPool = getMaxPool(stack);
		NBTTagCompound nbt = stack.getTagCompound();
		nbt.setInteger("mythos_pool", setPool);
		stack.setTagCompound(nbt);
	}
	
	public void removeFromPool(ItemStack stack, int remove)
	{
		int currentPool = getCurrentPool(stack);
		int maxPool = getMaxPool(stack);
		NBTTagCompound nbt = stack.getTagCompound();
		nbt.setInteger("mythos_pool", currentPool - remove);
		stack.setTagCompound(nbt);
	}
	//Enchant glint when full
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) 
	{
		if (getCurrentPool(stack) == getMaxPool(stack))
		{
			stack.setTagInfo("ench", new NBTTagList());
			return true;
		}
		else
		{
			return false;
		}
	}
}
