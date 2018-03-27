package com.themastergeneral.ctdmythos.common.items.misc;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;

import com.jcraft.jorbis.Block;
import com.themastergeneral.ctdcore.item.CTDItem;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.effects.EffectUtils;
import com.themastergeneral.ctdmythos.common.items.ModItems;
import com.themastergeneral.ctdmythos.common.processing.MainOffhandCrafting;
import com.themastergeneral.ctdmythos.common.processing.ModSounds;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;

public class BaseItem extends CTDItem {
	public BaseItem(String name) {
		super(name, CTDMythos.MODID);
		this.setCreativeTab(CTDMythos.creativeTab);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,
			int itemSlot, boolean isSelected) {
		EntityPlayer playerIn = (EntityPlayer) entityIn;
		ItemStack offhand = playerIn.getHeldItemOffhand();
		// Blindness effect with grief
		if ((stack.getItem() == ModItems.crystal_grief)
				&& (offhand.getItem() != ModItems.crystal_glove)) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
					MobEffects.BLINDNESS, 20, 0, true, false));
		}
		// Nausea effect with memory
		if ((stack.getItem() == ModItems.crystal_memory)
				&& (offhand.getItem() != ModItems.crystal_glove)) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
					MobEffects.WEAKNESS, 20, 2, true, false));
		}
		// Slowness effect with Woe
		if ((stack.getItem() == ModItems.crystal_woe)
				&& (offhand.getItem() != ModItems.crystal_glove)) {
			((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(
					MobEffects.SLOWNESS, 20, 2, true, false));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn,
			EntityPlayer playerIn, EnumHand handIn) {
		ItemStack offhand = playerIn.getHeldItemOffhand();
		ItemStack mainhand = playerIn.getHeldItemMainhand();
		// Crystalized Memory to repair the item in the user's offhand, at
		// the cost of one Crystalized Memory.
		if (mainhand.getItem() == ModItems.crystal_memory) {
			if (offhand != ItemStack.EMPTY) {
				if (offhand.isItemDamaged()) {
					offhand.setItemDamage(0);
					mainhand.shrink(1);
					worldIn.playSound(playerIn, playerIn.getPosition(),
							ModSounds.spell_complete, SoundCategory.PLAYERS,
							1.0F, 1.0F);
				}
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS,
				playerIn.getHeldItem(handIn));
	}

	public boolean validMultiblock(BlockPos pos, World world,
			EntityPlayer player, boolean output) {
		if (!world.isRemote) {
			BlockPos startpos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
			BlockPos underpos = new BlockPos(pos.getX(), pos.getY() - 1,
					pos.getZ());
			BlockPos northpos = new BlockPos(pos.getX() + 1, pos.getY() - 1,
					pos.getZ());
			BlockPos southpos = new BlockPos(pos.getX() - 1, pos.getY() - 1,
					pos.getZ());
			BlockPos eastpos = new BlockPos(pos.getX(), pos.getY() - 1,
					pos.getZ() + 1);
			BlockPos westpos = new BlockPos(pos.getX(), pos.getY() - 1,
					pos.getZ() - 1);
			if (world.getBlockState(startpos).getBlock() == ModBlocks.crystal_fire_block) {
				if (world.getBlockState(underpos).getBlock() == ModBlocks.crystal_fire_brick) {
					if (world.getBlockState(northpos).getBlock() == ModBlocks.crystal_fire_brick) {
						if (world.getBlockState(southpos).getBlock() == ModBlocks.crystal_fire_brick) {
							if (world.getBlockState(eastpos).getBlock() == ModBlocks.crystal_fire_brick) {
								if (world.getBlockState(westpos).getBlock() == ModBlocks.crystal_fire_brick) {
									if (output) {
										player.sendMessage(new TextComponentString(
												"Valid multiblock."));
									}
									return true;
								} else {
									if (output) {
										player.sendMessage(new TextComponentString(
												"Expecting "
														+ ModBlocks.crystal_fire_brick
																.getUnlocalizedName()
														+ " at "
														+ westpos
														+ " but got "
														+ world.getBlockState(
																westpos)
																.getBlock()
																.getUnlocalizedName()));
									}
									return false;
								}
							} else {
								if (output) {
									player.sendMessage(new TextComponentString(
											"Expecting "
													+ ModBlocks.crystal_fire_brick
															.getUnlocalizedName()
													+ " at "
													+ eastpos
													+ " but got "
													+ world.getBlockState(
															eastpos)
															.getBlock()
															.getUnlocalizedName()));
								}
								return false;
							}
						} else {
							if (output) {
								player.sendMessage(new TextComponentString(
										"Expecting "
												+ ModBlocks.crystal_fire_brick
														.getUnlocalizedName()
												+ " at "
												+ southpos
												+ " but got "
												+ world.getBlockState(southpos)
														.getBlock()
														.getUnlocalizedName()));
							}
							return false;
						}
					} else {
						if (output) {
							player.sendMessage(new TextComponentString(
									"Expecting "
											+ ModBlocks.crystal_fire_brick
													.getUnlocalizedName()
											+ " at "
											+ northpos
											+ " but got "
											+ world.getBlockState(northpos)
													.getBlock()
													.getUnlocalizedName()));
						}
						return false;
					}
				} else {
					if (output) {
						player.sendMessage(new TextComponentString("Expecting "
								+ ModBlocks.crystal_fire_brick
										.getUnlocalizedName()
								+ " at "
								+ underpos
								+ " but got "
								+ world.getBlockState(underpos).getBlock()
										.getUnlocalizedName()));
					}
					return false;
				}
			} else {
				if (output) {
					player.sendMessage(new TextComponentString("Expecting "
							+ ModBlocks.crystal_fire_block.getUnlocalizedName()
							+ " at "
							+ startpos
							+ " but got "
							+ world.getBlockState(startpos).getBlock()
									.getUnlocalizedName()));
				}
				return false;
			}
		} else
			return false;
	}

	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn,
			BlockPos pos, EnumHand handIn, EnumFacing facing, float hitX,
			float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			ItemStack offhand = playerIn.getHeldItemOffhand();
			ItemStack mainhand = playerIn.getHeldItemMainhand();
			if (MultiblockRecipes.instance().getRecipeResult(mainhand)
					.getItem() != ItemStack.EMPTY.getItem()) {
				if (validMultiblock(pos, worldIn, playerIn, false)) {
					mainhand.shrink(1);
					worldIn.addWeatherEffect(new EntityLightningBolt(worldIn,
							pos.getX(), pos.getY() + 1, pos.getZ(), false));
					worldIn.spawnEntity(new EntityItem(worldIn, playerIn.posX,
							playerIn.posY, playerIn.posZ, MultiblockRecipes
									.instance().getRecipeResult(mainhand)));
					return EnumActionResult.SUCCESS;
				}
			}
		}
		return EnumActionResult.FAIL;
	}
}
