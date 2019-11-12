package com.themastergeneral.ctdmythos.common.config;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.proxy.Common;

public class ModConfig {
	private static final String CATEGORY_GENERAL = "General";
	private static final String CATEGORY_WORLDGEN = "World Gen";
	private static final String CATEGORY_MYTHOS = "Mythos Balance";

	// Actual Config shit
	public static int DurabilityHumanEffigy = 5;
	public static int DurabilityShears = 25;
	public static int StoredLevels = 5;
	public static int reachAmuletRange = 10;
	public static int creativereachAmuletRange = 255;
	public static int bow_draw = 50;
	public static int bow_multiplier = 3;
	public static int heart_stack = 4;
	public static int wand_damage = 64;
	public static int mule_damage = 4;
	public static int talisman_damage = 5;
	public static int lightning_staff_damage = 5;
	public static boolean crystal_effects = true;
	public static boolean tmg_cheats = true;
	public static boolean ovk_dmg = false;

	// World Gen configs
	public static int crystalSpawnMinY = 0;
	public static int crystalSpawnMaxY = 80;
	public static int crystalSpawnChance = 10;
	public static int crystalSpawnVeinSize = 4;
	
	//Mythos configs
	public static int mythosMaxStorage = 4000;
	public static int mythosCostLightingStaff = 2000;

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
		
		cfg.addCustomCategoryComment(CATEGORY_MYTHOS,
				"Mythos-based config options.");

		// Config for maximum uses on Human Effigy
		DurabilityHumanEffigy = cfg.getInt("Human Effigy Max Uses",
				CATEGORY_GENERAL, DurabilityHumanEffigy, 1, 32766,
				"How many uses per effigy");
		wand_damage = cfg.getInt("Wands max use", CATEGORY_GENERAL,
				wand_damage, 1, 32766,
				"How many times can a player use a wand item before breaking");
		mule_damage = cfg.getInt("Mule's Kick max use", CATEGORY_GENERAL,
				mule_damage, 1, 32766,
				"How many times can a player use Mule's Kick before breaking");
		talisman_damage = cfg
				.getInt("Talisman of Evasion max use", CATEGORY_GENERAL,
						talisman_damage, 1, 32766,
						"How many times can a player use Talisman of Evasion before breaking");
		lightning_staff_damage = cfg
				.getInt("Staff of Lightning max use", CATEGORY_GENERAL,
						lightning_staff_damage, 1, 32766,
						"How many times can a player use the Staff of Lightning before breaking");
		DurabilityShears = cfg.getInt("Grim Shears Max Uses", CATEGORY_GENERAL,
				DurabilityShears, 1, 32766,
				"How many uses per pair of grim shears");
		StoredLevels = cfg.getInt("Levels stored in Tome of Knowledge",
				CATEGORY_GENERAL, StoredLevels, 1, 32766,
				"How many levels should be stored in a Tome of Knowledge");

		bow_draw = cfg.getInt("Longbow Draw Time", CATEGORY_GENERAL, bow_draw,
				1, 32766,
				"How many ticks required before you can fire the longbow.");

		bow_multiplier = cfg.getInt("Longbow Damage Multiplier",
				CATEGORY_GENERAL, bow_multiplier, 2, 10,
				"Normal bow damage multiplied by this number.");

		crystal_effects = cfg.getBoolean("Crystallized Gems Effects",
				CATEGORY_GENERAL, crystal_effects,
				"Should the effects while holding a crystal be active?");
		
		tmg_cheats = cfg.getBoolean("Developer cheaty mode",
				CATEGORY_GENERAL, tmg_cheats,
				"Should things be made easier for TheMasterGeneral while he's developing?");
		ovk_dmg = cfg.getBoolean("Lightning Staff Overkill Damage",
				CATEGORY_GENERAL, ovk_dmg,
				"Enables some things to make every hit a one hit, as intended by the mod author. This WILL likely be overkill.");

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

		heart_stack = cfg.getInt("Heart Charm max stack size.",
				CATEGORY_WORLDGEN, heart_stack, 1, 64,
				"How many Heart Charms can be in a stack, at maximum.");

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
		mythosMaxStorage = cfg.getInt("Mythos Max", CATEGORY_MYTHOS, mythosMaxStorage, 0, 20000000,
				"How much Mythos may a player stockpile?");
		mythosCostLightingStaff = cfg.getInt("Lightning Staff", CATEGORY_MYTHOS, mythosCostLightingStaff, 0, mythosMaxStorage,
						"Mythos Cost");
	}
}
