package com.themastergeneral.ctdmythos.common.items.tools;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class TMGDrill extends MythosSwordBase
{

    public TMGDrill(String name)
    {
        super(ToolMaterial.IRON, name, 762);
        this.maxStackSize = 1;
    }
}
