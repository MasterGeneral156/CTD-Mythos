package com.themastergeneral.ctdmythos.tileentity;

import java.util.Random;

import javax.annotation.Nullable;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.processing.MultiblockRecipes;
import com.themastergeneral.ctdmythos.network.PacketRequestUpdatePedestal;
import com.themastergeneral.ctdmythos.network.PacketUpdatePedestal;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class PedestalTileEntity extends TileEntity implements ITickable {

	public ItemStackHandler inventory = new ItemStackHandler(1) {
		protected void onContentsChanged(int slot) {
			if (!world.isRemote) {
				lastChangeTime = world.getTotalWorldTime();
				CTDMythos.wrapper.sendToAllAround(
						new PacketUpdatePedestal(PedestalTileEntity.this),
						new NetworkRegistry.TargetPoint(world.provider
								.getDimension(), pos.getX(), pos.getY(), pos
								.getZ(), 64));
			}
		}
	};
	public long lastChangeTime;
	public int tickup;

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setLong("lastChangeTime", lastChangeTime);
		compound.setInteger("tickup", tickup);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		lastChangeTime = compound.getLong("lastChangeTime");
		tickup = compound.getInteger("tickup");
		super.readFromNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability,
			@Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
				|| super.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability,
			@Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory
				: super.getCapability(capability, facing);
	}

	@Override
	public void onLoad() {
		if (world.isRemote) {
			CTDMythos.wrapper
					.sendToServer(new PacketRequestUpdatePedestal(this));
		}
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
	}

	public int getTicks() {
		return tickup;
	}

	public void incrementTicks() {
		tickup++;
		markDirty();
	}

	public void decrementTicks() {
		tickup--;
		markDirty();
	}

	public void resetTicks() {
		tickup = 0;
		markDirty();
	}

	@Override
	public void update() {
		if (validMB()) {
			if (validItem()) {
				incrementTicks();
				if (getTicks() == 200 * inventory.getStackInSlot(0).getCount()) {
					int newtotal = inventory.getStackInSlot(0).getCount()
							* getOutput().getCount();
					ItemStack Output = new ItemStack(getOutput().getItem(),
							newtotal, getOutput().getMetadata());
					world.addWeatherEffect(new EntityLightningBolt(world, pos
							.getX(), pos.getY() + 1, pos.getZ(), false));
					this.inventory.setStackInSlot(0, Output);
					Random randomGenerator = new Random();
					int actualheal = randomGenerator.nextInt(100);
					if (actualheal < 3) {
						world.setBlockState(new BlockPos(pos.getX(),
								pos.getY() - 1, pos.getZ()), Blocks.BRICK_BLOCK
								.getDefaultState());
						world.setBlockState(
								new BlockPos(pos.getX() + 1, pos.getY() - 1,
										pos.getZ()), Blocks.BRICK_BLOCK
										.getDefaultState());
						world.setBlockState(
								new BlockPos(pos.getX() - 1, pos.getY() - 1,
										pos.getZ()), Blocks.BRICK_BLOCK
										.getDefaultState());
						world.setBlockState(new BlockPos(pos.getX(),
								pos.getY() - 1, pos.getZ() + 1),
								Blocks.BRICK_BLOCK.getDefaultState());
						world.setBlockState(new BlockPos(pos.getX(),
								pos.getY() - 1, pos.getZ() - 1),
								Blocks.BRICK_BLOCK.getDefaultState());
					}
					resetTicks();
				}
			} else {
				resetTicks();
			}
		} else {
			resetTicks();
		}
	}

	public boolean validItem() {
		if (MultiblockRecipes.instance()
				.getRecipeResult(this.inventory.getStackInSlot(0)).getItem() != ItemStack.EMPTY
				.getItem())
			return true;
		else
			return false;
	}

	public ItemStack getOutput() {
		return MultiblockRecipes.instance().getRecipeResult(
				this.inventory.getStackInSlot(0));
	}

	public boolean validMB() {
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
			if (world.getBlockState(startpos).getBlock() == ModBlocks.pedestal_block) {
				if (world.getBlockState(underpos).getBlock() == ModBlocks.crystal_fire_brick) {
					if (world.getBlockState(northpos).getBlock() == ModBlocks.crystal_fire_brick) {
						if (world.getBlockState(southpos).getBlock() == ModBlocks.crystal_fire_brick) {
							if (world.getBlockState(eastpos).getBlock() == ModBlocks.crystal_fire_brick) {
								if (world.getBlockState(westpos).getBlock() == ModBlocks.crystal_fire_brick) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

}
