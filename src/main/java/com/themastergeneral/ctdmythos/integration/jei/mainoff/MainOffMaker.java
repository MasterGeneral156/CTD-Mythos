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
		Map<ItemStack, ItemStack> smeltingMap = furnaceRecipes.getRecipeList();

		List<MainOffJEI> recipes = new ArrayList<>();

		for (Entry<ItemStack, ItemStack> entry : smeltingMap.entrySet()) {
			ItemStack mainhand = entry.getKey();
			ItemStack offhand = MainOffhandCrafting.instance().getRecipeResult(mainhand);
			ItemStack output = entry.getKey();
			

			List<ItemStack> inputs = stackHelper.getSubtypes(mainhand);
			List<ItemStack> outputs = stackHelper.getSubtypes(output);
			MainOffJEI recipe = new MainOffJEI(inputs, outputs, offhand);
			recipes.add(recipe);
		}

		return recipes;
	}
}
