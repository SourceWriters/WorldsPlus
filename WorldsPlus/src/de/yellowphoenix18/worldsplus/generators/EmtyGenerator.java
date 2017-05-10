package de.yellowphoenix18.worldsplus.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class EmtyGenerator extends ChunkGenerator {
	
	public List<BlockPopulator> getDefaultPopulators(World world) {
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();
		
		return populators;
	}
	
	public byte[] generate(World world, Random random, int chunkX, int chunkZ) {
		byte[] blocks = new byte[32768];
		
		return blocks;
	}

}
