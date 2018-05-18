package com.themastergeneral.ctdmythos.common.processing;

import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

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
		// Ore Doubling
		addRecipe(new ItemStack(Blocks.IRON_ORE, 1), new ItemStack(
				Items.IRON_INGOT, 2));
		addRecipe(new ItemStack(Blocks.GOLD_ORE, 1), new ItemStack(
				Items.GOLD_INGOT, 2));
		addRecipe(new ItemStack(Blocks.REDSTONE_ORE, 1), new ItemStack(
				Items.REDSTONE, 2));
		addRecipe(new ItemStack(Blocks.DIAMOND_ORE, 1), new ItemStack(
				Items.DIAMOND, 2));
		addRecipe(new ItemStack(Blocks.EMERALD_ORE, 1), new ItemStack(
				Items.EMERALD, 2));
		addRecipe(new ItemStack(Blocks.COAL_ORE, 1), new ItemStack(Items.COAL,
				2));
		addRecipe(new ItemStack(Blocks.QUARTZ_ORE, 1), new ItemStack(
				Items.QUARTZ, 2));
		addRecipe(new ItemStack(ModBlocks.crystal_fire_ore), new ItemStack(
				ModItems.crystal_fire, 2));
		addRecipe(new ItemStack(ModBlocks.crystal_woe_ore), new ItemStack(
				ModItems.crystal_woe, 2));
		addRecipe(new ItemStack(ModBlocks.crystal_memory_ore), new ItemStack(
				ModItems.crystal_memory, 2));
		addRecipe(new ItemStack(ModBlocks.crystal_grief_ore), new ItemStack(
				ModItems.crystal_grief, 2));
		addRecipe(new ItemStack(ModBlocks.crystal_oath_ore), new ItemStack(
				ModItems.crystal_oath, 2));
		addRecipe(new ItemStack(Blocks.LAPIS_ORE), new ItemStack(Items.DYE, 2,
				4));
		addRecipe(new ItemStack(Blocks.WEB, 2), new ItemStack(
				ModItems.ethereal_fiber, 8));

		// Archeron Ingot
		addRecipe(new ItemStack(Blocks.TNT, 4), new ItemStack(
				ModItems.archeron_ingot, 4));

		addRecipe(new ItemStack(Items.TOTEM_OF_UNDYING), new ItemStack(
				ModItems.humansoul, 4));

		addRecipe(new ItemStack(Items.KNOWLEDGE_BOOK), new ItemStack(
				ModItems.xptome));

		addRecipe(new ItemStack(Items.STICK), new ItemStack(
				ModItems.enchanted_stick));

		addRecipe(new ItemStack(Items.ENCHANTED_BOOK, 1,
				OreDictionary.WILDCARD_VALUE), new ItemStack(ModItems.xptome));
	}

	public void addRecipe(ItemStack mainhand, ItemStack output) {
		addRecipeItem(mainhand, output);
	}

	public void addRecipeItem(ItemStack mainhand, ItemStack output) {
		recipeList.put(mainhand, output);
	}

	public void removeRecipe(ItemStack input, ItemStack output) {
		ItemStack result = getRecipeResult(input);
		if (result == ItemStack.EMPTY) {
			CTDMythos.logger.error("Could not remove: " + input
					+ " from the Multiblock registry as it doesn't exist.");
			return;
		}
		recipeList.remove(input, output);
	}

	public ItemStack getRecipeResult(ItemStack stack) {
		for (Entry<ItemStack, ItemStack> entry : recipeList.entries()) {
			if (compareItemStacks(stack, (ItemStack) entry.getKey())) {
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
		return recipeList;
	}
}
