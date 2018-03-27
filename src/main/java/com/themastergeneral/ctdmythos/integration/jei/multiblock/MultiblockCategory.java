package com.themastergeneral.ctdmythos.integration.jei.multiblock;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.integration.jei.CTDMythosJEICategory;
import com.themastergeneral.ctdmythos.integration.jei.mainoff.MainOffJEI;

public class MultiblockCategory extends CTDMythosJEICategory<MultiblockJEI> {
	private final IDrawable background;
	private final String localizedName;
	ResourceLocation backgroundLocation = new ResourceLocation("ctdmythos",
			"textures/gui/gui_jei_2.png");

	public MultiblockCategory(IGuiHelper guiHelper) {
		super(guiHelper);
		background = guiHelper.createDrawable(backgroundLocation, 0, 168, 125,
				18, 0, 20, 0, 0);
		localizedName = "Multiblock Crafting";
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
		return "ctdmythos.multiblock_recipes";
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MultiblockJEI recipeWrapper,
			IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 0, 0);
		guiItemStacks.init(1, true, 49, 0);
		guiItemStacks.init(2, false, 107, 0);

		guiItemStacks.set(ingredients);
	}

}
