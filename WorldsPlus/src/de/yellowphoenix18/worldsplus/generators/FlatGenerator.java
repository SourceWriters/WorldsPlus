package de.yellowphoenix18.worldsplus.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class FlatGenerator extends ChunkGenerator {
	
	public List<BlockPopulator> getDefaultPopulators(World world) {
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();
		
		return populators;
	}
	
	public ChunkData generateChunkData(World world, Random random, int ChunkX, int ChunkZ, BiomeGrid biome) {
		ChunkData data = createChunkData(world);
		
		for(int x = 0; x < 16; x++) {
			for(int z = 0; z < 16; z++) {
				for(int y = 0; y < 64; y++) {
					if(y <= 1) {
						data.setBlock(x, y, z, Material.BEDROCK);
					} else if(y < 59) {
						data.setBlock(x, y, z, Material.STONE);
					} else if(y < 63) {
						data.setBlock(x, y, z, Material.DIRT);
					} else {
						data.setBlock(x, y, z, Material.GRASS);
					}
				}
			}
		}
		
		return data;
	}

}
