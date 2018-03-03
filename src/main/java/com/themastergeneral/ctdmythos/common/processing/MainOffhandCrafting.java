package com.themastergeneral.ctdmythos.common.processing;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class MainOffhandCrafting {
	private static final MainOffhandCrafting MAIN_OFF_HAND_ITEMS = new MainOffhandCrafting();

	// Main Hand + Offhand
	/*private final Map<ItemStack, ItemStack> mainHandList = Maps
			.<ItemStack, ItemStack> newHashMap();*/
	private final Multimap <ItemStack, ItemStack> mainHandList = ArrayListMultimap.create();

	// Main Hand + Output
	/*private final Map<ItemStack, ItemStack> outputList = Maps
			.<ItemStack, ItemStack> newHashMap();*/
	
	private final Multimap <ItemStack, ItemStack> outputList = ArrayListMultimap.create();

	public static MainOffhandCrafting instance() {
		return MAIN_OFF_HAND_ITEMS;
	}

	private MainOffhandCrafting() {
		this.addRecipe(new ItemStack(ModItems.crystal_fire, 1), new ItemStack(
				Blocks.TNT, 1), new ItemStack(ModItems.archeron_ingot, 1));
		this.addRecipe(new ItemStack(ModItems.crystal_oath, 1), new ItemStack(
				Items.BOOK, 1), new ItemStack(ModItems.xptome, 1));
		//Ore Doubling
		this.addRecipe(new ItemStack(ModItems.crystal_woe, 1), new ItemStack(
				Blocks.IRON_ORE, 1), new ItemStack(Items.IRON_INGOT, 2));
		this.addRecipe(new ItemStack(ModItems.crystal_woe, 1), new ItemStack(
				Blocks.GOLD_ORE, 1), new ItemStack(Items.GOLD_INGOT, 2));
	}

	public void addRecipe(ItemStack mainhand, ItemStack offhand,
			ItemStack output) {
		this.addRecipeItem(mainhand, offhand, output);
	}

	public void addRecipeItem(ItemStack mainhand, ItemStack offhand,
			ItemStack output) {
		this.mainHandList.put(mainhand, offhand);
		this.outputList.put(mainhand, output);
	}

	public void removeRecipe(ItemStack input, ItemStack offhand, ItemStack output) {
		ItemStack result = getRecipeResult(input);
		if (result == ItemStack.EMPTY) {
			CTDMythos.logger.error("Could not remove: " + input
					+ " from the Main/Offhand registry as it doesn't exist.");
			return;
		}
		this.mainHandList.remove(input,offhand);
		this.outputList.remove(input,output);
	}

	public ItemStack getRecipeResult(ItemStack stack) {
		for (Entry<ItemStack, ItemStack> entry : this.outputList.entries()) {
			if (this.compareItemStacks(stack, (ItemStack) entry.getKey())) {
				return (ItemStack) entry.getValue();
			}
		}

		return ItemStack.EMPTY;
	}

	public ItemStack getRecipeOffhand(ItemStack stack) {
		for (Entry<ItemStack, ItemStack> entry : this.mainHandList.entries()) {
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
		return this.mainHandList;
	}

	public Multimap<ItemStack, ItemStack> getOutputList() {
		return this.outputList;
	}
}
