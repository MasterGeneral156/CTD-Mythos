package com.themastergeneral.ctdmythos.common.config;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.proxy.Common;

public class ModConfig {
	private static final String CATEGORY_GENERAL = "General";
	private static final String CATEGORY_WORLDGEN = "World Gen";

	// Actual Config shit
	public static int DurabilityHumanEffigy = 5;
	public static int DurabilityShears = 25;
	public static int StoredLevels = 5;
	public static int reachAmuletRange = 10;
	public static int creativereachAmuletRange = 255;
	public static int bow_draw = 50;

	// World Gen configs
	public static int crystalSpawnMinY = 0;
	public static int crystalSpawnMaxY = 80;
	public static int crystalSpawnChance = 10;
	public static int crystalSpawnVeinSize = 4;

	public static void readConfig() {
		Configuration cfg = Common.config;
		try {
			cfg.load();
			initGeneralConfig(cfg);
		} catch (Exception e1) {
			CTDMythos.logger.log(Level.ERROR, "Problem loading config file!",
					e1);
		} finally {
			if (cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(CATEGORY_GENERAL,
				"General configuration for our mod.");

		cfg.addCustomCategoryComment(CATEGORY_WORLDGEN,
				"CTD Mythos World Generation.");

		// Config for maximum uses on Human Effigy
		DurabilityHumanEffigy = cfg.getInt("Human Effigy Max Uses",
				CATEGORY_GENERAL, DurabilityHumanEffigy, 1, 32766,
				"How many uses per effigy");
		DurabilityShears = cfg.getInt("Grim Shears Max Uses", CATEGORY_GENERAL,
				DurabilityShears, 1, 32766,
				"How many uses per pair of grim shears");
		StoredLevels = cfg.getInt("Levels stored in Tome of Knowledge",
				CATEGORY_GENERAL, StoredLevels, 1, 32766,
				"How many levels should be stored in a Tome of Knowledge");
		
		bow_draw = cfg.getInt("Longbow Draw Time",
				CATEGORY_GENERAL, bow_draw, 1, 32766,
				"How many ticks required before you can fire the longbow.");

		// Config for world generation
		crystalSpawnMinY = cfg.getInt(
				"Crysatllized Ore minimum Y spawn level.", CATEGORY_WORLDGEN,
				crystalSpawnMinY, 1, 254,
				"How low should Crystallized Ores spawn.");

		crystalSpawnMaxY = cfg.getInt(
				"Crysatllized Ore maximum Y spawn level.", CATEGORY_WORLDGEN,
				crystalSpawnMaxY, 2, 255,
				"How high should Crystallized Ores spawn.");

		crystalSpawnChance = cfg.getInt("Crystallized Ore Spawn rate",
				CATEGORY_WORLDGEN, crystalSpawnChance, 0, 16,
				"How often should Crystallized Ores spawn.");

		crystalSpawnVeinSize = cfg.getInt("Crystallized Ore Vein Size",
				CATEGORY_WORLDGEN, crystalSpawnVeinSize, 0, 16,
				"How big should Crystallized Ores spawn.");

		reachAmuletRange = cfg
				.getInt("Amulet of Reaching range?",
						CATEGORY_GENERAL,
						reachAmuletRange,
						5,
						65655,
						"How large should the player's range be when they equip the Amulet of Reaching? (Vanilla is 5)");
		creativereachAmuletRange = cfg
				.getInt("Creative Amulet of Reaching range?",
						CATEGORY_GENERAL,
						creativereachAmuletRange,
						5,
						65655,
						"How large should the player's range be when they equip the Creative Amulet of Reaching? (Vanilla is 5)");
	}
}
