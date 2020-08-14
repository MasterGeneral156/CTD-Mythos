package com.themastergeneral.ctdmythos.common.items.mythos;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;

public class MythosSword extends MythosItemBase
{
	protected String name;	//Name of the item.
	protected int poolSize;
	protected int changeSize;
	
	public MythosSword(String name, int poolSize, int changeSize) 
	{
		super(name, poolSize, changeSize);
		this.name = name;
		this.poolSize = poolSize;
		this.changeSize = changeSize;
	}

}
