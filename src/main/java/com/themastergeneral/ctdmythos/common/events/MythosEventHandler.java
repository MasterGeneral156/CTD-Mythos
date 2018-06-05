package com.themastergeneral.ctdmythos.common.events;

import net.minecraftforge.common.MinecraftForge;

public class MythosEventHandler
{
    public static void LoadEvents()
    {
        MinecraftForge.EVENT_BUS.register(new CowBloodDropEvent());
    }
}
