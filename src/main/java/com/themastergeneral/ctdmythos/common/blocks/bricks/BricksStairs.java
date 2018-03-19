package com.themastergeneral.ctdmythos.common.blocks.bricks;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.BlockRenderRegister;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;

public class BricksStairs extends BlockStairs implements ItemModelProvider,
		BlockRenderRegister {

	private Block modelBlock;
	private IBlockState modelState;
	protected String name; // Block's registry name

	public BricksStairs(String name, IBlockState modelState) {
		super(modelState);
		this.setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.NORTH)
				.withProperty(HALF, BlockStairs.EnumHalf.BOTTOM)
				.withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT));
		this.modelBlock = modelState.getBlock();
		this.modelState = modelState;
		this.setHardness(2.0F);
		this.name = name;
		this.setResistance(10.0F);
		this.setSoundType(SoundType.STONE);
		this.setHarvestLevel("pickaxe", 0);
		this.setLightOpacity(255);
		this.setCreativeTab(CTDMythos.creativeTab);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	// Get the block's model.
	@Override
	public void reg(Block block) {
		Minecraft
				.getMinecraft()
				.getRenderItem()
				.getItemModelMesher()
				.register(
						Item.getItemFromBlock(block),
						0,
						new ModelResourceLocation(CTDMythos.MODID + ":"
								+ block.getUnlocalizedName().substring(5),
								"inventory"));
	}

	// Get the block's item model.
	@Override
	public void registerItemModel(Item itemBlock) {
		CTDCore.proxy.registerItemRenderer(CTDMythos.MODID, itemBlock, 0, name);
	}

}
