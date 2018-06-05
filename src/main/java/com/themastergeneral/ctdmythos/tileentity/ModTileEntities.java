package com.themastergeneral.ctdmythos.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities
{

    public static void register()
    {
        GameRegistry.registerTileEntity(PedestalTileEntity.class,
                "ctdmythos:pedestal_block");
    }
}
