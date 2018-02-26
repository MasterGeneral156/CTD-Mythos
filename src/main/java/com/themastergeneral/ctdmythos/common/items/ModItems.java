package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.item.Item.ToolMaterial;

import com.themastergeneral.ctdcore.item.RegisterItem;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.base.BaseFood;
import com.themastergeneral.ctdmythos.common.items.base.BaseItem;
import com.themastergeneral.ctdmythos.common.items.base.GlowingItem;
import com.themastergeneral.ctdmythos.common.items.base.MythosSwordBase;
import com.themastergeneral.ctdmythos.common.items.base.SingleStackItem;

public class ModItems extends RegisterItem {
	public static HumanEffigyItem humaneffigy;
	public static MulesKickItem muleskick;
	
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
	public static BaseItem ethereal_fiber;
	public static BaseItem crystal_glove;

	public static BaseFood revitalizing_rye;

	public static ArcheronIngot archeron_ingot;
	public static GlowingItem crystal_ender;

	public static TMGDrill tmgdrill;
	public static MythosSwordBase gladius_sword;

	public static XPTomeItem xptome;

	public static WandItemTeleport teleport_wand;
	public static WandItemFlight flight_wand;

	public static void registerItems() {
		humaneffigy = register(new HumanEffigyItem("humaneffigy",
				CTDMythos.MODID));
		muleskick = register(new MulesKickItem("muleskick"));
		grimshears = register(new ShearsItem("grimshears", CTDMythos.MODID));
		cowblooddrop = register(new CowBloodItem("cowblooddrop",
				CTDMythos.MODID));
		bucketcowblood = register(new SingleStackItem("bucketcowblood",
				CTDMythos.MODID));
		humansoul = register(new BaseItem("humansoul", CTDMythos.MODID));
		archeron_ingot = register(new ArcheronIngot("archeron_ingot",
				CTDMythos.MODID));
		crystal_woe = register(new BaseItem("crystal_woe", CTDMythos.MODID));
		crystal_memory = register(new BaseItem("crystal_memory",
				CTDMythos.MODID));
		crystal_grief = register(new BaseItem("crystal_grief", CTDMythos.MODID));
		crystal_fire = register(new BaseItem("crystal_fire", CTDMythos.MODID));
		crystal_oath = register(new BaseItem("crystal_oath", CTDMythos.MODID));
		crystal_glove = register(new BaseItem("crystal_glove", CTDMythos.MODID));
		ethereal_fiber = register(new BaseItem("ethereal_fiber",
				CTDMythos.MODID));
		crystal_ender = register(new GlowingItem("crystal_ender",
				CTDMythos.MODID));

		longbow = register(new LongBowItem("longbow", CTDMythos.MODID, 90000,
				200));

		tmgdrill = register(new TMGDrill("tmgdrill"));
		gladius_sword = register(new MythosSwordBase(ToolMaterial.GOLD,
				"gladius_sword", 823));

		xptome = register(new XPTomeItem("xptome", CTDMythos.MODID));

		teleport_wand = register(new WandItemTeleport("teleport_wand",
				CTDMythos.MODID));

		flight_wand = register(new WandItemFlight("flight_wand",
				CTDMythos.MODID));

		revitalizing_rye = register(new BaseFood("revitalizing_rye", 3, 1.0F,
				false));
	}
}
