package com.themastergeneral.ctdmythos.common.config;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.proxy.Common;

public class ModConfig {
	private static final String CATEGORY_GENERAL = "General";

	// Actual Config shit
	public static int DurabilityHumanEffigy = 5;
	public static int DurabilityShears = 5;

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

		// Config for maximum uses on Human Effigy
		DurabilityHumanEffigy = cfg.getInt("Human Effigy Max Uses",
				CATEGORY_GENERAL, DurabilityHumanEffigy, 1, 32766,
				"How many uses per effigy");
		DurabilityShears = cfg.getInt("Grim Shears Max Uses", CATEGORY_GENERAL,
				DurabilityShears, 1, 32766,
				"How many uses per pair of grim shears");
	}
}
