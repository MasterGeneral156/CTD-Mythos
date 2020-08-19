package com.themastergeneral.ctdmythos.common.blocks;

import java.text.NumberFormat;

import com.themastergeneral.ctdcore.block.CTDTEBase;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.mythos.MythosItemBase;
import com.themastergeneral.ctdmythos.tileentity.MythosBaseTileEntity;
import com.themastergeneral.ctdmythos.tileentity.PedestalTileEntity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class MythosTEBlock extends CTDTEBase<MythosBaseTileEntity>
{
	protected int storage;
	public MythosTEBlock(String name, int mythosStorage) 
	{
		super(Material.ANVIL, name, CTDMythos.MODID);
        this.setHardness(10F);
        this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CTDMythos.creativeTab);
        this.storage = mythosStorage;
	}

	@Override
	public Class<MythosBaseTileEntity> getTileEntityClass() {
		return MythosBaseTileEntity.class;
	}

	@Override
	public MythosBaseTileEntity createTileEntity(World world, IBlockState state) {
		return new MythosBaseTileEntity();
	}
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
        	ItemStack heldItem = player.getHeldItem(hand);
        	MythosBaseTileEntity tile = getTileEntity(world, pos);
        	CTDMythos.logger.info(heldItem);
        	if (validMythosItem(heldItem))
        	{
        		int changeRate = MythosItemBase.getChangeRate(heldItem);
	        	if (player.isSneaking())
	        	{
	        		if (tile.getCurrentPool() >= changeRate)
	        		{
	        			if ((MythosItemBase.getCurrentPool(heldItem) + changeRate) <= (MythosItemBase.getMaxPool(heldItem)))
	        			{
	        				MythosItemBase.addToPool(heldItem, changeRate);
	        				tile.removeFromPool(changeRate);
	        			}
	        		}
	        	}
	        	else
	        	{
	        		if (MythosItemBase.getCurrentPool(heldItem) >= changeRate)
	        		{
	        			if ((tile.getCurrentPool() + changeRate) <= tile.getMaxPool())
	        			{
	        				MythosItemBase.removeFromPool(heldItem, changeRate);
	        				tile.addToPool(changeRate);
	        			}
	        		}
	        	}
        	}
        	else
        	{
    			TextComponentTranslation message = new TextComponentTranslation("Mythos Stored: ");
            	message.appendSibling(new TextComponentString(NumberFormat.getInstance().format(tile.getCurrentPool())));
            	message.appendText("/");
            	message.appendSibling(new TextComponentString(NumberFormat.getInstance().format(tile.getMaxPool())));
                player.sendStatusMessage(message,true);
        	}
        }
        return true;
    }
	
	public boolean validMythosItem(ItemStack stack)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		if (stack.isEmpty())
        	return false;
		else if (!nbt.hasKey("mythos_pool_max"))
        	return false;
        else if (!nbt.hasKey("mythos_pool"))
        	return false;
        else
        	return true;
    }
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
		CTDMythos.logger.info("test");
		if (!worldIn.isRemote)
        {
			MythosBaseTileEntity tile = getTileEntity(worldIn, pos);
			tile.setMaxPool(storage);
        }
    }
}
