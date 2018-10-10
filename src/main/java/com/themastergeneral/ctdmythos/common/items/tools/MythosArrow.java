package com.themastergeneral.ctdmythos.common.items.tools;

import com.themastergeneral.ctdcore.item.CTDArrow;
import com.themastergeneral.ctdmythos.CTDMythos;

public class MythosArrow extends CTDArrow
{

    public MythosArrow(String name, String modid)
    {
        super(name, modid);
        this.setCreativeTab(CTDMythos.creativeTab);
    }

}
