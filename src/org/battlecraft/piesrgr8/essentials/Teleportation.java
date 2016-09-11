package org.battlecraft.piesrgr8.essentials;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleportation implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(BattlecraftServer.prefixMain + ChatColor.RED + "You are not a player!");
				return true;
			}
			Player p = (Player) sender;
			if (!p.hasPermission("bc.teleport")) {
				p.sendMessage(BattlecraftServer.prefixMain + ChatColor.RED + "You dont have permission to teleport.");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage(BattlecraftServer.prefixMain + ChatColor.RED + "Arguments are: /tp <player : coordinates : all");
				return true;
			}
			if (args.length >= 1) {
				Player tar = Bukkit.getServer().getPlayer(args[0]);
				
				if (tar == null) {
					p.sendMessage(BattlecraftServer.prefixMain + ChatColor.RED + "That player isnt on the server!");
					return true;
				}
				if (tar.getName().equalsIgnoreCase(args[0])) {
					p.teleport(new Location(tar.getWorld(), tar.getLocation().getX(), tar.getLocation().getY(), tar.getLocation().getZ()));
					p.sendMessage(BattlecraftServer.prefixMain + ChatColor.GREEN + "Teleported to " + ChatColor.YELLOW + tar.getName());
					return true;
				}
			}
		}
		return true;
	}
}
