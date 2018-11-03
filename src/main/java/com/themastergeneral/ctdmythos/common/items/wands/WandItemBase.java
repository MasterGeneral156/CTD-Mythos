package com.themastergeneral.ctdmythos.common.items.wands;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;

public class WandItemBase extends BaseItem
{

    public WandItemBase(String name)
    {
        super(name);
        this.maxStackSize = 1;
        this.setMaxDamage(ModConfig.wand_damage + 1);
    }

}
