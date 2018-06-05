package com.themastergeneral.ctdmythos.common.processing;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.Map.Entry;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.google.common.collect.Maps;

public class WandFlightItems
{
    private static final WandFlightItems FLIGHT_ITEMS = new WandFlightItems();

    // Flight Item + Flight Time
    private final Map<ItemStack, Integer> flightItemsList = Maps
            .<ItemStack, Integer> newHashMap();

    // Flight Item + Flight Multiplier
    private final Map<ItemStack, Integer> flightMultiList = Maps
            .<ItemStack, Integer> newHashMap();

    // Flight Item + Resistance Time
    private final Map<ItemStack, Integer> flightResistList = Maps
            .<ItemStack, Integer> newHashMap();

    public static WandFlightItems instance()
    {
        return FLIGHT_ITEMS;
    }

    private WandFlightItems()
    {
        this.addFlight(new ItemStack(Items.DIAMOND), 300, 1, 360);
        this.addFlight(new ItemStack(Items.EMERALD), 400, 0, 480);
        this.addFlight(new ItemStack(Items.IRON_INGOT), 80, 0, 120);
        this.addFlight(new ItemStack(Items.GOLD_INGOT), 40, 3, 70);

        this.addFlight(new ItemStack(Blocks.DIAMOND_BLOCK), 3000, 1, 3300);
        this.addFlight(new ItemStack(Blocks.EMERALD_BLOCK), 4000, 0, 4400);
        this.addFlight(new ItemStack(Blocks.IRON_BLOCK), 800, 0, 860);
        this.addFlight(new ItemStack(Blocks.GOLD_BLOCK), 400, 3, 500);

        this.addFlight(new ItemStack(Items.IRON_NUGGET), 8, 0, 12);
        this.addFlight(new ItemStack(Items.GOLD_NUGGET), 4, 3, 7);
    }

    public void addFlight(ItemStack input, int flighttime,
            int flightmultiplier, int resistancetime)
    {
        this.addFlightItem(input, flighttime, flightmultiplier, resistancetime);
    }

    public void addFlightItem(ItemStack input, int flighttime,
            int flightmultiplier, int resistancetime)
    {
        if (getFlightItem(input) != ItemStack.EMPTY)
        {
            CTDMythos.logger
                    .error("Ignored flight item with conflicting input: "
                            + input);
            return;
        }
        this.flightItemsList.put(input, flighttime);
        this.flightMultiList.put(input, flightmultiplier);
        this.flightResistList.put(input, resistancetime);
    }

    public ItemStack getFlightItem(ItemStack stack)
    {
        for (Entry<ItemStack, Integer> entry : this.flightItemsList.entrySet())
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
        return stack2.getItem() == stack1.getItem()
                && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1
                        .getMetadata());
    }

    public Map<ItemStack, Integer> getFlightItemsList()
    {
        return this.flightItemsList;
    }

    public int getFlightTime(ItemStack stack)
    {
        for (Entry<ItemStack, Integer> entry : this.flightItemsList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack) entry.getKey()))
            {
                return (entry.getValue()).intValue();
            }
        }
        return 0;
    }

    public int getFlightMultiplier(ItemStack stack)
    {
        for (Entry<ItemStack, Integer> entry : this.flightMultiList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack) entry.getKey()))
            {
                return (entry.getValue()).intValue();
            }
        }
        return 0;
    }

    public int getFlightResistance(ItemStack stack)
    {
        for (Entry<ItemStack, Integer> entry : this.flightResistList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack) entry.getKey()))
            {
                return (entry.getValue()).intValue();
            }
        }
        return 0;
    }

    public void removeFlight(ItemStack input)
    {
        ItemStack result = getFlightItem(input);
        if (result == ItemStack.EMPTY)
        {
            CTDMythos.logger.error("Could not remove: " + input
                    + " from the Flight Wand registry as it doesn't exist.");
            return;
        }
        this.flightItemsList.remove(input);
        this.flightMultiList.remove(input);
        this.flightResistList.remove(input);
    }
}
