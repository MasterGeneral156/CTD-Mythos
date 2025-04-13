package mastergeneral156.ctdmythos;

import mastergeneral156.ctdmythos.blocks.BlockRegistry;
import mastergeneral156.ctdmythos.blocks.blockentity.BlockEntityRegistry;
import mastergeneral156.ctdmythos.items.ItemRegistry;
import mastergeneral156.ctdmythos.recipes.RecipeRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("ctdmythos")
public class MythosReborn {
	public static MythosReborn instance;
	public static final Logger LOGGER = LogManager.getLogger();

	public MythosReborn() {
		instance = this;
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        ItemRegistry.ITEMS.register(modbus);
        BlockRegistry.BLOCKS.register(modbus);
        RecipeRegistry.RECIPE_SERIALIZER.register(modbus);
        RecipeRegistry.RECIPE_TYPES.register(modbus);
        BlockEntityRegistry.TILES.register(modbus);
        MythosTab.CREATIVE_MODE_TABS.register(modbus);
        MythosNetworkManager.registerMessages();
    }
	
	private void setup(final FMLCommonSetupEvent event)
    {
		LOGGER.info("CTD Mythos is launching.");
    }

}