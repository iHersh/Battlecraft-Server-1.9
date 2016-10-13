package org.battlecraft.piesrgr8.config;

import java.io.File;
import java.io.IOException;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.configuration.file.YamlConfiguration;

public class Spawning {

	BattlecraftServer plugin;

	static File f = new File("plugins/BattlecraftServer/spawns.yml");
	static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);

	public Spawning(BattlecraftServer p) {
		this.plugin = p;
	}

	public static void saveSpawnYaml(BattlecraftServer plugin) {
		if (!new File(plugin.getDataFolder(), "spawns.yml").exists()) {
			try {
				new File(plugin.getDataFolder(), "spawns.yml").createNewFile();
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

	public static void saveWarpYaml(BattlecraftServer plugin) {
		if (!new File(plugin.getDataFolder(), "warps.yml").exists()) {
			try {
				new File(plugin.getDataFolder(), "warps.yml").createNewFile();
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
}
