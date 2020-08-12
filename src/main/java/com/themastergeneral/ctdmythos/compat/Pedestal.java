package com.themastergeneral.ctdmythos.compat;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.ctdmythos.Pedestal")
@ZenRegister
public class Pedestal 
{
	@ZenMethod
    public static void addRecipe(IItemStack input, IItemStack output) 
	{
		MultiblockRecipes.instance().addRecipe(InputHelper.toStack(input), InputHelper.toStack(output));
	}
	
	@ZenMethod
    public static void removeRecipe(IItemStack input, IItemStack output) 
	{
		MultiblockRecipes.instance().removeRecipe(InputHelper.toStack(input), InputHelper.toStack(output));
	}
	
	@ZenMethod
    public static void removeAllRecipes() 
	{
		MultiblockRecipes.instance().getRecipeList().clear();
	}
	
}
