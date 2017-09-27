package com.themastergeneral.ctdmythos.common.items;

import com.themastergeneral.ctdcore.item.RegisterItem;
import com.themastergeneral.ctdmythos.CTDMythos;

public class ModItems extends RegisterItem {
	public static HumanEffigyItem humaneffigy;
	public static ShearsItem grimshears;

	public static CowBloodItem cowblooddrop;
	public static SingleStackItem bucketcowblood;
	
	public static LongBowItem longbow;

	public static BaseItem humansoul;

	public static void registerItems() {
		humaneffigy = register(new HumanEffigyItem("humaneffigy",
				CTDMythos.MODID));
		grimshears = register(new ShearsItem("grimshears", CTDMythos.MODID));
		cowblooddrop = register(new CowBloodItem("cowblooddrop",
				CTDMythos.MODID));
		bucketcowblood = register(new SingleStackItem("bucketcowblood",
				CTDMythos.MODID));
		humansoul = register(new BaseItem("humansoul", CTDMythos.MODID));
		
		longbow = register(new LongBowItem("longbow", CTDMythos.MODID, 72000, 200));
	}
}
