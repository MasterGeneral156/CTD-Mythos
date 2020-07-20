package com.themastergeneral.ctdmythos.common.items.crystals;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

public class CrystallizedOath extends BaseItem
{

    private Block containedBlock;

    public CrystallizedOath(String name)
    {
        super(name);
    }

    // TODO: Wrap crafting check into seperate method.
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn,
            EntityPlayer playerIn, EnumHand handIn)
    {
        if (!worldIn.isRemote)
        {
            ItemStack offhand = playerIn.getHeldItemOffhand();
            ItemStack mainhand = playerIn.getHeldItemMainhand();
            if (playerIn.isSneaking())
            {
                Block blocktotest = Blocks.BRICK_BLOCK;
                boolean flag = this.containedBlock == blocktotest;
                RayTraceResult raytraceresult = this.rayTrace(worldIn,
                        playerIn, flag);
                if (raytraceresult == null)
                {
                    return new ActionResult(EnumActionResult.PASS, mainhand);
                }
                else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
                {
                    return new ActionResult(EnumActionResult.PASS, mainhand);
                }
                else
                {
                    BlockPos blockpos = raytraceresult.getBlockPos();
                    if (!worldIn.isBlockModifiable(playerIn, blockpos))
                    {
                        return new ActionResult(EnumActionResult.FAIL, mainhand);
                    }
                    if (!playerIn.canPlayerEdit(
                            blockpos.offset(raytraceresult.sideHit),
                            raytraceresult.sideHit, mainhand))
                    {
                        return new ActionResult(EnumActionResult.FAIL, mainhand);
                    }
                    else
                    {
                        IBlockState iblockstate = worldIn
                                .getBlockState(blockpos);
                        if (iblockstate == blocktotest.getDefaultState())
                        {
                            worldIn.setBlockState(blockpos,
                                    ModBlocks.crystal_oath_brick
                                            .getDefaultState(), 11);
                            EntityLightningBolt lightning = new EntityLightningBolt(
                                    worldIn, blockpos.getX(), blockpos.getY(),
                                    blockpos.getZ(), false);
                            worldIn.addWeatherEffect(lightning);
                            mainhand.shrink(1);
                            return new ActionResult(EnumActionResult.PASS,
                                    mainhand);
                        }
                        else
                        {
                            return new ActionResult(EnumActionResult.FAIL,
                                    mainhand);
                        }
                    }
                }
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS,
                playerIn.getHeldItem(handIn));
    }
}
