package org.battlecraft.piesrgr8.utils;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RestartCommand implements CommandExecutor {

	BattlecraftServer plugin;

	public RestartCommand(BattlecraftServer p) {
		this.plugin = p;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("restart")) {
			Debug.debugBroadcast("SERVER RECOGNIZED /RESTART");
			reloadMessage();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					Bukkit.getServer().shutdown();
				}
			}, 200L);
		}

		if (cmd.getName().equalsIgnoreCase("reload")) {
			Debug.debugBroadcast("SERVER RECOGNIZED /RELOAD");
			reloadMessage();
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					Bukkit.getServer().reload();
				}
			}, 200L);
		}
		return true;
	}

	public void reloadMessage() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				Bukkit.broadcastMessage(BattlecraftServer.prefixMain + "SERVER RESTARTING!");
			}
		}, 60L, 200L);
	}
}
