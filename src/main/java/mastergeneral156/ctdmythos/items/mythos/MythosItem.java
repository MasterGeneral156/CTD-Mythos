package mastergeneral156.ctdmythos.items.mythos;

import com.themastergeneral.ctdcore.helpers.ModUtils;
import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class MythosItem extends CTDItem implements IMythosItem {

	protected float maxMythos;
	protected float currentMythos = 0;
	
	public MythosItem(float max) {
		super(new Properties().stacksTo(1));
		maxMythos = max;
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) {
		CompoundTag tag = stack.getOrCreateTag();
		if (!tag.contains("maxMythos")) {
			tag.putFloat("maxMythos", maxMythos); // from constructor
			tag.putFloat("currentMythos", 0);
		}
	}

	@Override
	public float receiveMythos(float receive, ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		float current = tag.getFloat("currentMythos");
		float max = tag.getFloat("maxMythos");
		float received = Math.min(max - current, receive);
		tag.putFloat("currentMythos", current + received);
		return received;
	}

	@Override
	public float extractMythos(float extract, ItemStack stack) {
		CompoundTag tag = stack.getOrCreateTag();
		float current = tag.getFloat("currentMythos");
		float extracted = Math.min(current, extract);
		tag.putFloat("currentMythos", current - extracted);
		return extracted;
	}

	@Override
	public float getMaxMythos() {
		return 100F;
	}

	@Override
	public float getCurrentMythos() {
		return 0F;
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
			tag.putFloat("maxMythos", maxMythos);
			tag.putFloat("currentMythos", 0);
			stack.setTag(tag);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(ModUtils.displayString("Mythos: " + ModUtils.returnShortenedNumber(getCurrentMythos(stack)) + "/" + ModUtils.returnShortenedNumber(getMaxMythos(stack))));

	}
}
