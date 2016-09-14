package org.battlecraft.piesrgr8.config;

import java.io.File;
import java.io.IOException;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
	
	static File f = new File("plugins/BattlecraftServer/issues.yml");
	static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
	
	static File f1 = new File("plugins/BattlecraftServer/reports.yml");
	static YamlConfiguration yaml1 = YamlConfiguration.loadConfiguration(f1);
	
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
	
	public static void saveIssueYaml(BattlecraftServer plugin) {
		if (!new File(plugin.getDataFolder(), "issues.yml").exists()) {
			try {
				new File(plugin.getDataFolder(), "issues.yml").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			yaml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveReportYaml(BattlecraftServer plugin) {
		if (!new File(plugin.getDataFolder(), "reports.yml").exists()) {
			try {
				new File(plugin.getDataFolder(), "reports.yml").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			yaml1.save(f1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
