package org.battlecraft.piesrgr8.config;

import java.io.File;
import java.io.IOException;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
	
	BattlecraftServer plugin;
	
	public ConfigManager(BattlecraftServer p) {
		this.plugin = p;
	}
	
	public void saveConfig(String name, FileConfiguration config) {
		if (!name.endsWith(".yml")) {
			name = name + ".yml";
		}
		
		File file = new File(plugin.getDataFolder(), name);
		try {
			config.save(file);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
