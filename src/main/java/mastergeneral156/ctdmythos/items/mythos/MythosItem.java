package mastergeneral156.ctdmythos.items.mythos;

import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MythosItem extends CTDItem implements IMythosItem {

	protected int maxMythos;
	protected int currentMythos = 0;
	
	public MythosItem(int max) {
		super(new Properties().stacksTo(1));
		maxMythos = max;
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) {
		CompoundTag tag = stack.getOrCreateTag();
		if (!tag.contains("maxMythos")) {
			tag.putInt("maxMythos", maxMythos); // from constructor
			tag.putInt("currentMythos", 0);
		}
	}

	@Override
	public int receiveMythos(int receive, ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		int current = tag.getInt("currentMythos");
		int max = tag.getInt("maxMythos");
		int received = Math.min(max - current, receive);
		tag.putInt("currentMythos", current + received);
		return received;
	}

	@Override
	public int extractMythos(int extract, ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		int current = tag.getInt("currentMythos");
		int extracted = Math.min(current, extract);
		tag.putInt("currentMythos", current - extracted);
		return extracted;
	}

	@Override
	public int getMaxMythos() {
		// Default fallback if needed
		return 100;
	}

	@Override
	public int getCurrentMythos() {
		// Deprecated - use stack-based method instead
		return 0;
	}

	public int getCurrentMythos(ItemStack stack) {
		return stack.getOrCreateTag().getInt("currentMythos");
	}

	public int getMaxMythos(ItemStack stack) {
		return stack.getOrCreateTag().getInt("maxMythos");
	}

	public static int getMythos(ItemStack stack) {
		return stack.getOrCreateTag().getInt("currentMythos");
	}

	public static void setMythos(ItemStack stack, int amount) {
		stack.getOrCreateTag().putInt("currentMythos", amount);
	}

	@Override
	public boolean isBarVisible(ItemStack stack) {
		return getMaxMythos(stack) > 0;
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		int max = getMaxMythos(stack);
		int current = getCurrentMythos(stack);
		return Math.round(13.0F * ((float) current / max));
	}

	@Override
	public int getBarColor(ItemStack stack) {
		float progress = (float) getCurrentMythos(stack) / getMaxMythos(stack);
		return Mth.hsvToRgb(progress / 3.0F, 1.0F, 1.0F);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
		if (!stack.hasTag()) {
			CompoundTag tag = new CompoundTag();
			tag.putInt("maxMythos", maxMythos);
			tag.putInt("currentMythos", 0);
			stack.setTag(tag);
		}
	}
}
