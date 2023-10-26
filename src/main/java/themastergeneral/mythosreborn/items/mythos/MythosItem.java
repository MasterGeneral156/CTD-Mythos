package themastergeneral.mythosreborn.items.mythos;

import com.themastergeneral.ctdcore.item.CTDItem;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;

public class MythosItem extends CTDItem implements IMythosItem, INBTSerializable<Tag> {

	protected int maxMythos;
	protected int currentMythos;
	
	public MythosItem(int maxMythos) {
		super(new Properties());
		this.maxMythos = maxMythos;
	}

	@Override
	public int receiveMythos(int receive) {
		int mythosReceived = Math.min(maxMythos - currentMythos, receive);
        currentMythos += mythosReceived;
        return mythosReceived;
	}

	@Override
	public int extractMythos(int extract) {
		int mythosExtracted = Math.min(currentMythos, extract);
        currentMythos -= mythosExtracted;
        return mythosExtracted;
	}

	@Override
	public int getMaxMythos() {
		return this.maxMythos;
	}

	@Override
	public int getCurrentMythos() {
		return currentMythos;
	}
	
    @Override
    public Tag serializeNBT()
    {
        return IntTag.valueOf(this.getCurrentMythos());
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
    public void deserializeNBT(Tag nbt)
    {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.currentMythos = intNbt.getAsInt();
    }

}
