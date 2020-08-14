package com.themastergeneral.ctdmythos.common.items.mythos.gens;

import com.themastergeneral.ctdmythos.CTDMythos;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MythosGenFall extends MythosInventGen {

	int mpt;
	public MythosGenFall(String name) 
	{
		super(name, 4096, 128, 8);
		mpt = 8;
	}
	
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if (!worldIn.isRemote)
		{
			if (entityIn.fallDistance > 2.0F)
			{
				CTDMythos.logger.info(entityIn.fallDistance);
				addToPool(stack, mpt);
			}
		}
    }

}
