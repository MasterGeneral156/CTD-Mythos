package com.themastergeneral.ctdmythos.integration.jei.mainoff;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Lists;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;
import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;

public class MainOffJEI implements IRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public MainOffJEI(List<ItemStack> inputs, ItemStack output2) {
		this.inputs = Collections.singletonList(inputs);
		this.output = output2;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(ItemStack.class, Lists.newArrayList(inputs));
		ingredients.setOutput(ItemStack.class, output);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		MainOffhandCrafting furnaceRecipes = MainOffhandCrafting.instance();
	}
}
