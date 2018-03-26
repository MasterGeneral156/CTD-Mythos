package com.themastergeneral.ctdmythos.common.processing;

import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class MultiblockRecipes {
	private static final MultiblockRecipes MULTIBLOCK_RECIPES = new MultiblockRecipes();
	private final Multimap<ItemStack, ItemStack> recipeList = ArrayListMultimap
			.create();

	public static MultiblockRecipes instance() {
		return MULTIBLOCK_RECIPES;
	}

	private MultiblockRecipes() {
		addRecipe(new ItemStack(ModItems.humansoul), new ItemStack(ModItems.humaneffigy));
	}

	public void addRecipe(ItemStack mainhand, ItemStack output) {
		this.addRecipeItem(mainhand, output);
	}

	public void addRecipeItem(ItemStack mainhand, ItemStack output) {
		this.recipeList.put(mainhand, output);
	}

	public void removeRecipe(ItemStack input, ItemStack output) {
		ItemStack result = getRecipeResult(input);
		if (result == ItemStack.EMPTY) {
			CTDMythos.logger.error("Could not remove: " + input
					+ " from the Multiblock registry as it doesn't exist.");
			return;
		}
		this.recipeList.remove(input, output);
	}

	public ItemStack getRecipeResult(ItemStack stack) {
		for (Entry<ItemStack, ItemStack> entry : this.recipeList.entries()) {
			if (this.compareItemStacks(stack, (ItemStack) entry.getKey())) {
				return (ItemStack) entry.getValue();
			}
		}

		return ItemStack.EMPTY;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem()
				&& (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1
						.getMetadata());
	}

	public Multimap<ItemStack, ItemStack> getRecipeList() {
		return this.recipeList;
	}
}
