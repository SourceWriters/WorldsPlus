package de.yellowphoenix18.worldsplus.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MessagesConfig {
	
	public static File f = new File("plugins/WorldsPlus", "messages.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public static String prefix = "&8[&3Worlds&bPlus&8] &6";
	public static String invalid_arguments = "&cSome Arguments are invalid, please try again!";
	public static String too_less_arguments = "&7Too much/less arguments. Please try &c%Commands%";
	public static String invalid_world = "&cInvalid World!";
	public static String invalid_player = "&cInvalid Player!";
	public static String invalid_pgenerator = "&cInvalid Plugin-Generator!";
	
	public static String player_moved_to_world = "&7You successfully moved &a%Player% &7to World &c%World%&7!";
	public static String moved_to_world = "&7You successfully moved to World &c%World%&7!";
	
	public static String no_permission = "&cYoure not permitted to use this command";
	
	public static void load() {
		prefix = fixColorCodes(setObject("WorldPlus.Prefix", prefix));
		invalid_arguments = fixColorCodes(setObject("WorldPlus.Error.Invalid-Arguments", invalid_arguments));
		too_less_arguments = fixColorCodes(setObject("WorldPlus.Error.Arguments-Error", too_less_arguments));
		invalid_world = fixColorCodes(setObject("WorldPlus.Error.Invalid-World", invalid_world));
		invalid_player = fixColorCodes(setObject("WorldPlus.Error.Invalid-Player", invalid_player));
		invalid_pgenerator = fixColorCodes(setObject("WorldPlus.Error.Invalid-PGenerator", invalid_pgenerator));
		no_permission = fixColorCodes(setObject("WorldPlus.Error.No-Permission", no_permission));
		
		player_moved_to_world = fixColorCodes(setObject("WorldPlus.Moved.Player", player_moved_to_world));
		moved_to_world = fixColorCodes(setObject("WorldPlus.Moved.Goto", moved_to_world));
	}
	
	public static String fixColorCodes(String code) {
		code = code.replace("&", "§");
		return code;
	}
	
	public static String setObject(String path, String obj) {
		if(cfg.contains(path)) {
			return cfg.getString(path);
		} else {
			cfg.set(path, obj);
			save();
			return obj;
		}
	}
	
	public static void save() {
		try {
			cfg.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
