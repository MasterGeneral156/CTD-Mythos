package com.themastergeneral.ctdmythos;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

import com.themastergeneral.ctdmythos.client.MythosTab;
import com.themastergeneral.ctdmythos.proxy.Common;

@Mod(modid = CTDMythos.MODID, name = CTDMythos.MODNAME, version = CTDMythos.VERSION, dependencies = CTDMythos.DEPENDENCIES, updateJSON = CTDMythos.updateJSON, acceptedMinecraftVersions = CTDMythos.MCVersion)
public class CTDMythos {
	public static final String MODID = "ctdmythos";
	public static final String MODNAME = "CTD Mythos";
	public static final String VERSION = "0.0.2";
	public static final String DEPENDENCIES = "required-after:ctdcore@[1.1.8,];";
	public static final String updateJSON = "https://raw.githubusercontent.com/MasterGeneral156/Version/master/CTD-Mythos.json";
	public static final String MCVersion = "1.12.2";

	// Creative Tab
	public static final MythosTab creativeTab = new MythosTab();

	@Instance
	public static CTDMythos instance = new CTDMythos();

	@SidedProxy(clientSide = "com.themastergeneral.ctdmythos.proxy.Client", serverSide = "com.themastergeneral.ctdmythos.proxy.Server")
	public static Common proxy;
	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}
