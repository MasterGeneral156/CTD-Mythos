package com.themastergeneral.ctdmythos.common.items.artifacts;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.misc.SingleStackItem;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;

public class MBValidatorItem extends SingleStackItem
{

    public MBValidatorItem(String name)
    {
        super(name);
    }

    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn,
            BlockPos pos, EnumHand handIn, EnumFacing facing, float hitX,
            float hitY, float hitZ)
    {
        validMultiblock(pos, worldIn, playerIn, true);
        return EnumActionResult.SUCCESS;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        par1ItemStack.setTagInfo("ench", new NBTTagList());
        return true;
    }

    // Add tooltip on client side.
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
            List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("Use on Pedestal to test multiblock.");
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
}
