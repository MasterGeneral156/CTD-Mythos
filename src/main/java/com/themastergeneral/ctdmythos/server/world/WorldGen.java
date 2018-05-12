package com.themastergeneral.ctdmythos.server.world;

import java.util.Random;

import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.blocks.ModBlocks;
import com.themastergeneral.ctdmythos.common.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator {

	private WorldGenerator gen_fire;
	private WorldGenerator gen_oath;
	private WorldGenerator gen_woe;
	private WorldGenerator gen_memory;
	private WorldGenerator gen_grief;

	public WorldGen() {
		this.gen_fire = new WorldGenMinable(
				ModBlocks.crystal_fire_ore.getDefaultState(),
				ModConfig.crystalSpawnChance);
		this.gen_oath = new WorldGenMinable(
				ModBlocks.crystal_oath_ore.getDefaultState(),
				ModConfig.crystalSpawnVeinSize);
		this.gen_memory = new WorldGenMinable(
				ModBlocks.crystal_memory_ore.getDefaultState(),
				ModConfig.crystalSpawnVeinSize);
		this.gen_grief = new WorldGenMinable(
				ModBlocks.crystal_grief_ore.getDefaultState(),
				ModConfig.crystalSpawnVeinSize);
		this.gen_woe = new WorldGenMinable(
				ModBlocks.crystal_woe_ore.getDefaultState(),
				ModConfig.crystalSpawnVeinSize);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 0: // Overworld
			this.runGenerator(this.gen_oath, world, random, chunkX, chunkZ,
					ModConfig.crystalSpawnChance, ModConfig.crystalSpawnMinY,
					ModConfig.crystalSpawnMaxY);
			this.runGenerator(this.gen_woe, world, random, chunkX, chunkZ,
					ModConfig.crystalSpawnChance, ModConfig.crystalSpawnMinY,
					ModConfig.crystalSpawnMaxY);
			this.runGenerator(this.gen_grief, world, random, chunkX, chunkZ,
					ModConfig.crystalSpawnChance, ModConfig.crystalSpawnMinY,
					ModConfig.crystalSpawnMaxY);
			break;
		case -1: // Nether
			this.runGenerator(this.gen_fire, world, random, chunkX, chunkZ,
					ModConfig.crystalSpawnChance, 1, 128);
		case 1: // The End
			this.runGenerator(this.gen_memory, world, random, chunkX, chunkZ,
					ModConfig.crystalSpawnChance, ModConfig.crystalSpawnMinY,
					ModConfig.crystalSpawnMaxY);
			break;
		}

	}

	private void runGenerator(WorldGenerator generator, World world,
			Random rand, int chunk_X, int chunk_Z, int chancesToSpawn,
			int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			CTDMythos.logger
					.error("Illegal height arguments for WorldGenerator.");
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			BlockPos Pos = new BlockPos(x, y, z);
			generator.generate(world, rand, Pos);
			CTDMythos.logger.info("Spawned at " + Pos);
		}
	}

}
