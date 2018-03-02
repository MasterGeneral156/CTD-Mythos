package com.themastergeneral.ctdmythos.common.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.google.common.collect.Maps;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class MainOffhandCrafting {
	private static final MainOffhandCrafting MAIN_OFF_HAND_ITEMS = new MainOffhandCrafting();

	// Main Hand + Offhand
	private final Map<List<ItemStack>, ItemStack> mainHandList = Maps
			.<List<ItemStack>, ItemStack> newHashMap();

	public static MainOffhandCrafting instance() {
		return MAIN_OFF_HAND_ITEMS;
	}

	private MainOffhandCrafting() {
		this.addRecipe(new ItemStack(ModItems.crystal_fire, 1), new ItemStack(
				Blocks.TNT, 1), new ItemStack(ModItems.archeron_ingot, 1));
		this.addRecipe(new ItemStack(ModItems.crystal_oath, 1), new ItemStack(
				Items.BOOK, 1), new ItemStack(ModItems.xptome, 1));

		// Ore doubling
		this.addRecipe(new ItemStack(ModItems.crystal_woe, 1), new ItemStack(
				Blocks.IRON_ORE, 1), new ItemStack(Items.IRON_INGOT, 2));
		this.addRecipe(new ItemStack(ModItems.crystal_woe, 1), new ItemStack(
				Blocks.EMERALD_ORE, 1), new ItemStack(Items.EMERALD, 2));
		this.addRecipe(new ItemStack(ModItems.crystal_woe, 1), new ItemStack(
				Blocks.DIAMOND_ORE, 1), new ItemStack(Items.DIAMOND, 2));
		this.addRecipe(new ItemStack(ModItems.crystal_woe, 1), new ItemStack(
				Blocks.COAL_ORE, 1), new ItemStack(Items.COAL, 2));
		this.addRecipe(new ItemStack(ModItems.crystal_woe, 1), new ItemStack(
				Blocks.GOLD_ORE, 1), new ItemStack(Items.GOLD_INGOT, 2));
		this.addRecipe(new ItemStack(ModItems.crystal_woe, 1), new ItemStack(
				Blocks.REDSTONE_ORE, 1), new ItemStack(Items.REDSTONE, 2));
	}

	public void addRecipe(ItemStack mainhand, ItemStack offhand,
			ItemStack output) {
		this.addRecipeItem(mainhand, offhand, output);
	}

	public void addRecipeItem(ItemStack mainhand, ItemStack offhand,
			ItemStack output) {
		ArrayList list = new ArrayList<ItemStack>();
		list.add(mainhand);
		list.add(offhand);
		this.mainHandList.put(list, output);
	}

	public void removeRecipe(List<ItemStack> input) {
		ItemStack result = getRecipeResult(input);
		if (result == ItemStack.EMPTY) {
			CTDMythos.logger.error("Could not remove: " + input
					+ " from the Main/Offhand registry as it doesn't exist.");
			return;
		}
		this.mainHandList.remove(input);
	}

	public ItemStack getRecipeResult(List<ItemStack> input) {
		for (Entry<List<ItemStack>, ItemStack> entry : this.mainHandList
				.entrySet()) {
			ItemStack mainhand = input.get(0);
			ItemStack offhand = input.get(1);
				return (ItemStack) entry.getValue();
		}

		return ItemStack.EMPTY;
	}

	public Map<List<ItemStack>, ItemStack> getRecipeList() {
		return this.mainHandList;
	}
}
