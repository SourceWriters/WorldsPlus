package de.yellowphoenix18.worldsplus.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class WorldConfig {
	
	public static File f = new File("plugins/WorldsPlus", "worlds.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static List<String> getWorlds() {
		List<String> worlds = new ArrayList<String>();
		if(cfg.contains("All-Worlds")) {
			worlds = cfg.getStringList("All-Worlds");
		}
		return worlds;
	}
	
	public static boolean isEnabled(String name) {
		boolean enabled = false;
		if(cfg.contains("Worlds." + name + ".Enabled")) {
			enabled = cfg.getBoolean("Worlds." + name + ".Enabled");
		}
		return enabled;
	}
	
	public static void setEnabled(String world, boolean enabled) {
		cfg.set("Worlds." + world + ".Enabled", enabled);
		save();
	}
	
	public static String getGenerator(String name) {
		String generator = "normal";
		if(cfg.contains("Worlds." + name + ".Generator")) {
			generator = cfg.getString("Worlds." + name + ".Generator");
		}
		return generator;
	}
	
	public static String getPluginGenerator(String name) {
		String generator = "normal";
		if(cfg.contains("Worlds." + name + ".PGenerator")) {
			generator = cfg.getString("Worlds." + name + ".PGenerator");
		}
		return generator;
	}
	
	public static String getSeed(String name) {
		String seed = null;
		if(cfg.contains("Worlds." + name + ".Seed")) {
			seed = cfg.getString("Worlds." + name + ".Seed");
		}
		return seed;
	}
	
	public static String getGeneratorID(String name) {
		String genid = "";
		if(cfg.contains("Worlds." + name + ".Gen-ID")) {
			genid = cfg.getString("Worlds." + name + ".Gen-ID");
		}
		return genid;
	}
	
	public static void addWorld(String name, String generator, String pgenerator, String id, String seed) {
		List<String> worlds = getWorlds();
		worlds.add(name);
		cfg.set("All-Worlds", worlds);
		cfg.set("Worlds." + name + ".PGenerator", pgenerator);
		cfg.set("Worlds." + name + ".Generator", generator);
		cfg.set("Worlds." + name + ".Gen-ID", id);
		cfg.set("Worlds." + name + ".Seed", seed);	
		cfg.set("Worlds." + name + ".Enabled", false);
		save();
	}
	
	
	
	
	
	public static void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
