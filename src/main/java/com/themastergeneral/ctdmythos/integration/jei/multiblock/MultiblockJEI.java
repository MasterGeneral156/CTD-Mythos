package com.themastergeneral.ctdmythos.integration.jei.multiblock;

import java.util.Collections;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Lists;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;

public class MultiblockJEI implements IRecipeWrapper
{
    private final List<List<ItemStack>> inputs;
    private final ItemStack output;

    public MultiblockJEI(List<ItemStack> inputs, ItemStack output2)
    {
        this.inputs = Collections.singletonList(inputs);
        this.output = output2;
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInputs(ItemStack.class, Lists.newArrayList(inputs));
        ingredients.setOutput(ItemStack.class, output);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth,
            int recipeHeight, int mouseX, int mouseY)
    {
        MultiblockRecipes furnaceRecipes = MultiblockRecipes.instance();
    }

}
