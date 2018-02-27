package com.themastergeneral.ctdmythos.common.processing;

import com.themastergeneral.ctdmythos.CTDMythos;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModSounds {
	public static final SoundEvent spell_complete = new SoundEvent(new ResourceLocation(CTDMythos.MODID, "spell_complete")).setRegistryName(new ResourceLocation(CTDMythos.MODID, "spell_complete"));
	public static final SoundEvent mules_kick = new SoundEvent(new ResourceLocation(CTDMythos.MODID, "mules_kick")).setRegistryName(new ResourceLocation(CTDMythos.MODID, "mules_kick"));
	public static final SoundEvent human_effigy = new SoundEvent(new ResourceLocation(CTDMythos.MODID, "human_effigy")).setRegistryName(new ResourceLocation(CTDMythos.MODID, "human_effigy"));
	public static final SoundEvent flight_wand = new SoundEvent(new ResourceLocation(CTDMythos.MODID, "flight_wand")).setRegistryName(new ResourceLocation(CTDMythos.MODID, "flight_wand"));
	
	public static void init(IForgeRegistry<SoundEvent> registry)
	{
		registry.register(spell_complete);
		registry.register(mules_kick);
		registry.register(human_effigy);
		registry.register(flight_wand);
	}

}
