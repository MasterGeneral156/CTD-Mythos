package com.themastergeneral.ctdmythos.integration.jei.flightwand;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.integration.jei.CTDMythosJEICategory;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class FlightWandCategory extends CTDMythosJEICategory<FlightWandJEI> {
	private final IDrawable background;
	private final String localizedName;
	ResourceLocation backgroundLocation = new ResourceLocation("ctdmythos", "textures/gui/gui_jei.png");

	public FlightWandCategory(IGuiHelper guiHelper) {
		super(guiHelper);
		background = guiHelper.createDrawable(backgroundLocation, 0, 114, 116, 20);
		localizedName = "Levitation Wand Catalysts";
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {
	}

	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public String getModName() {
		return CTDMythos.MODNAME;
	}

	@Override
	public String getUid() {
		return "ctdmythos.flight_wand_items";
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FlightWandJEI recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(inputSlot, true, 0, 0);

		guiItemStacks.set(ingredients);
	}
}
