package com.themastergeneral.ctdmythos.integration.jei.multiblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Multimap;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;
import com.themastergeneral.ctdmythos.integration.jei.mainoff.MainOffJEI;

public class MultiblockMaker {
	private MultiblockMaker() {
	}

	public static List<MultiblockJEI> getFlightItems(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		MultiblockRecipes furnaceRecipes = MultiblockRecipes.instance();
		Multimap<ItemStack, ItemStack> smeltingMap = furnaceRecipes
				.getRecipeList();

		List<MultiblockJEI> recipes = new ArrayList<>();

		for (Entry<ItemStack, ItemStack> entry : smeltingMap.entries()) {
			ItemStack mainhand = entry.getKey();
			ItemStack output = furnaceRecipes.getRecipeResult(mainhand);

			List<ItemStack> inputs = stackHelper.getSubtypes(mainhand);
			MultiblockJEI recipe = new MultiblockJEI(inputs, output);
			recipes.add(recipe);
		}

		return recipes;
	}
}
