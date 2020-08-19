package com.themastergeneral.ctdmythos.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class MythosBaseTileEntity extends TileEntity
{	
	public int mythos_pool;
	public int max_mythos_pool;
	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
		compound.setInteger("mythos_pool", mythos_pool);
		compound.setInteger("max_mythos_pool", max_mythos_pool);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        mythos_pool = compound.getInteger("mythos_pool");
        max_mythos_pool = compound.getInteger("max_mythos_pool");
        super.readFromNBT(compound);
    }
	public int getCurrentPool()
	{
		return mythos_pool;
	}
	
	public int getMaxPool()
	{
		return max_mythos_pool;
	}
	
	public void addToPool(int addToPool)
	{
		mythos_pool = getCurrentPool() + addToPool;
		if (mythos_pool > getMaxPool())
			mythos_pool = getMaxPool();
		markDirty();
	}
	
	public void removeFromPool(int removeFromPool)
	{
		mythos_pool = getCurrentPool() - removeFromPool;
		if (mythos_pool < 0)
			mythos_pool = 0;
		markDirty();
	}
	
	public void setMaxPool(int newMaxPool)
	{
		max_mythos_pool = newMaxPool;
		markDirty();
	}
}
