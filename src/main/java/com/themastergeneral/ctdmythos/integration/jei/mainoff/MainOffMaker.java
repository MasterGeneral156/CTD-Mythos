package com.themastergeneral.ctdmythos.integration.jei.mainoff;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;
import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;
import com.themastergeneral.ctdmythos.integration.jei.flightwand.FlightWandJEI;

public class MainOffMaker {

	private MainOffMaker() {
	}

	public static List<MainOffJEI> getFlightItems(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		MainOffhandCrafting furnaceRecipes = MainOffhandCrafting.instance();
		Map<List<ItemStack>, ItemStack> smeltingMap = furnaceRecipes.getRecipeList();


		List<MainOffJEI> recipes = new ArrayList<>();

		for (Entry<List<ItemStack>, ItemStack> entry : smeltingMap.entrySet()) {
			List<ItemStack> mainhand = entry.getKey();
			ItemStack offhand3 = entry.getValue();
			ItemStack output = furnaceRecipes.getRecipeResult(mainhand);

			MainOffJEI recipe = new MainOffJEI(mainhand, output);
			recipes.add(recipe);
		}

		return recipes;
	}
}
