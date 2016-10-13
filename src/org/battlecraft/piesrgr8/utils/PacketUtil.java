package org.battlecraft.piesrgr8.utils;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PacketUtil implements Listener {

	BattlecraftServer plugin;

	public PacketUtil(BattlecraftServer p) {
		this.plugin = p;
	}

	@EventHandler
	public static void onJoin(final BattlecraftServer plugin, final Player p) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				TitleManager.sendTitle(p.getPlayer(), ChatColor.translateAlternateColorCodes('&', "&c&lBATTLECRAFT"),
						ChatColor.translateAlternateColorCodes('&', "&aWelcome to the server, " + p.getName()), 40);
			}
		}, 20);
	}

	@EventHandler
	public static void sendTitle(final BattlecraftServer plugin, final Player p, final String msg, final String msg1) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				TitleManager.sendTitle(p, msg, msg1, 70);
			}
		}, 20);
	}

	@EventHandler
	public static void sendActionMsg(final BattlecraftServer plugin, final Player p, final String msg) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				TitleManager.sendActionBar(p, msg, 70);
			}
		}, 20);
	}
}