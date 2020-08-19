package com.themastergeneral.ctdmythos.tileentity;

import net.minecraft.util.ITickable;

public class MythosExciterFocusTileEntity extends MythosBaseTileEntity implements ITickable 
{

	@Override
	public void update() 
	{
		if (getCurrentPool() < getMaxPool())
		{
			if (world.isDaytime())
			{
				addToPool(1);
			}
		}
		if (getMaxPool() == 0)
		{
			setMaxPool(20000);
		}
	}
}
