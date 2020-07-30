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
		CTDMythos.LATE_ADDITIONS.add(new Add(InputHelper.toStack(input), InputHelper.toStack(output)));
	}
	
	@ZenMethod
    public static void removeRecipe(IItemStack input, IItemStack output) 
	{
		CTDMythos.LATE_ADDITIONS.add(new Remove(InputHelper.toStack(input), InputHelper.toStack(output)));
	}
	
	private static class Add extends BaseAction 
	{ 
        private ItemStack output;
        private ItemStack input;
        
        public Add(ItemStack input, ItemStack output) 
        {
            super("Pedestal");
            this.output = output;
            this.input = input;
        }
        
        @Override
        public void apply() 
        {
        	MultiblockRecipes.instance().addRecipe(input, output);
        }
    }
	private static class Remove extends BaseAction 
	{
        private ItemStack input;
        private ItemStack output;
        
        public Remove(ItemStack input, ItemStack output) {
            super("Pedestal");
            this.input = input;
            this.input = output;
        }
        
        @Override
        public void apply() 
        {
           MultiblockRecipes.instance().removeRecipe(input, output);
        }
    }
	
}
