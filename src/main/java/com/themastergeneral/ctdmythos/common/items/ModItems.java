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
		humaneffigy = register(new HumanEffigyItem("humaneffigy"));
		muleskick = register(new MulesKickItem("muleskick"));
		grimshears = register(new ShearsItem("grimshears"));
		cowblooddrop = register(new CowBloodItem("cowblooddrop"));
		bucketcowblood = register(new SingleStackItem("bucketcowblood"));
		humansoul = register(new BaseItem("humansoul"));
		archeron_ingot = register(new ArcheronIngot("archeron_ingot"));
		crystal_woe = register(new BaseItem("crystal_woe"));
		crystal_memory = register(new BaseItem("crystal_memory"));
		crystal_grief = register(new BaseItem("crystal_grief"));
		crystal_fire = register(new BaseItem("crystal_fire"));
		crystal_oath = register(new BaseItem("crystal_oath"));
		crystal_glove = register(new BaseItem("crystal_glove"));
		ethereal_fiber = register(new BaseItem("ethereal_fiber"));
		crystal_ender = register(new GlowingItem("crystal_ender"));

		longbow = register(new LongBowItem("longbow", 90000, 200));

		tmgdrill = register(new TMGDrill("tmgdrill"));
		gladius_sword = register(new MythosSwordBase(ToolMaterial.GOLD,
				"gladius_sword", 823));

		xptome = register(new XPTomeItem("xptome"));

		teleport_wand = register(new WandItemTeleport("teleport_wand"));

		flight_wand = register(new WandItemFlight("flight_wand"));

		revitalizing_rye = register(new BaseFood("revitalizing_rye", 3, 1.0F,
				false));
	}
}
