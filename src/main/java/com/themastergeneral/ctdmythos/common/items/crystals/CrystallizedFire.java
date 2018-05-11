package com.themastergeneral.ctdmythos.common.items.crystals;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleLava;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.effects.EffectUtils;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.items.misc.BaseItem;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;

public class CrystallizedFire extends BaseItem {

	private Block containedBlock;

	public CrystallizedFire(String name) {
		super(name);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
			int itemSlot, boolean isSelected) {
		// Extinguish fire with Fire
		if (stack.getItem() == ModItems.crystal_fire) {
			((EntityLivingBase) entityIn).extinguish();
		}
	}

	// TODO: Wrap crafting check into seperate method.
	// Crafting, yay.
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		// Create Archeron Ingot with TNT and Crystallized Fire
		if (MainOffhandCrafting.instance().getRecipeResult(mainhand) != ItemStack.EMPTY) {
			if (MainOffhandCrafting.instance().getRecipeOffhand(mainhand) != ItemStack.EMPTY) {
				if (MainOffhandCrafting.instance().getRecipeOffhand(mainhand)
						.getItem() == offhand.getItem()) {
					if (!worldIn.isRemote) {
						mainhand.shrink(1);
						offhand.shrink(MainOffhandCrafting.instance()
								.getRecipeOffhand(mainhand).getCount());
						worldIn.spawnEntity(new EntityItem(worldIn,
								playerIn.posX, playerIn.posY, playerIn.posZ,
								MainOffhandCrafting.instance().getRecipeResult(
										mainhand)));
					}
					worldIn.playSound(playerIn, playerIn.getPosition(),
							ModSounds.spell_complete, SoundCategory.PLAYERS,
							1.0F, 1.0F);
				}
			}
		}
		if (playerIn.isSneaking()) {
			Block blocktotest = Blocks.BRICK_BLOCK;
			boolean flag = this.containedBlock == blocktotest;
			RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn,
					flag);
			if (raytraceresult == null) {
				return new ActionResult(EnumActionResult.PASS, mainhand);
			} else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
				return new ActionResult(EnumActionResult.PASS, mainhand);
			} else {
				BlockPos blockpos = raytraceresult.getBlockPos();
				if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
					return new ActionResult(EnumActionResult.FAIL, mainhand);
				}
				if (!playerIn.canPlayerEdit(
						blockpos.offset(raytraceresult.sideHit),
						raytraceresult.sideHit, mainhand)) {
					return new ActionResult(EnumActionResult.FAIL, mainhand);
				} else {
					IBlockState iblockstate = worldIn.getBlockState(blockpos);
					if (iblockstate == blocktotest.getDefaultState()) {
						worldIn.setBlockState(blockpos,
								ModBlocks.crystal_fire_brick.getDefaultState(),
								11);
						EntityLightningBolt lightning = new EntityLightningBolt(
								worldIn, blockpos.getX(), blockpos.getY(),
								blockpos.getZ(), false);
						worldIn.addWeatherEffect(lightning);
						return new ActionResult(EnumActionResult.PASS, mainhand);
					} else {
						return new ActionResult(EnumActionResult.FAIL, mainhand);
					}
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}
}
