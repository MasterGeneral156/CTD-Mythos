package com.themastergeneral.ctdmythos.common.processing;

import com.themastergeneral.ctdcore.imc.IMCHelper;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Handlers
{

    public static void addCrystalRecipe(ItemStack input, ItemStack output,
            float xp)
    {
        // Enderfuge stuff if its available? /shrug.
        addEnderfugeRecipe(input, new ItemStack(output.getItem(), output.getCount()*2, output.getMetadata()), xp);
        GameRegistry.addSmelting(input, output, xp);
    }

    public static void addEnderfugeRecipe(ItemStack input, ItemStack output)
    {
        addEnderfugeRecipe(input, output, 0F);
    }
    
    public static void addEnderfugeRecipe(ItemStack input, ItemStack output, float xp)
    {
        IMCHelper.addEnderfugeSmelt(input, output, xp);
    }
}
