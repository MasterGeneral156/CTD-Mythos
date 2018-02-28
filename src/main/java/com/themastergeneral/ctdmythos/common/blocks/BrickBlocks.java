package com.themastergeneral.ctdmythos.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BrickBlocks extends BaseBlock {

	public BrickBlocks(String name) {
		super(Material.GROUND, name);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setSoundType(SoundType.STONE);
		this.setHarvestLevel("pickaxe", 0);
	}

}
