package com.themastergeneral.ctdmythos.common.blocks;

import net.minecraft.block.material.Material;

import com.themastergeneral.ctdcore.block.CTDBlock;
import com.themastergeneral.ctdmythos.CTDMythos;

public class BaseBlock extends CTDBlock
{

    public BaseBlock(Material materialIn, String name)
    {
        super(materialIn, name, CTDMythos.MODID);
        setCreativeTab(CTDMythos.creativeTab);
    }

}
