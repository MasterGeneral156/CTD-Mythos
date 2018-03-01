package com.themastergeneral.ctdmythos.integration.jei.flightwand;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class FlightWandJEI implements IRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final Integer output;

	public FlightWandJEI(List<ItemStack> inputs, Integer output2) {
		this.inputs = Collections.singletonList(inputs);
		this.output = output2;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutput(ItemStack.class, output);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		WandFlightItems furnaceRecipes = WandFlightItems.instance();
		String experienceString = "Flight: " + output + " ticks.";
		FontRenderer fontRenderer = minecraft.fontRenderer;
		int stringWidth = fontRenderer.getStringWidth(experienceString);
		fontRenderer.drawString(experienceString, recipeWidth - stringWidth, 0, Color.gray.getRGB());
	}
}
