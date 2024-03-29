package com.themastergeneral.ctdmythos;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.themastergeneral.ctdmythos.client.MythosTab;
import com.themastergeneral.ctdmythos.integration.imc.IMCHandler;
import com.themastergeneral.ctdmythos.network.PacketRequestUpdatePedestal;
import com.themastergeneral.ctdmythos.network.PacketUpdatePedestal;
import com.themastergeneral.ctdmythos.proxy.Common;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;

@Mod(modid = CTDMythos.MODID, name = CTDMythos.MODNAME, version = CTDMythos.VERSION, acceptedMinecraftVersions = CTDMythos.acceptedMinecraftVersions,
        updateJSON = CTDMythos.updateJSON, certificateFingerprint = CTDMythos.certificateFingerprint, dependencies = CTDMythos.DEPENDENCIES)
public class CTDMythos
{
    public static final String MODID = "ctdmythos";
    public static final String MODNAME = "CTD Mythos";
    public static final String VERSION = "0.9.4";
    public static final String updateJSON = "https://raw.githubusercontent.com/MasterGeneral156/Version/master/CTD-Mythos.json";
    public static final String acceptedMinecraftVersions = "1.12.2";
    public static final String certificateFingerprint = "b50fe22651b9d97d9d6631514a6a57cd6174b0dc";
    public static final String DEPENDENCIES = "required-after:baubles;required-after:ctdcore@[1.4.2,];";

    // Creative Tab
    public static final MythosTab creativeTab = new MythosTab();

    @Instance
    public static CTDMythos instance = new CTDMythos();

    @SidedProxy(clientSide = "com.themastergeneral.ctdmythos.proxy.Client", serverSide = "com.themastergeneral.ctdmythos.proxy.Server")
    public static Common proxy;
    public static Logger logger;
    public static SimpleNetworkWrapper wrapper;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        logger = e.getModLog();
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        wrapper.registerMessage(new PacketUpdatePedestal.Handler(), PacketUpdatePedestal.class, 0, Side.CLIENT);
        wrapper.registerMessage(new PacketRequestUpdatePedestal.Handler(), PacketRequestUpdatePedestal.class, 1, Side.SERVER);
        proxy.preInit(e);
        proxy.registerRenderers();
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit(e);
    }

    @EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent e)
    {
        FMLLog.warning("Invalid fingerprint detected for CTD Mythos! TheMasterGeneral will not support this version!");
    }
    
    //Needed for craft tweaker!
    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) 
    {
    	IMCHandler.INSTANCE.handleIMC(FMLInterModComms
				.fetchRuntimeMessages(this));
    }

    @EventHandler
    public void handleIMC(IMCEvent e)
    {
        logger.info("CTD Mythos is awaiting IMC from other mods...");
        IMCHandler.INSTANCE.handleIMC(e.getMessages());
    }
}
