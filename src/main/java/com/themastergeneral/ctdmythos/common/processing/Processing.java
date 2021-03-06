package com.themastergeneral.ctdmythos.common.processing;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class Processing
{
    public static void initProcessing()
    {
        Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_fire_ore),
                new ItemStack(ModItems.crystal_fire, 1), 0F);
        Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_woe_ore),
                new ItemStack(ModItems.crystal_woe, 1), 0F);
        Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_memory_ore),
                new ItemStack(ModItems.crystal_memory, 1), 0F);
        Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_grief_ore),
                new ItemStack(ModItems.crystal_grief, 1), 0F);
        Handlers.addCrystalRecipe(new ItemStack(ModBlocks.crystal_oath_ore),
                new ItemStack(ModItems.crystal_oath, 1), 0F);

        // Enderfuge only
        Handlers.addEnderfugeRecipe(new ItemStack(ModItems.grimshears, 1,
                OreDictionary.WILDCARD_VALUE), new ItemStack(
                ModItems.archeron_ingot));

        Handlers.addEnderfugeRecipe(new ItemStack(ModItems.gladius_sword, 1,
                OreDictionary.WILDCARD_VALUE), new ItemStack(
                ModItems.archeron_ingot));
    }

}
