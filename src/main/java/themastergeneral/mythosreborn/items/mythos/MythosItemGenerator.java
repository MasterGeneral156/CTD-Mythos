package themastergeneral.mythosreborn.items.mythos;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MythosItemGenerator extends MythosItem {

	private int genRate;
	
	public MythosItemGenerator(int maxMythos, int genRate) {
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
		if (checkGenerationCondtions(stack, level, entity))
		{
			MythosItem actualStack = (MythosItem) stack.getItem();
			actualStack.receiveMythos(returnGenRate());
		}
	}
	
	public int returnGenRate()
	{
		return genRate;
	}

}
