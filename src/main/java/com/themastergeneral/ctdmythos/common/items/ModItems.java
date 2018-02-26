package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.item.Item.ToolMaterial;

import com.themastergeneral.ctdcore.item.RegisterItem;
import com.themastergeneral.ctdmythos.CTDMythos;

public class ModItems extends RegisterItem {
	public static HumanEffigyItem humaneffigy;
	public static ShearsItem grimshears;

	public static CowBloodItem cowblooddrop;
	public static SingleStackItem bucketcowblood;
	
	public static LongBowItem longbow;

	public static BaseItem humansoul;
	public static BaseItem crystal_woe;
	public static BaseItem crystal_memory;
	public static BaseItem crystal_grief;
	public static BaseItem crystal_fire;
	public static BaseItem crystal_oath;
	
	public static TMGDrill tmgdrill;
	public static MythosSwordBase gladius_sword;
	
	public static XPTomeItem xptome;
	

	public static void registerItems() {
		humaneffigy = register(new HumanEffigyItem("humaneffigy",
				CTDMythos.MODID));
		grimshears = register(new ShearsItem("grimshears", CTDMythos.MODID));
		cowblooddrop = register(new CowBloodItem("cowblooddrop",
				CTDMythos.MODID));
		bucketcowblood = register(new SingleStackItem("bucketcowblood",
				CTDMythos.MODID));
		humansoul = register(new BaseItem("humansoul", CTDMythos.MODID));
		crystal_woe = register(new BaseItem("crystal_woe", CTDMythos.MODID));
		crystal_memory = register(new BaseItem("crystal_memory", CTDMythos.MODID));
		crystal_grief = register(new BaseItem("crystal_grief", CTDMythos.MODID));
		crystal_fire = register(new BaseItem("crystal_fire", CTDMythos.MODID));
		crystal_oath = register(new BaseItem("crystal_oath", CTDMythos.MODID));
		
		longbow = register(new LongBowItem("longbow", CTDMythos.MODID, 90000, 200));
		
		tmgdrill = register(new TMGDrill("tmgdrill"));
		gladius_sword = register(new MythosSwordBase(ToolMaterial.GOLD, "gladius_sword", 823));
		
		xptome = register(new XPTomeItem("xptome", CTDMythos.MODID));
	}
}
