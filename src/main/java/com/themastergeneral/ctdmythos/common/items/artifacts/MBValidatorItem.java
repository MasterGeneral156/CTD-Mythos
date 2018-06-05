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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        tooltip.add("Use on Crystallized Fire Block to test multiblock.");
    }
}
