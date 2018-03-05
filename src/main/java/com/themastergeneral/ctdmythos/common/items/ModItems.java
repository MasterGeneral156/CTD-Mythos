package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.item.Item.ToolMaterial;

import com.themastergeneral.ctdcore.item.RegisterItem;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.artifacts.DebugItem;
import com.themastergeneral.ctdmythos.common.items.artifacts.HumanEffigyItem;
import com.themastergeneral.ctdmythos.common.items.artifacts.MulesKickItem;
import com.themastergeneral.ctdmythos.common.items.artifacts.OreDoublingNode;
import com.themastergeneral.ctdmythos.common.items.artifacts.TalismanItemEvasion;
import com.themastergeneral.ctdmythos.common.items.artifacts.XPTomeItem;
import com.themastergeneral.ctdmythos.common.items.crystals.CrystallizedFire;
import com.themastergeneral.ctdmythos.common.items.crystals.CrystallizedOath;
import com.themastergeneral.ctdmythos.common.items.crystals.CrystallizedWoe;
import com.themastergeneral.ctdmythos.common.items.misc.ArcheronIngot;
import com.themastergeneral.ctdmythos.common.items.misc.BaseFood;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.items.misc.CowBloodItem;
import com.themastergeneral.ctdmythos.common.items.misc.GlowingItem;
import com.themastergeneral.ctdmythos.common.items.misc.SingleStackItem;
import com.themastergeneral.ctdmythos.common.items.tools.LongBowItem;
import com.themastergeneral.ctdmythos.common.items.tools.MythosSwordBase;
import com.themastergeneral.ctdmythos.common.items.tools.ShearsItem;
import com.themastergeneral.ctdmythos.common.items.tools.TMGDrill;
import com.themastergeneral.ctdmythos.common.items.wands.WandItemFlight;
import com.themastergeneral.ctdmythos.common.items.wands.WandItemTeleport;

public class ModItems extends RegisterItem {
	public static HumanEffigyItem humaneffigy;
	public static MulesKickItem muleskick;

	public static ShearsItem grimshears;

	public static CowBloodItem cowblooddrop;
	public static SingleStackItem bucketcowblood;

	public static LongBowItem longbow;

	public static BaseItem humansoul;
	public static CrystallizedWoe crystal_woe;
	public static BaseItem crystal_memory;
	public static BaseItem crystal_grief;
	public static CrystallizedFire crystal_fire;
	public static CrystallizedOath crystal_oath;
	public static BaseItem ethereal_fiber;
	public static BaseItem crystal_glove;
	public static OreDoublingNode node_ore_double;

	public static BaseFood revitalizing_rye;

	public static ArcheronIngot archeron_ingot;
	public static GlowingItem crystal_ender;

	public static TMGDrill tmgdrill;
	public static MythosSwordBase gladius_sword;

	public static XPTomeItem xptome;

	public static WandItemTeleport teleport_wand;
	public static WandItemFlight flight_wand;

	public static DebugItem debug_item;

	public static TalismanItemEvasion evasion_talisman;

	public static void registerItems() {
		humaneffigy = register(new HumanEffigyItem("humaneffigy"));
		muleskick = register(new MulesKickItem("muleskick"));
		grimshears = register(new ShearsItem("grimshears"));
		cowblooddrop = register(new CowBloodItem("cowblooddrop"));
		bucketcowblood = register(new SingleStackItem("bucketcowblood"));
		humansoul = register(new BaseItem("humansoul"));
		archeron_ingot = register(new ArcheronIngot("archeron_ingot"));
		crystal_woe = register(new CrystallizedWoe("crystal_woe"));
		crystal_memory = register(new BaseItem("crystal_memory"));
		crystal_grief = register(new BaseItem("crystal_grief"));
		crystal_fire = register(new CrystallizedFire("crystal_fire"));
		crystal_oath = register(new CrystallizedOath("crystal_oath"));
		crystal_glove = register(new BaseItem("crystal_glove"));
		ethereal_fiber = register(new BaseItem("ethereal_fiber"));
		crystal_ender = register(new GlowingItem("crystal_ender"));

		longbow = register(new LongBowItem("longbow", 180000, 200));

		tmgdrill = register(new TMGDrill("tmgdrill"));
		gladius_sword = register(new MythosSwordBase(ToolMaterial.GOLD,
				"gladius_sword", 823));

		xptome = register(new XPTomeItem("xptome"));

		teleport_wand = register(new WandItemTeleport("teleport_wand"));

		flight_wand = register(new WandItemFlight("flight_wand"));

		revitalizing_rye = register(new BaseFood("revitalizing_rye", 3, 1.0F,
				false));

		evasion_talisman = register(new TalismanItemEvasion("evasion_talisman"));

		debug_item = register(new DebugItem("debug"));

		node_ore_double = register(new OreDoublingNode("node_ore_double"));
	}
}
