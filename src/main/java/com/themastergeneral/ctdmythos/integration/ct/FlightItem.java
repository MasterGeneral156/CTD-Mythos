package com.themastergeneral.ctdmythos.integration.ct;

import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.ctdmythos.flightwand")
@ZenRegister
public class FlightItem {
	
	
	@ZenMethod
	public static void addFlightCatalyst(IItemStack input, int flightTime, int flightMultiplier, int flightResistance)
	{
		WandFlightItems.instance().addFlight(CraftTweakerMC.getItemStack(input), flightTime, flightMultiplier, flightResistance);
	}
	
	@ZenMethod
	public static void removeFlightCatalyst(IItemStack input)
	{
		WandFlightItems.instance().removeFlight(CraftTweakerMC.getItemStack(input));
	}
}
