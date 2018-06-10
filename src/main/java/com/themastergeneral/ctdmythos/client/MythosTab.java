package com.themastergeneral.ctdmythos.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class MythosTab extends CreativeTabs
{

    public MythosTab()
    {
        super(CTDMythos.MODID);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ModBlocks.pedestal_block);
    }

    @Override
    public boolean hasSearchBar()
    {
        return false;
    }

}
