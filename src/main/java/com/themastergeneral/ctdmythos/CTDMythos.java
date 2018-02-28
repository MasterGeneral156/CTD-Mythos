package com.themastergeneral.ctdmythos;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

import com.themastergeneral.ctdmythos.client.MythosTab;
import com.themastergeneral.ctdmythos.proxy.Common;

@Mod(modid = CTDMythos.MODID, name = CTDMythos.MODNAME, version = CTDMythos.VERSION, acceptedMinecraftVersions = CTDMythos.acceptedMinecraftVersions, updateJSON = CTDMythos.updateJSON, certificateFingerprint = CTDMythos.certificateFingerprint, dependencies = CTDMythos.DEPENDENCIES)
public class CTDMythos {
	public static final String MODID = "ctdmythos";
	public static final String MODNAME = "CTD Mythos";
	public static final String VERSION = "0.0.19";
	public static final String updateJSON = "https://raw.githubusercontent.com/MasterGeneral156/Version/master/CTD-Mythos.json";
	public static final String acceptedMinecraftVersions = "1.12.2";
	public static final String certificateFingerprint = "1cd8befc36d6dedc5601d77a013f43afc71f899f";
	public static final String DEPENDENCIES = "required-after:ctdcore@[1.2.4,];";

	// Creative Tab
	public static final MythosTab creativeTab = new MythosTab();

	@Instance
	public static CTDMythos instance = new CTDMythos();

	@SidedProxy(clientSide = "com.themastergeneral.ctdmythos.proxy.Client", serverSide = "com.themastergeneral.ctdmythos.proxy.Server")
	public static Common proxy;
	public static Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		logger = e.getModLog();
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

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent e) {
		FMLLog.warning("Invalid fingerprint detected for CTD Mythos! TheMasterGeneral will not support this version!");
	}
}
