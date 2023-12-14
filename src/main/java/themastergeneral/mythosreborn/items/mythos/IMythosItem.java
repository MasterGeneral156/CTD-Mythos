package themastergeneral.mythosreborn.items.mythos;

import net.minecraft.world.item.ItemStack;

public interface IMythosItem {

	int receiveMythos(int receive, ItemStack stack);
	int extractMythos(int extract, ItemStack stack);
	int getMaxMythos();
	int getCurrentMythos();
}
