package com.themastergeneral.ctdmythos.common.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OreBlock extends BaseBlock
{

    private Item drop;

    public OreBlock(String name, Item droppeditem)
    {
        super(Material.GROUND, name);
        this.setHarvestLevel("pickaxe", 2);
        this.setSoundType(SoundType.STONE);
        this.setHardness(1.0f);
        this.drop = droppeditem;
    }

    @Override
    public Item getItemDropped(IBlockState blockstate, Random random,
            int fortune)
    {
        return this.drop;
    }
}
