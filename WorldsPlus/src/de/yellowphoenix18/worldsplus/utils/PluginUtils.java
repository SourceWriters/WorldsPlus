package de.yellowphoenix18.worldsplus.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import de.yellowphoenix18.worldsplus.WorldsPlus;
import de.yellowphoenix18.worldsplus.commands.WorldsPlusCommand;
import de.yellowphoenix18.worldsplus.config.MessagesConfig;
import de.yellowphoenix18.worldsplus.config.WorldConfig;

public class PluginUtils implements Listener {
	
	public static void setUp() {
		loadConfigs();
		loadWorlds();
		loadCommands();
	}
	
	public static void loadCommands() {
		WorldsPlus.m.getCommand("wp").setExecutor(new WorldsPlusCommand());
	}
	
	public static void loadConfigs() {
		MessagesConfig.load();
	}
	
	public static void loadWorlds() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(WorldsPlus.m, new Runnable() {
			@Override
			public void run() {
				for(String world : WorldConfig.getWorlds()) {
					if(WorldConfig.isEnabled(world)) {
						GenerateUtils.createWorld(null, world, WorldConfig.getGenerator(world), WorldConfig.getPluginGenerator(world), WorldConfig.getSeed(world), WorldConfig.getGeneratorID(world), false);
					}
				}
			}	
		}, 1);
	}

}
