package org.battlecraft.piesrgr8.utils;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ErrorUtil {
	
	public static void cantTeleport(Player p, String msg) {
		p.sendMessage(BattlecraftServer.prefixHub + ChatColor.RED + "Can't teleport to " + ChatColor.RED + ChatColor.BOLD + msg);
	}
	
	public static void noPoll(Player p) {
		p.sendMessage(BattlecraftServer.prefixPolls + ChatColor.RED + "There are no polls available at this time!");
	}
	
	public static void generalError(Player p, String msg, Exception e) {
		p.sendMessage(BattlecraftServer.prefixError + ChatColor.RED + "An error has occured with this method. Report it to Piesrgr8!");
		e.printStackTrace();
	}
}
