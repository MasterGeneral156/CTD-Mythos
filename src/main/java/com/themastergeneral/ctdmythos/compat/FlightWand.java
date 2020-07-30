package com.themastergeneral.ctdmythos.compat;

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
    public static void addRecipe(IItemStack input, int flightTime, int flightMultiplier, int resistTime) 
	{
		CTDMythos.LATE_ADDITIONS.add(new Add(InputHelper.toStack(input), flightTime, flightMultiplier, resistTime));
	}
	
	@ZenMethod
    public static void addRecipe(IItemStack input, int flightTime, int flightMultiplier) 
	{
		CTDMythos.LATE_ADDITIONS.add(new Add(InputHelper.toStack(input), flightTime, flightMultiplier, 0));
	}
	
	@ZenMethod
    public static void addRecipe(IItemStack input, int flightTime) 
	{
		CTDMythos.LATE_ADDITIONS.add(new Add(InputHelper.toStack(input), flightTime, 0, 0));
	}
	
	@ZenMethod
    public static void removeRecipe(IItemStack input) 
	{
		CTDMythos.LATE_ADDITIONS.add(new Remove(InputHelper.toStack(input)));
	}
	private static class Add extends BaseAction 
	{ 
        private ItemStack input;
        private int flightTime;
        private int flightMultiplier;
        private int resistTime;
        
        public Add(ItemStack input, int flightTime, int flightMultiplier, int resistTime) 
        {
            super("WandFlightItem");
            this.input = input;
            this.flightTime = flightTime;
            this.flightMultiplier = flightMultiplier;
            this.resistTime = resistTime;
        }
        
        @Override
        public void apply() 
        {
        	WandFlightItems.instance().addFlight(input, flightTime, flightMultiplier, resistTime);
        }
    }
	private static class Remove extends BaseAction 
	{
        private ItemStack input;
        
        public Remove(ItemStack input) {
            super("WandFlightItem");
            this.input = input;
        }
        
        @Override
        public void apply() 
        {
           WandFlightItems.instance().removeFlight(input);
        }
    }
}
