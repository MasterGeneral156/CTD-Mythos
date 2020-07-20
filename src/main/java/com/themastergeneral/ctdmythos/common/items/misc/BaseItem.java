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
import com.themastergeneral.ctdmythos.common.effects.EffectUtils;
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

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
            int itemSlot, boolean isSelected)
    {
        EntityPlayer playerIn = (EntityPlayer) entityIn;
        ItemStack offhand = playerIn.getHeldItemOffhand();
        if (ModConfig.crystal_effects)
        {
	        // Blindness effect with grief
	        if ((stack.getItem() == ModItems.crystal_grief)
	                && (offhand.getItem() != ModItems.crystal_glove))
	        {
	            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
	                    MobEffects.BLINDNESS, 20, 0, true, false));
	        }
	        // Nausea effect with memory
	        if ((stack.getItem() == ModItems.crystal_memory)
	                && (offhand.getItem() != ModItems.crystal_glove))
	        {
	            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
	                    MobEffects.WEAKNESS, 20, 2, true, false));
	        }
	        // Slowness effect with Woe
	        if ((stack.getItem() == ModItems.crystal_woe)
	                && (offhand.getItem() != ModItems.crystal_glove))
	        {
	            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
	                    MobEffects.SLOWNESS, 20, 2, true, false));
	        }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn,
            EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack offhand = playerIn.getHeldItemOffhand();
        ItemStack mainhand = playerIn.getHeldItemMainhand();
        // Crystalized Memory to repair the item in the user's offhand, at
        // the cost of one Crystalized Memory.
        if (mainhand.getItem() == ModItems.crystal_memory)
        {
            if (offhand != ItemStack.EMPTY)
            {
                if (offhand.isItemDamaged())
                {
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

    public static boolean validMultiblock(BlockPos pos, World world,
            EntityPlayer player, boolean output)
    {
        if (!world.isRemote)
        {
            BlockPos startpos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            BlockPos underpos = new BlockPos(pos.getX(), pos.getY() - 1,
                    pos.getZ());
            BlockPos northpos = new BlockPos(pos.getX() + 1, pos.getY() - 1,
                    pos.getZ());
            BlockPos southpos = new BlockPos(pos.getX() - 1, pos.getY() - 1,
                    pos.getZ());
            BlockPos eastpos = new BlockPos(pos.getX(), pos.getY() - 1,
                    pos.getZ() + 1);
            BlockPos westpos = new BlockPos(pos.getX(), pos.getY() - 1,
                    pos.getZ() - 1);
            if (world.getBlockState(startpos).getBlock() == ModBlocks.pedestal_block)
            {
                if (world.getBlockState(underpos).getBlock() == ModBlocks.crystal_fire_brick)
                {
                    if (world.getBlockState(northpos).getBlock() == ModBlocks.crystal_fire_brick)
                    {
                        if (world.getBlockState(southpos).getBlock() == ModBlocks.crystal_fire_brick)
                        {
                            if (world.getBlockState(eastpos).getBlock() == ModBlocks.crystal_fire_brick)
                            {
                                if (world.getBlockState(westpos).getBlock() == ModBlocks.crystal_fire_brick)
                                {
                                    if (output)
                                    {
                                        player.sendStatusMessage(new TextComponentString(
                                                "Valid multiblock."), true);
                                    }
                                    return true;
                                }
                                else
                                {
                                    if (output)
                                    {
                                        player.sendStatusMessage(new TextComponentString(
                                                "Expecting " + ModBlocks.crystal_fire_brick
                                                                .getLocalizedName()
                                                        + " at X: "
                                                        + westpos.getX()
                                                        + ", Y: "
                                                        + westpos.getY()
                                                        + ", Z: "
                                                        + westpos.getZ()
                                                        + " but got "
                                                        + world.getBlockState(
                                                                westpos)
                                                                .getBlock()
                                                                .getLocalizedName()), true);
                                    }
                                    return false;
                                }
                            }
                            else
                            {
                                if (output)
                                {
                                    player.sendStatusMessage(new TextComponentString(
                                            "Expecting "
                                                    + ModBlocks.crystal_fire_brick
                                                            .getLocalizedName()
                                                    + " at X: "
                                                    + eastpos.getX()
                                                    + ", Y: "
                                                    + eastpos.getY()
                                                    + ", Z: "
                                                    + eastpos.getZ()
                                                    + " but got "
                                                    + world.getBlockState(
                                                            eastpos).getBlock()
                                                            .getLocalizedName()), true);
                                }
                                return false;
                            }
                        }
                        else
                        {
                            if (output)
                            {
                                player.sendStatusMessage(new TextComponentString(
                                        "Expecting "
                                                + ModBlocks.crystal_fire_brick
                                                        .getLocalizedName()
                                                + " at X: "
                                                + southpos.getX()
                                                + ", Y: "
                                                + southpos.getY()
                                                + ", Z: "
                                                + southpos.getZ()
                                                + " but got "
                                                + world.getBlockState(southpos)
                                                        .getBlock()
                                                        .getLocalizedName()), true);
                            }
                            return false;
                        }
                    }
                    else
                    {
                        if (output)
                        {
                            player.sendStatusMessage(new TextComponentString(
                                    "Expecting "
                                            + ModBlocks.crystal_fire_brick
                                                    .getLocalizedName()
                                            + " at X: "
                                            + northpos.getX()
                                            + ", Y: "
                                            + northpos.getY()
                                            + ", Z: "
                                            + northpos.getZ()
                                            + " but got "
                                            + world.getBlockState(northpos)
                                                    .getBlock()
                                                    .getLocalizedName()), true);
                        }
                        return false;
                    }
                }
                else
                {
                    if (output)
                    {
                        player.sendStatusMessage(new TextComponentString("Expecting "
                                + ModBlocks.crystal_fire_brick
                                        .getLocalizedName()
                                + " at X: "
                                + underpos.getX()
                                + ", Y: "
                                + underpos.getY()
                                + ", Z: "
                                + underpos.getZ()
                                + " but got "
                                + world.getBlockState(underpos).getBlock()
                                        .getLocalizedName()), true);
                    }
                    return false;
                }
            }
            else
            {
                if (output)
                {
                    player.sendStatusMessage(new TextComponentString("Expecting "
                            + ModBlocks.pedestal_block.getLocalizedName()
                            + " at X: "
                            + startpos.getX()
                            + ", Y: "
                            + startpos.getY()
                            + ", Z: "
                            + startpos.getZ()
                            + " but got "
                            + world.getBlockState(startpos).getBlock()
                                    .getLocalizedName()), true);
                }
                return false;
            }
        }
        else
            return false;
    }
    public int getMythos(EntityPlayer playerIn)
	{
		return playerIn.getEntityData().getInteger("ctdmythos:mythos");
	}
	
	public void setMythos(EntityPlayer playerIn, int mythos)
	{
		playerIn.getEntityData().setInteger("ctdmythos:mythos", mythos);
	}
	
	public boolean checkMythos(int currentMythos, int required)
	{
		return currentMythos >= required;
	}
	
	public void removeMythos(EntityPlayer playerIn, int mythosChange)
	{
		int mythos = getMythos(playerIn);
		int newMythos = mythos - mythosChange;
		if (newMythos < 0)
			newMythos = 0;
		setMythos(playerIn, newMythos);
	}
	public void addMythos(EntityPlayer playerIn, int mythosChange)
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
