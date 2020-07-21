package com.themastergeneral.ctdmythos.common.items.misc;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;

import com.themastergeneral.ctdcore.item.CTDItem;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.processing.LightningRecipes;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;
import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;

public class BaseItem extends CTDItem
{
	private Item containedBlock;
    public BaseItem(String name)
    {
        super(name, CTDMythos.MODID);
        this.setCreativeTab(CTDMythos.creativeTab);
    }
    //Can't move these for technical debt reasons.
    public static int getMythos(EntityPlayer playerIn)
	{
		return playerIn.getEntityData().getInteger("ctdmythos:mythos");
	}
	
	public static void setMythos(EntityPlayer playerIn, int mythos)
	{
		playerIn.getEntityData().setInteger("ctdmythos:mythos", mythos);
	}
	
	public boolean checkMythos(int currentMythos, int required)
	{
		return currentMythos >= required;
	}
	
	public static void removeMythos(EntityPlayer playerIn, int mythosChange)
	{
		int mythos = getMythos(playerIn);
		int newMythos = mythos - mythosChange;
		if (newMythos < 0)
			newMythos = 0;
		setMythos(playerIn, newMythos);
	}
	public static void addMythos(EntityPlayer playerIn, int mythosChange)
	{
		int mythos = getMythos(playerIn);
		int newMythos = mythos + mythosChange;
		if (newMythos > ModConfig.mythosMaxStorage)
			newMythos = ModConfig.mythosMaxStorage;
		setMythos(playerIn, newMythos);
	}
	/*public ActionResult<ItemStack> doInWorldLightningCraft(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		//Server side only
		if (!worldIn.isRemote)
        {
            ItemStack offhand = playerIn.getHeldItemOffhand();
            ItemStack mainhand = playerIn.getHeldItemMainhand();
            //Block we're looking for, but in item form
            Block blocktotest = LightningRecipes.instance().getRecipeItem(mainhand);
            
            //Check if player is crouching.
            if (playerIn.isSneaking())
            {
                boolean flag = this.containedBlock == blocktotest;
                RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, flag);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS,
                playerIn.getHeldItem(handIn));
	}*/
}
