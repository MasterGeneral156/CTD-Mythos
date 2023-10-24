package themastergeneral.mythosreborn;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import themastergeneral.mythosreborn.items.ItemRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("mythosreborn")
public class MythosReborn {
	public static MythosReborn instance;
	private static final Logger LOGGER = LogManager.getLogger();

	public MythosReborn() {
		instance = this;
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        ItemRegistry.ITEMS.register(modbus);
    }
	
	private void setup(final FMLCommonSetupEvent event)
    {
		LOGGER.info("Mythos Reborn is launching.");
    }

}