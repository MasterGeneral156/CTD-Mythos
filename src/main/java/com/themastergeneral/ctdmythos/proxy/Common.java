package com.themastergeneral.ctdmythos.proxy;

import java.io.File;

import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.events.MythosEventHandler;
import com.themastergeneral.ctdmythos.common.items.ModItems;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Common {
	public static Configuration config;

	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
		config = new Configuration(new File(directory.getPath(),
				"ctd/ctdmythos.cfg"));
		ModConfig.readConfig();
		ModItems.registerItems();
	}

	public void init(FMLInitializationEvent e) {
		MythosEventHandler.LoadEvents();
	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
