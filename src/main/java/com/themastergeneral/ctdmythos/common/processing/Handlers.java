package com.themastergeneral.ctdmythos.common.processing;

import com.themastergeneral.ctdmythos.integration.EnderfugeIMC;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Handlers
{

    public static void addCrystalRecipe(ItemStack input, ItemStack output,
            float xp)
    {
        // Enderfuge stuff if its available? /shrug.
        EnderfugeIMC.addEnderfugeSmelt(input, new ItemStack(output.getItem(), output.getCount()*3, output.getMetadata()), xp);
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addEnderfugeRecipe(ItemStack input, ItemStack output)
    {
        EnderfugeIMC.addEnderfugeSmelt(input, output, 0F);
    }
}
