package mastergeneral156.ctdmythos.items.mythos;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MythosItemGenerator extends MythosItem {

	protected float genRate;
	
	public MythosItemGenerator(float maxMythos, float genRate) {
		super(maxMythos);
		this.genRate = genRate;
	}
	
	protected boolean checkGenerationCondtions(ItemStack stack, Level level, Entity entity)
	{
		return true;
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int int1, boolean bool1)
	{
		super.inventoryTick(stack, level, entity, int1, bool1);
		if (checkGenerationCondtions(stack, level, entity))
		{
			receiveMythos(returnGenRate(), stack);
		}
	}
	
	public float returnGenRate()
	{
		return genRate;
	}

}
