package com.themastergeneral.ctdmythos.server.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class CrystalGenerator extends WorldGenerator {
	private IBlockState block;
	private int i;

	public CrystalGenerator(IBlockState state, int blockCount) {
		super();
		block = state;
		i = blockCount;
	}

	@Override
	public boolean generate(World world, Random random, BlockPos position) {
		if (world.getBlockState(position).getMaterial() != Material.WATER)
        {
            return false;
        }
        else
        {
            int j = 2;

            for (int k = position.getX() - i; k <= position.getX() + i; ++k)
            {
                for (int l = position.getZ() - i; l <= position.getZ() + i; ++l)
                {
                    int i1 = k - position.getX();
                    int j1 = l - position.getZ();

                    if (i1 * i1 + j1 * j1 <= i * i)
                    {
                        for (int k1 = position.getY() - 2; k1 <= position.getY() + 2; ++k1)
                        {
                            BlockPos blockpos = new BlockPos(k, k1, l);
                            Block block = world.getBlockState(blockpos).getBlock();

                            if (block == Blocks.DIRT || block == Blocks.GRASS)
                            {
                                world.setBlockState(blockpos, this.block, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
	}

}
