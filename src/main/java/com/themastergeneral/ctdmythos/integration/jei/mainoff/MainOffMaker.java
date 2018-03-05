package com.themastergeneral.ctdmythos.integration.jei.mainoff;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Multimap;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;
import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;
import com.themastergeneral.ctdmythos.integration.jei.flightwand.FlightWandJEI;

public class MainOffMaker {

	private MainOffMaker() {
	}

	public static List<MainOffJEI> getFlightItems(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		MainOffhandCrafting furnaceRecipes = MainOffhandCrafting.instance();
		Multimap<ItemStack, ItemStack> smeltingMap = furnaceRecipes
				.getRecipeList();

		Multimap<ItemStack, ItemStack> outputMap = furnaceRecipes
				.getOutputList();

		List<MainOffJEI> recipes = new ArrayList<>();

		for (Entry<ItemStack, ItemStack> entry : smeltingMap.entries()) {
			ItemStack mainhand = entry.getKey();
			ItemStack offhand = furnaceRecipes.getRecipeOffhand(mainhand);
			ItemStack output = furnaceRecipes.getRecipeResult(mainhand);

			List<ItemStack> inputs = stackHelper.getSubtypes(mainhand);
			List<ItemStack> offhands = stackHelper.getSubtypes(offhand);
			MainOffJEI recipe = new MainOffJEI(inputs, offhands, output);
			recipes.add(recipe);
		}

		return recipes;
	}
}
