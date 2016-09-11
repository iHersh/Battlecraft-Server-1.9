package org.battlecraft.piesrgr8.players;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.utils.PacketUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Piesrgr8 implements Listener {

	BattlecraftServer plugin;

	public Piesrgr8(BattlecraftServer p) {
		this.plugin = p;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void piesOn(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("bc.silentjoin")) {
			return;
		}

		if (e.getPlayer().getName().equals("Piesrgr8")) {
			e.setJoinMessage(ChatColor.BLUE + e.getPlayer().getName() + ChatColor.GOLD + " has joined the server!");
			
			for (Player pl : Bukkit.getOnlinePlayers()) {
				PacketUtil.sendActionMsg(plugin, pl, ChatColor.BLUE + "Piesrgr8: " + ChatColor.GOLD + "Im here to save the day!");
			}
		}
	}
}