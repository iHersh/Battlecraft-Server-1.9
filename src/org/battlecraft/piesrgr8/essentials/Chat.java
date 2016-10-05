package org.battlecraft.piesrgr8.essentials;

import java.io.File;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Chat implements Listener {

	BattlecraftServer plugin;
	
	File f = new File("plugins/BattlecraftServer/players.yml");
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);

	public Chat(BattlecraftServer p) {
		this.plugin = p;
	}
	
	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if (yaml.contains(p.getName() + ".nick")) {
			p.setDisplayName("*" + ChatColor.translateAlternateColorCodes('&', yaml.getString(p.getName() + ".nick")));
		}
		
		e.setFormat(ChatColor.translateAlternateColorCodes('&', PermissionsEx.getUser(p).getPrefix() + " " + p.getDisplayName() + " " +
		ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage()));

		/*
		if (p.hasPermission("bc.prefix.owner") && p.isOp()) {
			e.setFormat(ChatColor.DARK_RED + "" + ChatColor.BOLD + "OWNER " + ChatColor.YELLOW + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.cowner") && p.isOp()) {
			e.setFormat(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "CO-OWNER " + ChatColor.YELLOW + p.getName()
					+ " " + ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.dev")&& p.isOp()) {
			e.setFormat(ChatColor.RED + "" + ChatColor.BOLD + "DEV " + ChatColor.YELLOW + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.admin") && p.isOp()) {
			e.setFormat(ChatColor.RED + "" + ChatColor.BOLD + "ADMIN " + ChatColor.YELLOW + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.mod") && p.isOp()) {
			e.setFormat(ChatColor.GOLD + "" + ChatColor.BOLD + "MOD " + ChatColor.YELLOW + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.helper") && p.isOp()) {
			e.setFormat(ChatColor.GREEN + "" + ChatColor.BOLD + "HELPER " + ChatColor.YELLOW + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else if (p.hasPermission("bc.prefix.builder") && p.isOp()) {
			e.setFormat(ChatColor.BLUE + "" + ChatColor.BOLD + "BUILDER " + ChatColor.YELLOW + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		} else {
			e.setFormat(ChatColor.GRAY + "" + ChatColor.ITALIC + "Player " + ChatColor.YELLOW + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		}

		if (p.hasPermission("bc.prefix.owner")) {
			e.setFormat(ChatColor.DARK_RED + "" + ChatColor.BOLD + "OWNER " + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.admin")) {
			e.setFormat(ChatColor.RED + "" + ChatColor.BOLD + "ADMIN " + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.dev")) {
			e.setFormat(ChatColor.RED + "" + ChatColor.BOLD + "DEV " + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.mod")) {
			e.setFormat(ChatColor.GOLD + "" + ChatColor.BOLD + "MOD " + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.helper")) {
			e.setFormat(ChatColor.GREEN + "" + ChatColor.BOLD + "HELPER " + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.builder")) {
			e.setFormat(ChatColor.BLUE + "" + ChatColor.BOLD + "BUILDER " + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.premium")) {
			e.setFormat(ChatColor.GOLD + "" + ChatColor.BOLD + "PREMIUM " + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.master")) {
			e.setFormat(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "MASTER " + ChatColor.RESET + p.getName()
					+ " " + ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.vip2")) {
			e.setFormat(ChatColor.GOLD + "" + ChatColor.BOLD + "+" + ChatColor.GREEN + "" + ChatColor.BOLD + "VIP"
					+ ChatColor.GOLD + "" + ChatColor.BOLD + "+ " + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.vip1")) {
			e.setFormat(ChatColor.GREEN + "" + ChatColor.BOLD + "VIP" + ChatColor.GOLD + "" + ChatColor.BOLD + "+ "
					+ ChatColor.RESET + p.getName() + " " + ChatColor.GRAY + "" + ChatColor.BOLD + "> "
					+ ChatColor.RESET + e.getMessage());
			
		} else if (p.hasPermission("bc.prefix.vip")) {
			e.setFormat(ChatColor.GREEN + "" + ChatColor.BOLD + "VIP" + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
			
		} else {
			e.setFormat(ChatColor.GRAY + "" + ChatColor.ITALIC + "Player " + ChatColor.RESET + p.getName() + " "
					+ ChatColor.GRAY + "" + ChatColor.BOLD + "> " + ChatColor.RESET + e.getMessage());
		}
		*/
	}

}
