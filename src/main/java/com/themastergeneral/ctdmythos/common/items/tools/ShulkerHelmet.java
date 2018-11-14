package com.themastergeneral.ctdmythos.common.items.tools;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ShulkerHelmet extends ItemArmor implements ItemModelProvider {

	protected String name;
	public ShulkerHelmet(String name, ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.name = name;
		this.setRegistryName(name);
		this.setMaxDamage(100);
		this.setUnlocalizedName(name);
        this.getIsRepairable(new ItemStack(this), new ItemStack(Items.SHULKER_SHELL));
        this.setCreativeTab(CTDMythos.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		CTDCore.proxy.registerItemRenderer(CTDMythos.MODID, this, 0, name);

	}

}
