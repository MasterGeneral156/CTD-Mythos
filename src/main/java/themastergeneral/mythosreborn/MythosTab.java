package themastergeneral.mythosreborn;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import themastergeneral.mythosreborn.items.ItemConstants;

public class MythosTab {

public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "mythosreborn");
	
	public static final RegistryObject<CreativeModeTab> MYTHOS_TAB = CREATIVE_MODE_TABS.register("mythos_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> new ItemStack(ItemConstants.mythos_diviner))
            .title(Component.translatable("itemgroup.mythosreborn.tab"))
            .displayItems((parameters, ev) -> {
            	ev.accept(ItemConstants.mythos_diviner);
            	ev.accept(new ItemStack(ItemConstants.mythos_refractor));
            	ev.accept(new ItemStack(ItemConstants.mythos_conductor));
            	
            	ev.accept(ItemConstants.crystal_grief);
                ev.accept(ItemConstants.ore_crystal_grief);
            	
                ev.accept(ItemConstants.crystal_fire);
                ev.accept(ItemConstants.ore_crystal_fire);
                
                ev.accept(ItemConstants.crystal_memory);
                ev.accept(ItemConstants.ore_crystal_memory);
                
                ev.accept(ItemConstants.crystal_oath);
                ev.accept(ItemConstants.ore_crystal_oath);
                
                ev.accept(ItemConstants.crystal_woe);
                ev.accept(ItemConstants.ore_crystal_woe);
            }).build());
}
