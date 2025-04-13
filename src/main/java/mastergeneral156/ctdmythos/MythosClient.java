package mastergeneral156.ctdmythos;

import mastergeneral156.ctdmythos.blocks.BlockConstants;
import mastergeneral156.ctdmythos.blocks.BlockRegistry;
import mastergeneral156.ctdmythos.blocks.blockentity.BlockEntityRegistry;
import mastergeneral156.ctdmythos.client.render.blockentity.MythosAltarRenderer;
import mastergeneral156.ctdmythos.client.render.blockentity.MythosPedestalRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = "ctdmythos", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MythosClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        MythosReborn.LOGGER.info("Loading custom block entity renderers");

        event.registerBlockEntityRenderer(BlockEntityRegistry.mythos_pedestal.get(), MythosPedestalRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.mythos_altar.get(), MythosAltarRenderer::new);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        MythosReborn.LOGGER.info("Loading other client side stuffs");

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.mythos_pedestal.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.mythos_altar.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.mythos_pylon.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.mythos_weather_pylon.get(), RenderType.translucent());
    }
}
