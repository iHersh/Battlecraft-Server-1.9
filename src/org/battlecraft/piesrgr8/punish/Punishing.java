package org.battlecraft.piesrgr8.punish;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Punishing implements CommandExecutor {

	BattlecraftServer plugin;

	String exline = "\n";
	String website = ChatColor.GREEN + "www.bcpvp101.enjin.com/forum";

	public Punishing(BattlecraftServer p) {
		this.plugin = p;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// USE DEBUGGING METHOD TO TEST OUT!

		if (cmd.getName().equalsIgnoreCase("kick")) {
			if (!sender.hasPermission("bc.kick")) {
				sender.sendMessage(ChatColor.RED + "You do not have permission to kick someone!");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(BattlecraftServer.prefixPunish + ChatColor.RED + "Please specify a player!");
				return true;
			}
			if (args.length == 1) {
				
				 if (args[0].equalsIgnoreCase(sender.getName())) {
				 sender.sendMessage( BattlecraftServer.prefixPunish +
				 ChatColor.RED + "Really? You're trying to kick yourself?");
				 return true; 
				 }
				 
				Player p = (Player) sender;
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					p.sendMessage(BattlecraftServer.prefixPunish + ChatColor.RED + args[0] + " isnt online!");
					return true;
				} else {
					target.kickPlayer(BattlecraftServer.prefixPunish + exline + ChatColor.YELLOW
							+ "You have been kicked for......" + exline + ChatColor.YELLOW
							+ "Well, it says that there was no reason for being kicked." + exline + ChatColor.YELLOW
							+ "We believe that this is a mistake, and we are sorry. Join back!");
					successful(p);
					Bukkit.broadcastMessage(BattlecraftServer.prefixPunish + target.getDisplayName() + ChatColor.YELLOW
							+ " was kicked by " + p.getDisplayName());
				}
			}
            if (args.length >= 2) {
			Player p = (Player) sender;
			Player target = Bukkit.getServer().getPlayer(args[0]);
				String bc = "";
				for (String message : args) {
					if (message.contains(p.getName())) {
						continue;
					}
					bc = (bc + message + " ");
				}
				
				target.kickPlayer(BattlecraftServer.prefixPunish + exline + ChatColor.YELLOW
						+ "You have been kicked for......" + exline + ChatColor.GREEN + bc + exline + ChatColor.YELLOW
						+ "This means that its not the end of the world. Join back!");
				successful(p);
				Bukkit.broadcastMessage(BattlecraftServer.prefixPunish + target.getDisplayName() + ChatColor.YELLOW
						+ " was kicked by " + p.getDisplayName() + ChatColor.YELLOW + " for " + ChatColor.GREEN + bc);
			}
		}

		if (cmd.getName().equalsIgnoreCase("ban")) {
			if (sender.hasPermission("bc.ban")) {
				if (args.length == 0) {
					sender.sendMessage(BattlecraftServer.prefixPunish + ChatColor.RED + "Please specify a player!");
					return true;
				}
				if (args.length == 1) {
					
					 if (args[0].equalsIgnoreCase(sender.getName())) {
					 sender.sendMessage( BattlecraftServer.prefixPunish +
					 ChatColor.RED + "Really? Your trying to ban yourself?");
					 return true; 
					 }
					 
					Player p = (Player) sender;
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if (target == null) {
						p.sendMessage("That player isnt online!");
						return true;
					} else {
						target.kickPlayer(BattlecraftServer.prefixPunish + exline + ChatColor.YELLOW
								+ "You have been banned for......" + exline + ChatColor.YELLOW
								+ "Well, it says that there was no reason for being banned." + exline + ChatColor.YELLOW
								+ "Report to " + website + ChatColor.YELLOW + " and submit an appeal!" + exline
								+ ChatColor.YELLOW + "Just be sure to take a screenshot of THIS message!");
						target.setBanned(true);
						successful(p);
						Bukkit.broadcastMessage(BattlecraftServer.prefixPunish + target.getDisplayName()
								+ ChatColor.YELLOW + " was banned by " + p.getDisplayName());
					}
				}
				if (args.length >= 2) {
				Player p = (Player) sender;
				Player target = Bukkit.getServer().getPlayer(args[0]);
					String bc = "";
					for (String message : args) {
						if (message.contains(p.getName())) {
							continue;
						}
						bc = (bc + message + " ");
					}
					target.kickPlayer(BattlecraftServer.prefixPunish + exline + ChatColor.YELLOW
							+ "You have been banned for......" + exline + ChatColor.GREEN + bc + exline
							+ ChatColor.YELLOW + "If you believe that you were wrongfully banned, report it!"
							+ exline + ChatColor.YELLOW + "Go to " + website + ChatColor.YELLOW + " and submit and appeal.");
					successful(p);
					Bukkit.broadcastMessage(BattlecraftServer.prefixPunish + target.getDisplayName() + ChatColor.YELLOW
							+ " was banned by " + p.getDisplayName() + ChatColor.YELLOW + " for " + ChatColor.GREEN + bc);
				}
			}
		}
		return true;
	}

	public void successful(Player p) {
		p.sendMessage(BattlecraftServer.prefixPunish + ChatColor.YELLOW + "Your target has been delt with!");
	}
}