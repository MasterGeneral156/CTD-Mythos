package com.themastergeneral.ctdmythos.common.items.artifacts;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;

public class OreDoublingNode extends BaseItem {

	public OreDoublingNode(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.setMaxDamage(64);
	}

	// Item does not laeve the crafting table.
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
		return false;
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	public boolean hasContainerItem(ItemStack itemStack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		ItemStack stack = itemStack.copy();
		stack.setItemDamage(stack.getItemDamage() + 1);
		this.maxStackSize = 1;
		return stack;
	}

	// TODO: Wrap crafting check into seperate method.
	// Craft items with the ore doubling node.
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		// Crystal oath + book in offhand to get a tome of XP
		if (MainOffhandCrafting.instance().getRecipeResult(mainhand) != ItemStack.EMPTY) {
			if (MainOffhandCrafting.instance().getRecipeOffhand(mainhand) != ItemStack.EMPTY) {
				if (MainOffhandCrafting.instance().getRecipeOffhand(mainhand)
						.getItem() == offhand.getItem()) {
					if (!worldIn.isRemote) {
						offhand.damageItem(1, playerIn);
						mainhand.shrink(MainOffhandCrafting.instance().getRecipeOffhand(mainhand).getCount());
						worldIn.spawnEntity(new EntityItem(worldIn,
								playerIn.posX, playerIn.posY, playerIn.posZ,
								MainOffhandCrafting.instance().getRecipeResult(
										mainhand)));
						playerIn.getCooldownTracker().setCooldown(this, 160);
					}
					worldIn.playSound(playerIn, playerIn.getPosition(),
							ModSounds.spell_complete, SoundCategory.PLAYERS,
							1.0F, 1.0F);
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

}
