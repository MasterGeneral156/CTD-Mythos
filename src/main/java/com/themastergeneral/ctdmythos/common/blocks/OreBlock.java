package com.themastergeneral.ctdmythos.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class OreBlock extends BaseBlock {

	private Item drop;

	public OreBlock(Material materialIn, String name, String modid, Item droppeditem) {
		super(materialIn, name, modid);
		this.setHarvestLevel("shovel", 2);
        this.setHardness(1.0f);
		this.drop=droppeditem;
	}
	
	@Override
	public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
	    return this.drop;
	}

}
