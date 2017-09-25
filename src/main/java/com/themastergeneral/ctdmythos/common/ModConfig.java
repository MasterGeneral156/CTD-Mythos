package com.themastergeneral.ctdmythos.common;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.proxy.Common;

public class ModConfig {
	private static final String CATEGORY_GENERAL = "General";

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

	}
}
