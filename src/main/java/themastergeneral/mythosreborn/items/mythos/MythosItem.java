package themastergeneral.mythosreborn.items.mythos;

import org.jetbrains.annotations.Nullable;

import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class MythosItem extends CTDItem implements IMythosItem {

	protected int maxMythos;
	protected int currentMythos = 0;
	
	public MythosItem(int maxMythos) {
		super(new Properties());
		this.maxMythos = maxMythos;
	}

	@Override
	public int receiveMythos(int receive) {
		int mythosReceived = Math.min(maxMythos - currentMythos, receive);
        this.currentMythos += mythosReceived;
        return mythosReceived;
	}

	@Override
	public int extractMythos(int extract) {
		int mythosExtracted = Math.min(currentMythos, extract);
        this.currentMythos -= mythosExtracted;
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

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundTag nbt)
    {
    	CompoundTag compoundnbt = new CompoundTag();
    	compoundnbt.putInt("currentMythos", currentMythos);
    	compoundnbt.putInt("maxMythos", maxMythos);
        stack.setTag(compoundnbt);
    }

}
