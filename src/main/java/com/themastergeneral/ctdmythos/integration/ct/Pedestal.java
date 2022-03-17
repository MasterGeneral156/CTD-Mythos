package com.themastergeneral.ctdmythos.integration.ct;

import javax.annotation.Nullable;

import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.ctdmythos.pedestal")
@ZenRegister
public class Pedestal {

	@ZenMethod
    public static void addRecipe(IItemStack input, IItemStack output) 
	{
		MultiblockRecipes.instance().addRecipe(CraftTweakerMC.getItemStack(input), CraftTweakerMC.getItemStack(output));
	}
	
	@ZenMethod
	public static void removeRecipe(IItemStack input, IItemStack output)
	{
		MultiblockRecipes.instance().removeRecipe(CraftTweakerMC.getItemStack(input), CraftTweakerMC.getItemStack(output));
	}
	
	@ZenMethod
	public static void clearAllRecipes()
	{
		MultiblockRecipes.instance().getRecipeList().clear();
	}
}
