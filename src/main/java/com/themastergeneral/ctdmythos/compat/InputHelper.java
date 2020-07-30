package com.themastergeneral.ctdmythos.compat;

import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;

public class InputHelper 
{
	public static ItemStack toStack(IItemStack iStack) 
	{
        if(iStack == null) 
        {
            return ItemStack.EMPTY;
        } 
        else 
        {
            Object internal = iStack.getInternal();           
            return (ItemStack) internal;
        }
    }
}
