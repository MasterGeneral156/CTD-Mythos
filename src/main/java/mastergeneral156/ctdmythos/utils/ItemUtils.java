package mastergeneral156.ctdmythos.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ItemUtils {
    public static ResourceLocation getIconByStack(ItemStack stack)
    {
        return Minecraft.getInstance().getItemRenderer().getItemModelShaper().getItemModel(stack.getItem()).getParticleIcon().contents().name().withPrefix("textures/").withSuffix(".png");
    }
}
