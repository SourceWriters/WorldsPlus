package de.yellowphoenix18.worldsplus;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import de.yellowphoenix18.worldsplus.generators.EmtyGenerator;
import de.yellowphoenix18.worldsplus.generators.FlatGenerator;
import de.yellowphoenix18.worldsplus.utils.PluginUtils;

public class WorldsPlus extends JavaPlugin {
	
	public static WorldsPlus m;
	
	public void onEnable() {
		m = this;
		PluginUtils.setUp();
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String uid) {
		if(uid.equalsIgnoreCase("empty")) {
			return new EmtyGenerator();
		} else if(uid.equalsIgnoreCase("flatland")) {
			return new FlatGenerator();
		} else {
			return new EmtyGenerator();
		}
	}

}
