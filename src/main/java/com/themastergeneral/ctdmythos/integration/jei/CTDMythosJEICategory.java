package com.themastergeneral.ctdmythos.integration.jei;

import net.minecraft.util.ResourceLocation;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

public abstract class CTDMythosJEICategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {
	protected static final int inputSlot = 0;
	protected static final int offHand = 1;
	protected static final int outputSlot = 2;

	public CTDMythosJEICategory(IGuiHelper guiHelper) {
		ResourceLocation backgroundLocation = new ResourceLocation("ctdmythos", "textures/gui/gui_jei.png");
	}
}
