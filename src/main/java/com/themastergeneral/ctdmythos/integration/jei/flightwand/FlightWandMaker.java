package com.themastergeneral.ctdmythos.integration.jei.flightwand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

import com.themastergeneral.ctdmythos.common.processing.WandFlightItems;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;

public class FlightWandMaker
{

    private FlightWandMaker()
    {
    }

    public static List<FlightWandJEI> getFlightItems(IJeiHelpers helpers)
    {
        IStackHelper stackHelper = helpers.getStackHelper();
        WandFlightItems furnaceRecipes = WandFlightItems.instance();
        Map<ItemStack, Integer> smeltingMap = furnaceRecipes
                .getFlightItemsList();

        List<FlightWandJEI> recipes = new ArrayList<>();

        for (Entry<ItemStack, Integer> entry : smeltingMap.entrySet())
        {
            ItemStack input = entry.getKey();
            Integer output = entry.getValue();

            List<ItemStack> inputs = stackHelper.getSubtypes(input);
            FlightWandJEI recipe = new FlightWandJEI(inputs, output);
            recipes.add(recipe);
        }

        return recipes;
    }
}
