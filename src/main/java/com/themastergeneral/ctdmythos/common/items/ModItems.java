package com.themastergeneral.ctdmythos.common.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import com.themastergeneral.ctdcore.item.RegisterItem;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.artifacts.DebugItem;
import com.themastergeneral.ctdmythos.common.items.artifacts.HumanEffigyItem;
import com.themastergeneral.ctdmythos.common.items.artifacts.LightningStaff;
import com.themastergeneral.ctdmythos.common.items.artifacts.MBValidatorItem;
import com.themastergeneral.ctdmythos.common.items.artifacts.MulesKickItem;
import com.themastergeneral.ctdmythos.common.items.artifacts.TalismanItemEvasion;
import com.themastergeneral.ctdmythos.common.items.baubles.AmuletExtendReach;
import com.themastergeneral.ctdmythos.common.items.baubles.BasicBauble;
import com.themastergeneral.ctdmythos.common.items.baubles.TrinketHealth;
import com.themastergeneral.ctdmythos.common.items.crystals.CrystallizedFire;
import com.themastergeneral.ctdmythos.common.items.crystals.CrystallizedGrief;
import com.themastergeneral.ctdmythos.common.items.crystals.CrystallizedMemory;
import com.themastergeneral.ctdmythos.common.items.crystals.CrystallizedOath;
import com.themastergeneral.ctdmythos.common.items.crystals.CrystallizedWoe;
import com.themastergeneral.ctdmythos.common.items.misc.BaseFood;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.items.misc.BottleLightning;
import com.themastergeneral.ctdmythos.common.items.misc.GlowingItem;
import com.themastergeneral.ctdmythos.common.items.misc.ModArmorMaterials;
import com.themastergeneral.ctdmythos.common.items.misc.SingleStackItem;
import com.themastergeneral.ctdmythos.common.items.mythos.MythosFlightWand;
import com.themastergeneral.ctdmythos.common.items.mythos.MythosInventGen;
import com.themastergeneral.ctdmythos.common.items.mythos.MythosPick;
import com.themastergeneral.ctdmythos.common.items.mythos.MythosPool;
import com.themastergeneral.ctdmythos.common.items.tools.LongBowItem;
import com.themastergeneral.ctdmythos.common.items.tools.MythosArrow;
import com.themastergeneral.ctdmythos.common.items.tools.MythosSwordBase;
import com.themastergeneral.ctdmythos.common.items.tools.ShearsItem;
import com.themastergeneral.ctdmythos.common.items.tools.ShulkerHelmet;
import com.themastergeneral.ctdmythos.common.items.tools.TMGDrill;
import com.themastergeneral.ctdmythos.common.items.wands.WandItemFlight;
import com.themastergeneral.ctdmythos.common.items.wands.WandItemTeleport;

public class ModItems extends RegisterItem
{

    public static HumanEffigyItem humaneffigy;
    public static MulesKickItem muleskick;
    public static TMGDrill tmgdrill;
    public static MythosSwordBase gladius_sword;
    public static LongBowItem longbow;
    public static ShearsItem grimshears;
    public static MythosArrow mythos_arrow;

    public static CrystallizedWoe crystal_woe;
    public static CrystallizedMemory crystal_memory;
    public static CrystallizedGrief crystal_grief;
    public static CrystallizedFire crystal_fire;
    public static CrystallizedOath crystal_oath;

    public static BaseItem ethereal_fiber;
    public static BaseItem humansoul;
    public static BasicBauble crystal_glove;
    public static BaseItem cowblooddrop;
    public static BaseItem xptome;
    public static BaseItem enchanted_stick;
    public static BaseItem archeron_ingot;
    public static BaseFood revitalizing_rye;

    public static SingleStackItem bucketcowblood;

    public static MBValidatorItem mb_validator;
    public static WandItemTeleport teleport_wand;
    public static WandItemFlight flight_wand;
    public static MythosFlightWand mythos_flight_wand;

    public static TalismanItemEvasion evasion_talisman;

    public static AmuletExtendReach amuletreach;
    public static AmuletExtendReach creativeamuletreach;

    public static TrinketHealth trinketHealth;

    public static DebugItem debug_item;
    
    public static ShulkerHelmet shulker_helmet;
    
    public static BottleLightning lightning_bottle;
    public static LightningStaff lightning_staff;

    public static MythosPool mythos_pool;
    public static MythosInventGen mythos_exciter;
    public static MythosInventGen mythos_nova;
    
    public static MythosPick mythos_pick;
    public static void registerItems()
    {
        humaneffigy = register(new HumanEffigyItem("humaneffigy"));
        muleskick = register(new MulesKickItem("muleskick"));
        grimshears = register(new ShearsItem("grimshears"));
        cowblooddrop = register(new BaseItem("cowblooddrop"));
        bucketcowblood = register(new SingleStackItem("bucketcowblood"));
        humansoul = register(new BaseItem("humansoul"));
        archeron_ingot = register(new BaseItem("archeron_ingot"));
        crystal_woe = register(new CrystallizedWoe("crystal_woe"));
        crystal_memory = register(new CrystallizedMemory("crystal_memory"));
        crystal_grief = register(new CrystallizedGrief("crystal_grief"));
        crystal_fire = register(new CrystallizedFire("crystal_fire"));
        crystal_oath = register(new CrystallizedOath("crystal_oath"));
        crystal_glove = register(new BasicBauble("crystal_glove"));
        ethereal_fiber = register(new BaseItem("ethereal_fiber"));
        enchanted_stick = register(new BaseItem("enchanted_stick"));
        mythos_pool = register(new MythosPool("mythos_pool", 20000, 250));
        mythos_exciter = register(new MythosInventGen("mythos_exciter", 512, 16, ModConfig.mythosExciterGen));
        mythos_nova = register(new MythosInventGen("mythos_nova", Integer.MAX_VALUE, 100, ModConfig.mythosMaxStorage));

        longbow = register(new LongBowItem("longbow", 144000, 640));

        tmgdrill = register(new TMGDrill("tmgdrill"));
        gladius_sword = register(new MythosSwordBase(ToolMaterial.GOLD,
                "gladius_sword", 823));

        xptome = register(new BaseItem("xptome"));

        teleport_wand = register(new WandItemTeleport("teleport_wand"));

        flight_wand = register(new WandItemFlight("flight_wand"));
        mythos_flight_wand = register(new MythosFlightWand("mythos_flight_wand", 4000, 375));

        revitalizing_rye = register(new BaseFood("revitalizing_rye", 3, 1.0F,
                false));

        evasion_talisman = register(new TalismanItemEvasion("evasion_talisman"));

        debug_item = register(new DebugItem("debug"));

        amuletreach = register(new AmuletExtendReach("amuletreach",
                ModConfig.reachAmuletRange));
        creativeamuletreach = register(new AmuletExtendReach(
                "creativeamuletreach", ModConfig.creativereachAmuletRange));
        trinketHealth = register(new TrinketHealth("trinketHealth"));

        mb_validator = register(new MBValidatorItem("mb_validator"));
        
        lightning_bottle = register(new BottleLightning("lightning_bottle"));
        
        lightning_staff = register(new LightningStaff("lightning_staff"));

        mythos_arrow = register(new MythosArrow("mythos_arrow", CTDMythos.MODID));
        mythos_pick = register(new MythosPick("mythos_pick", 4000, 50));
        shulker_helmet = register(new ShulkerHelmet("shulker_helmet", ModArmorMaterials.shulker_armor, 1, EntityEquipmentSlot.HEAD));
    }
}
