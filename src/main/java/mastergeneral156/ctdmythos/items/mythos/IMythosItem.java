package mastergeneral156.ctdmythos.items.mythos;

import net.minecraft.world.item.ItemStack;

public interface IMythosItem {

	float receiveMythos(float receive, ItemStack stack);
	float extractMythos(float extract, ItemStack stack);
	float getMaxMythos();
	float getCurrentMythos();
}
