package de.yellowphoenix18.worldsplus.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.yellowphoenix18.worldsplus.config.MessagesConfig;
import de.yellowphoenix18.worldsplus.config.WorldConfig;

public class WorldCreateThread extends Thread {
	
	private int trys = 0;
	private Player p;
	private boolean created = false;
	private String wname;
	
	public WorldCreateThread(Player p, String name) {
		this.p = p;
		this.wname = name;
	}
	
	public void run() {
		while(trys <= 100 && created == false) {
			trys++;
			if(Bukkit.getWorld(wname) != null) {
				this.created = true;
				WorldConfig.setEnabled(wname, true);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(trys > 100) {
			if(p != null) {
				p.sendMessage(MessagesConfig.prefix + "§cWorld-Creation failed");
			}
		}
		if(created == true) {
			if(p != null) {
				p.sendMessage(MessagesConfig.prefix + "§7World §a" + wname + " §7successfully created");
			}
		}
	}

}
