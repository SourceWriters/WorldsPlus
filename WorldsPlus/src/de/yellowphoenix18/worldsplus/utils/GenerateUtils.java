package de.yellowphoenix18.worldsplus.utils;

import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;

import de.yellowphoenix18.worldsplus.WorldsPlus;
import de.yellowphoenix18.worldsplus.config.MessagesConfig;
import de.yellowphoenix18.worldsplus.config.WorldConfig;

public class GenerateUtils {
	
	public static void createWorld(Player p, String name, String generator, String pgenerator, String seed, String id, boolean create) {
		WorldCreator w = new WorldCreator(name);
		boolean failed = false;
		
		if(pgenerator != null) {
			if(!pgenerator.startsWith("plugin:")) {
				pgenerator = pgenerator.toLowerCase();
			}
		} else {
			pgenerator = "";
		}
		
		generator = generator.toLowerCase();
		
		if(seed != null) {
			w.seed(Long.valueOf(seed));
		} else {	
			seed = w.seed() + "";
		}
		
		if(pgenerator.startsWith("plugin:")) {
			String pxgen = pgenerator.replace("plugin:", "");
			Plugin gen = WorldsPlus.m.getServer().getPluginManager().getPlugin(pxgen);
			if(gen != null) {
				ChunkGenerator cgen = gen.getDefaultWorldGenerator(name, id);
				w.generator(cgen);
			} else {
				failed = true;
			}
		}
		
		 if(pgenerator.equalsIgnoreCase("empty")) {
				ChunkGenerator cgen = WorldsPlus.m.getDefaultWorldGenerator(name, "empty");
				w.generator(cgen);
		} else if(pgenerator.equalsIgnoreCase("flatland")) {
				ChunkGenerator cgen = WorldsPlus.m.getDefaultWorldGenerator(name, "flatland");
				w.generator(cgen);
		} else if(generator.equalsIgnoreCase("normal")) {
			w.environment(Environment.NORMAL);
		} else if(generator.equalsIgnoreCase("nether")) {
			w.environment(Environment.NETHER);
		} else if(generator.equalsIgnoreCase("end")) {
			w.environment(Environment.THE_END);
		}
		
		if(failed == false) {
			if(create == true) {
				WorldConfig.addWorld(name, generator, pgenerator, id, seed);
			}
			if(p != null) {
				String pgen_info = "";
				if(!pgenerator.equalsIgnoreCase("")) {
					if(pgenerator.startsWith("plugin:")) {
						pgen_info = "§7Plugin: §c" + pgenerator.replace("plugin:", "") + " §7, Generator: §c" + generator;
					} else {
						pgen_info =  "Generator: §c" + pgenerator;
					}
				} else {
					
				}
				p.sendMessage(MessagesConfig.prefix + "§7Generating World §a" + name + " §7with " + pgen_info + " §7and Seed: §c" + seed);
			}
			w.createWorld();
			WorldCreateThread thread = new WorldCreateThread(p, name);
			thread.start();
		}
	}

}
