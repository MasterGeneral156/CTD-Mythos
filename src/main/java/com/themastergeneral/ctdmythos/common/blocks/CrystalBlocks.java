package com.themastergeneral.ctdmythos.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CrystalBlocks extends BaseBlock
{

    public CrystalBlocks(String name)
    {
        super(Material.GRASS, name);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(5.0F);
        this.setResistance(5.0F);
        this.setHarvestLevel("pickaxe", 3);
    }

}
