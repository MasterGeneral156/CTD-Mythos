package com.themastergeneral.ctdmythos.common.processing;

import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class MultiblockRecipes {
	private static final MultiblockRecipes MULTIBLOCK_RECIPES = new MultiblockRecipes();
	private final Multimap<ItemStack, ItemStack> recipeList = ArrayListMultimap
			.create();

	public static MultiblockRecipes instance() {
		return MULTIBLOCK_RECIPES;
	}

	private MultiblockRecipes() {
		addRecipe(new ItemStack(ModItems.humansoul), new ItemStack(
				ModItems.humaneffigy));
		// Ore Doubling
		this.addRecipe(new ItemStack(Blocks.IRON_ORE, 1), new ItemStack(
				Items.IRON_INGOT, 2));
		this.addRecipe(new ItemStack(Blocks.GOLD_ORE, 1), new ItemStack(
				Items.GOLD_INGOT, 2));
		this.addRecipe(new ItemStack(Blocks.REDSTONE_ORE, 1), new ItemStack(
				Items.REDSTONE, 2));
		this.addRecipe(new ItemStack(Blocks.DIAMOND_ORE, 1), new ItemStack(
				Items.DIAMOND, 2));
		this.addRecipe(new ItemStack(Blocks.EMERALD_ORE, 1), new ItemStack(
				Items.EMERALD, 2));
		this.addRecipe(new ItemStack(Blocks.COAL_ORE, 1), new ItemStack(
				Items.COAL, 2));
		this.addRecipe(new ItemStack(Blocks.QUARTZ_ORE, 1), new ItemStack(
				Items.QUARTZ, 2));
		addRecipe(new ItemStack(ModBlocks.crystal_fire_ore), new ItemStack(
				ModItems.crystal_fire, 4));
		addRecipe(new ItemStack(ModBlocks.crystal_woe_ore), new ItemStack(
				ModItems.crystal_woe, 4));
		addRecipe(new ItemStack(ModBlocks.crystal_memory_ore), new ItemStack(
				ModItems.crystal_memory, 4));
		addRecipe(new ItemStack(ModBlocks.crystal_grief_ore), new ItemStack(
				ModItems.crystal_grief, 4));
		addRecipe(new ItemStack(ModBlocks.crystal_oath_ore), new ItemStack(
				ModItems.crystal_oath, 4));
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
