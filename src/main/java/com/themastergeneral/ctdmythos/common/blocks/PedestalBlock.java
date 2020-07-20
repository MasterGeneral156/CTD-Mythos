package com.themastergeneral.ctdmythos.common.blocks;

import java.text.NumberFormat;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import com.themastergeneral.ctdcore.block.CTDTEBase;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;
import com.themastergeneral.ctdmythos.tileentity.PedestalTileEntity;

public class PedestalBlock extends CTDTEBase<PedestalTileEntity>
{

    protected static final AxisAlignedBB SOUL_SAND_AABB = new AxisAlignedBB(
            0.0625D, 0.0D, 0.0625D, 0.9375D, 0.8125D, 0.9375D);

    public PedestalBlock(String name)
    {
        super(Material.ROCK, name, CTDMythos.MODID);
        this.setHardness(10F);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CTDMythos.creativeTab);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            PedestalTileEntity tile = getTileEntity(world, pos);
            ItemStack heldItem = player.getHeldItem(hand);
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
            if (!player.isSneaking())
            {
                if (heldItem.isEmpty())
                {
                    player.setHeldItem(hand,itemHandler.extractItem(0, 64, false));
                    tile.resetTicks();
                }
                else
                {
                    player.setHeldItem(hand,itemHandler.insertItem(0, heldItem, false));
                    tile.resetTicks();
                }
                tile.markDirty();
            }
            else
            {
                ItemStack stack = itemHandler.getStackInSlot(0);
                if (tile.validItem())
                {
                	TextComponentTranslation message = new TextComponentTranslation("pedestal.progress.info");
                	message.appendSibling(new TextComponentString(NumberFormat.getInstance().format(tile.getTicks())));
                	message.appendText(" / ");
                	message.appendSibling(new TextComponentString(NumberFormat.getInstance().format(200 * tile.inventory.getStackInSlot(0).getCount())));
                	message.appendSibling(new TextComponentTranslation("pedestal.progress.ticks"));
                    player.sendStatusMessage(message,true);
                }
                else
                {
                    player.sendStatusMessage(new TextComponentTranslation("pedestal.invalid"),true);
                }
            }
        }
        return true;
    }

    @Override
    public Class<PedestalTileEntity> getTileEntityClass()
    {
        return PedestalTileEntity.class;
    }

    @Nullable
    @Override
    public PedestalTileEntity createTileEntity(World world, IBlockState state)
    {
        return new PedestalTileEntity();
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state)
    {
        PedestalTileEntity tile = getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (!stack.isEmpty())
        {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntity(item);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState,
            IBlockAccess worldIn, BlockPos pos)
    {
        return SOUL_SAND_AABB;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn,
            BlockPos pos, Random rand)
    {
        PedestalTileEntity tile = getTileEntity(worldIn, pos);
        if (tile.getTicks() > 0)
        {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            if (rand.nextDouble() < 0.1D)
            {
                worldIn.playSound((double) pos.getX() + 0.5D,
                        (double) pos.getY(), (double) pos.getZ() + 0.5D,
                        ModSounds.multiblock_random, SoundCategory.BLOCKS,
                        1.0F, 1.0F, false);
            }
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1,  d2 + 0.52D, 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
        }
    }
}
