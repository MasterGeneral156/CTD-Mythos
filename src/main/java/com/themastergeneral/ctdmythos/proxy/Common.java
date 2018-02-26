package com.themastergeneral.ctdmythos.proxy;

import java.io.File;

import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.events.MythosEventHandler;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.processing.Processing;
import com.themastergeneral.ctdmythos.integration.OreDict;
import com.themastergeneral.ctdmythos.server.world.WorldGen;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Common {
	public static Configuration config;

	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
		config = new Configuration(new File(directory.getPath(),
				"ctd/ctdmythos.cfg"));
		ModConfig.readConfig();
		ModItems.registerItems();
		ModBlocks.loadBlocks();
		GameRegistry.registerWorldGenerator(new WorldGen(), 0);
	}

	public void init(FMLInitializationEvent e) {
		MythosEventHandler.LoadEvents();
		Processing.initProcessing();
		OreDict.init();
	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
