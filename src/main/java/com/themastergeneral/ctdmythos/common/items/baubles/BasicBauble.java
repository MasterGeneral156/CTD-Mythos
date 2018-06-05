package com.themastergeneral.ctdmythos.common.items.baubles;

import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

import net.minecraft.item.ItemStack;
import baubles.api.BaubleType;
import baubles.api.IBauble;

public class BasicBauble extends BaseItem implements IBauble
{

    public BasicBauble(String name)
    {
        super(name);
    }

    @Override
    public BaubleType getBaubleType(ItemStack arg0)
    {
        return BaubleType.TRINKET;
    }

}
