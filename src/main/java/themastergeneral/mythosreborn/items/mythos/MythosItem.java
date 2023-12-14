package themastergeneral.mythosreborn.items.mythos;

import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
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
		if (!stack.hasTag())
		{
			CompoundTag compoundnbt = new CompoundTag();
			compoundnbt.putInt("maxMythos", maxMythos);
			compoundnbt.putInt("currentMythos", currentMythos);
			stack.setTag(compoundnbt);
		}
	}

	@Override
	public int receiveMythos(int receive, ItemStack stack) {
		int mythosReceived = Math.min(maxMythos - currentMythos, receive);
		currentMythos += mythosReceived;
		if (stack.hasTag())
		{
			CompoundTag compoundnbt = new CompoundTag();
			compoundnbt.putInt("maxMythos", maxMythos);
			compoundnbt.putInt("currentMythos", currentMythos);
			stack.setTag(compoundnbt);
		}
        return mythosReceived;
	}

	@Override
	public int extractMythos(int extract, ItemStack stack) {
		int mythosExtracted = Math.min(currentMythos, extract);
        currentMythos -= mythosExtracted;
        if (stack.hasTag())
		{
			CompoundTag compoundnbt = new CompoundTag();
			compoundnbt.putInt("maxMythos", maxMythos);
			compoundnbt.putInt("currentMythos", currentMythos);
			stack.setTag(compoundnbt);
		}
        return mythosExtracted;
	}

	@Override
	public int getMaxMythos() {
		return maxMythos;
	}

	@Override
	public int getCurrentMythos() {
		return currentMythos;
	}
    
    @Override
	public boolean isBarVisible(ItemStack stack)
	{
    	if (this.getMaxMythos() > 0)
    		return true;
    	else
    		return false;
	}
    
    @Override
    public int getBarWidth(ItemStack stack) 
	{
		return Math.round(13.0F - (this.getMaxMythos() - this.getCurrentMythos()) * 13.0F / this.getMaxMythos());
	}
    
    public int getBarColor(ItemStack stack) 
    {
       float f = Math.max(0.0F, (this.getMaxMythos() - (float) (this.getMaxMythos() - this.getCurrentMythos())) / this.getMaxMythos());
       return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

}
