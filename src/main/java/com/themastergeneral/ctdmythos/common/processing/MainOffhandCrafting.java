package com.themastergeneral.ctdmythos.common.processing;

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
	private final Map<ItemStack, ItemStack> mainHandList = Maps
			.<ItemStack, ItemStack> newHashMap();

	// Main Hand + Output
	private final Map<ItemStack, ItemStack> outputList = Maps
			.<ItemStack, ItemStack> newHashMap();

	public static MainOffhandCrafting instance() {
		return MAIN_OFF_HAND_ITEMS;
	}

	private MainOffhandCrafting() {
		this.addRecipe(new ItemStack(ModItems.crystal_fire, 1), new ItemStack(Blocks.TNT, 1), new ItemStack(ModItems.archeron_ingot, 1));
	}

	public void addRecipe(ItemStack mainhand, ItemStack offhand,
			ItemStack output) {
		this.addRecipeItem(mainhand, offhand, output);
	}

	public void addRecipeItem(ItemStack mainhand, ItemStack offhand,
			ItemStack output) {
		if (getRecipeResult(mainhand) != ItemStack.EMPTY) {
			CTDMythos.logger
					.error("Ignored flight item with conflicting input: "
							+ mainhand);
			return;
		}
		this.mainHandList.put(mainhand, offhand);
		this.outputList.put(mainhand, output);
	}

	public void removeRecipe(ItemStack input) {
		ItemStack result = getRecipeResult(input);
		if (result == ItemStack.EMPTY) {
			CTDMythos.logger.error("Could not remove: " + input
					+ " from the Main/Offhand registry as it doesn't exist.");
			return;
		}
		this.mainHandList.remove(input);
		this.outputList.remove(input);
	}

	public ItemStack getRecipeResult(ItemStack stack) {
		for (Entry<ItemStack, ItemStack> entry : this.outputList.entrySet()) {
			if (this.compareItemStacks(stack, (ItemStack) entry.getKey())) {
				return (ItemStack) entry.getValue();
			}
		}

		return ItemStack.EMPTY;
	}
	
	public ItemStack getRecipeOffhand(ItemStack stack) {
		for (Entry<ItemStack, ItemStack> entry : this.mainHandList.entrySet()) {
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

	public Map<ItemStack, ItemStack> getRecipeList() {
		return this.mainHandList;
	}

	public Map<ItemStack, ItemStack> getOutputList() {
		return this.outputList;
	}
}
