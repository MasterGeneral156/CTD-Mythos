package com.themastergeneral.ctdmythos.common.processing;

import com.themastergeneral.ctdmythos.integration.EnderfugeIMC;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Handlers {

	public static void addCrystalRecipe(ItemStack input, ItemStack output,
			float xp) {
		// Enderfuge stuff if its available? /shrug.
		if (Loader.isModLoaded("enderfuge")) {
			EnderfugeIMC.addEnderfugeSmelt(input, output, xp);
		} else {
			GameRegistry.addSmelting(input, output, xp);
		}
	}
}
