package com.themastergeneral.ctdmythos.common.events;

import java.util.Random;

import com.themastergeneral.ctdmythos.common.items.ModItems;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CowBloodDropEvent {
	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		if (event.getEntity() instanceof EntityCow) {
			Random randomGenerator = new Random();
			int itemdrop = randomGenerator.nextInt(100);
			ItemStack itemStackToDrop = new ItemStack(ModItems.cowblooddrop, 1);
			// 50% chance to have an extra dropped.
			if (itemdrop <= 50) {
				event.getDrops().add(
						new EntityItem(event.getEntity().world, event
								.getEntity().posX, event.getEntity().posY,
								event.getEntity().posZ, itemStackToDrop));
			}
			// 25% chance to have an extra dropped.
			if (itemdrop <= 25) {
				event.getDrops().add(
						new EntityItem(event.getEntity().world, event
								.getEntity().posX, event.getEntity().posY,
								event.getEntity().posZ, itemStackToDrop));
			}
			// Drop the blood.
			event.getDrops().add(
					new EntityItem(event.getEntity().world,
							event.getEntity().posX, event.getEntity().posY,
							event.getEntity().posZ, itemStackToDrop));
		}
	}
}
