package org.battlecraft.piesrgr8.fake;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SilentJoin implements Listener {

	BattlecraftServer plugin;

	public SilentJoin(BattlecraftServer p) {
		this.plugin = p;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (!e.getPlayer().hasPermission("bc.silentjoin")) {
			System.out.println("Must have the permission first.");
			return;
		}
		if (e.getPlayer().isOp()) {
			e.setJoinMessage("A silent joiner is here");
		}
		e.setJoinMessage(null);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent e) {
		if (!e.getPlayer().hasPermission("bc.silentquit")) {
			System.out.println("Must have the permission first.");
			return;
		}
		e.setQuitMessage(null);
	}
}
