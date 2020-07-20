package com.themastergeneral.ctdmythos.common.processing;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class LightningRecipes 
{
	/*private static final LightningRecipes LIGHTNING_RECIPES = new LightningRecipes();
	private final HashMap<ItemStack, Block> recipeList = new HashMap();
	private final HashMap<ItemStack, Block> outputList = new HashMap();

	public static LightningRecipes  instance() {
		return LIGHTNING_RECIPES;
	}
	
	private LightningRecipes() 
	{
		addRecipe(new ItemStack(ModItems.crystal_fire), Blocks.BRICK_BLOCK, ModBlocks.crystal_fire_brick);
		addRecipe(new ItemStack(ModItems.crystal_woe), Blocks.BRICK_BLOCK, ModBlocks.crystal_woe_brick);
		addRecipe(new ItemStack(ModItems.crystal_oath), Blocks.BRICK_BLOCK, ModBlocks.crystal_oath_brick);
		addRecipe(new ItemStack(ModItems.crystal_grief), Blocks.BRICK_BLOCK, ModBlocks.crystal_grief_brick);
		addRecipe(new ItemStack(ModItems.crystal_memory), Blocks.BRICK_BLOCK, ModBlocks.crystal_memory_brick);
	}
	public void addRecipe(ItemStack mainhand, Block block, Block output) 
	{
		addRecipeItem(mainhand, block, output);
	}
	public void addRecipeItem(ItemStack mainhand, Block block, Block output) 
	{
		outputList.put(mainhand, output);
		outputList.put(mainhand, block);
	}
	public Block getRecipeItem(ItemStack stack)
    {
        for (Entry<ItemStack, Block> entry : this.recipeList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return entry.getKey().get;
            }
        }
        return ItemStack.EMPTY;
    }
	
	public ItemStack getOutputItem(ItemStack stack)
    {
        for (Entry<ItemStack, Block> entry : this.outputList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack) entry.getKey()))
            {
                return (ItemStack) entry.getKey();
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1 .getMetadata());
    }

    public HashMap<ItemStack, Block> getRecipeList()
    {
        return this.recipeList;
    }
    public HashMap<ItemStack, Block> getOutputList()
    {
        return this.outputList;
    }
    public void removeRecipe(ItemStack input)
    {
        ItemStack result = getRecipeItem(input);
        if (result == ItemStack.EMPTY)
        {
            CTDMythos.logger.error("Could not remove: " + input + " from the Lightning Crafting registry as it doesn't exist.");
            return;
        }
        this.outputList.remove(input);
        this.recipeList.remove(input);
    }*/
}
