package de.yellowphoenix18.worldsplus.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.yellowphoenix18.worldsplus.config.MessagesConfig;
import de.yellowphoenix18.worldsplus.utils.GenerateUtils;

public class WorldsPlusCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				
			} else if(args.length > 0) {
				if(args[0].equalsIgnoreCase("create")) {
					if(p.hasPermission("worldsplus.create") || p.hasPermission("worldsplus.*")) {
						if(args.length >= 2) {
							String world = args[1];
							String generator = "normal";
							String pgenerator = null;
							String generatorid = null;
							String seed = null;
							
							boolean failed = false;
							
							int arg = 2;
							while(arg < args.length) {
								if(args[arg].startsWith("seed:")) {
									seed = args[arg].replace("seed:", "");
								} else if(args[arg].startsWith("plugin:")) {
									pgenerator = args[arg];
								} else if(args[arg].startsWith("id:")) {
									generatorid = args[arg].replace("id:", "");
								} else if(args[arg].equalsIgnoreCase("normal")) {
									generator = "normal";
								} else if(args[arg].equalsIgnoreCase("nether")) {
									generator = "nether";
								} else if(args[arg].equalsIgnoreCase("end")) {
									generator = "end";
								} else if(args[arg].equalsIgnoreCase("empty")) {
									pgenerator = "empty";
								} else if(args[arg].equalsIgnoreCase("flatland")) {
									pgenerator = "flatland";
								} else {
									failed = true;
								}
								arg++;
							}
							if(failed == false) {
								GenerateUtils.createWorld(p, world, generator, pgenerator, seed, generatorid, true);
							} else {
								p.sendMessage(MessagesConfig.prefix + MessagesConfig.invalid_arguments);
							}
						}
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else if(args[0].equalsIgnoreCase("goto")) {
					if(p.hasPermission("worldsplus.goto") || p.hasPermission("worldsplus.*")) {
						if(args.length == 2) {
							String world = args[1];
							if(Bukkit.getWorld(world) != null) {
								p.teleport(Bukkit.getWorld(world).getSpawnLocation());
								p.sendMessage(MessagesConfig.prefix + MessagesConfig.moved_to_world.replace("%World%", world));
							} else {
								p.sendMessage(MessagesConfig.prefix + MessagesConfig.invalid_world);
							}
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.too_less_arguments.replace("%Command%", "/wp goto <World>"));
						}
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else if(args[0].equalsIgnoreCase("move")) {
					if(p.hasPermission("worldsplus.move") || p.hasPermission("worldsplus.*")) {
						if(args.length == 3) {
							String player = args[1];
							String world = args[2];
							if(Bukkit.getPlayerExact(player) != null) {
								Player k = Bukkit.getPlayerExact(player);
								if(Bukkit.getWorld(world) != null) {
									k.teleport(Bukkit.getWorld(world).getSpawnLocation());
									p.sendMessage(MessagesConfig.prefix + MessagesConfig.player_moved_to_world.replace("%Player%", k.getName()).replace("%World%", world));
								} else {
									p.sendMessage(MessagesConfig.prefix + MessagesConfig.invalid_world);
								}
							} else {
								p.sendMessage(MessagesConfig.prefix + MessagesConfig.invalid_player);
							}
						} else {
							p.sendMessage(MessagesConfig.prefix + MessagesConfig.too_less_arguments.replace("%Command%", "/wp goto <World>"));
						}
					} else {
						p.sendMessage(MessagesConfig.prefix + MessagesConfig.no_permission);
					}
				} else if(args[0].equalsIgnoreCase("help")) {
					p.sendMessage("   §8» §3Worlds§bPlus §8«   ");
					p.sendMessage("§6/wp help §8- §7Shows all Commands");
					p.sendMessage("§6/wp create <Name> [nether/normal/end/flatland/empty] [plugin:<Plugin>] [id:<Gen-ID>] [seed:<Seed>] §8- §7Creates an specified World");
					p.sendMessage("§6/wp goto <World> §8- §7Moves you to an specified World");
					p.sendMessage("§6/wp move <Player> <World> §8- §7Moves an Player to an specified World");
					p.sendMessage("   §8» §3Worlds§bPlus §8«   ");
				}
			}
		}
		
		
		
		return true;
	}

}
