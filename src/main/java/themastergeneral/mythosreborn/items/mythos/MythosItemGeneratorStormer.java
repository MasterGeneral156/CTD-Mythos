package themastergeneral.mythosreborn.items.mythos;

import com.themastergeneral.ctdcore.helpers.WorldHelper;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MythosItemGeneratorStormer extends MythosItemGenerator {

	public MythosItemGeneratorStormer() 
	{
		super(2048, 32);
	}
	
	@Override
	protected boolean checkGenerationCondtions(ItemStack stack, Level level, Entity entity)
	{
		return WorldHelper.isStorming(level);
	}

}
