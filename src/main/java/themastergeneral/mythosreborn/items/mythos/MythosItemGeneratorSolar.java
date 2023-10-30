package themastergeneral.mythosreborn.items.mythos;

import com.themastergeneral.ctdcore.helpers.WorldHelper;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MythosItemGeneratorSolar extends MythosItemGenerator {

	public MythosItemGeneratorSolar() 
	{
		super(1024, 8);
	}
	
	@Override
	protected boolean checkGenerationCondtions(ItemStack stack, Level level, Entity entity)
	{
		return WorldHelper.isClearDaytime(level);
	}

}
