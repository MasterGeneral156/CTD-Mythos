package com.themastergeneral.ctdmythos.integration.jei;

import net.minecraft.item.ItemStack;

import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.integration.jei.flightwand.FlightWandCategory;
import com.themastergeneral.ctdmythos.integration.jei.flightwand.FlightWandMaker;
import com.themastergeneral.ctdmythos.integration.jei.mainoff.MainOffCategory;
import com.themastergeneral.ctdmythos.integration.jei.mainoff.MainOffMaker;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public class JEICTDMythos implements IModPlugin {
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

		registry.addRecipeCategories(new FlightWandCategory(guiHelper),
				new MainOffCategory(guiHelper));
	}

	@Override
	public void register(IModRegistry registry) {
		final IIngredientRegistry ingredientRegistry = registry
				.getIngredientRegistry();
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();

		registry.addRecipes(FlightWandMaker.getFlightItems(jeiHelpers),
				"ctdmythos.flight_wand_items");

		registry.addRecipeCatalyst(new ItemStack(ModItems.flight_wand),
				"ctdmythos.flight_wand_items");

		registry.addRecipes(MainOffMaker.getFlightItems(jeiHelpers),
				"ctdmythos.mainoff_recipes");

		registry.addRecipeCatalyst(new ItemStack(ModItems.crystal_fire),
				"ctdmythos.mainoff_recipes");
		registry.addRecipeCatalyst(new ItemStack(ModItems.crystal_woe),
				"ctdmythos.mainoff_recipes");
		registry.addRecipeCatalyst(new ItemStack(ModItems.crystal_oath),
				"ctdmythos.mainoff_recipes");
		registry.addRecipeCatalyst(new ItemStack(ModItems.crystal_memory),
				"ctdmythos.mainoff_recipes");
	}
}
