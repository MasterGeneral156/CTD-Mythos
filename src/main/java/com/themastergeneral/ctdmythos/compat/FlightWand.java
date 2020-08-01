package com.themastergeneral.ctdmythos.compat;

import javax.annotation.Nullable;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;
import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.ctdmythos.FlightWand")
@ZenRegister
public class FlightWand 
{
	@ZenMethod
    public static void addRecipe(IItemStack input, int flightTime, @Nullable int flightMultiplier, @Nullable int resistTime) 
	{
		WandFlightItems.instance().addFlight(InputHelper.toStack(input), flightTime, flightMultiplier, resistTime);
	}
	
	@ZenMethod
    public static void removeRecipe(IItemStack input) 
	{
		WandFlightItems.instance().removeFlight(InputHelper.toStack(input));
	}
}
