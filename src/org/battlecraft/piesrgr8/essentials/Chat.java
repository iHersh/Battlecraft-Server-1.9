package org.battlecraft.piesrgr8.essentials;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.ezprestige.EZPrestige;

public class Chat implements Listener{
	
	BattlecraftServer plugin;
	
	public Chat(BattlecraftServer p) {
		this.plugin = p;
	}
	
	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String ptop = EZPrestige.getOptions().getOpPrestigeChatTag();
		String pt = EZPrestige.getOptions().getPrestigeRank();
		String pno = EZPrestige.getOptions().getNoPrestigeChatTag();
		
		if (p.hasPermission("bc.prefix.owner")) {
			e.setFormat(ptop + " " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "OWNER " + ChatColor.RESET + p.getName() + " " +
		ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.admin")) {
			e.setFormat(pt + " " + ChatColor.RED + "" + ChatColor.BOLD + "ADMIN " + ChatColor.RESET + p.getName() + " " +
					ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.mod")) {
			e.setFormat(pt + " " + ChatColor.GOLD + "" + ChatColor.BOLD + "MOD " + ChatColor.RESET + p.getName() + " " +
					ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.helper")) {
			e.setFormat(pt + " " + ChatColor.GREEN + "" + ChatColor.BOLD + "HELPER " + ChatColor.RESET + p.getName() + " " +
					ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.builder")) {
			e.setFormat(pt + " " + ChatColor.BLUE + "" + ChatColor.BOLD + "BUILDER " + ChatColor.RESET + p.getName() + " " +
					ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else {
			e.setFormat(pno + " " + ChatColor.GRAY + "" + ChatColor.ITALIC + "Player " + ChatColor.RESET + p.getName() + " " +
					ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		}
	}

}
