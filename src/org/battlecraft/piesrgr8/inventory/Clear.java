package org.battlecraft.piesrgr8.inventory;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Clear implements CommandExecutor{
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clear")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("You are not a player!");
				return true;
			}
			Player p = (Player) sender;
			
			if (!p.hasPermission("bc.clear")) {
				p.sendMessage(BattlecraftServer.prefixInv + ChatColor.RED + "You dont have permission to clear your inventory!");
				return true;
			}
			
			if (args.length == 0) {
				p.sendMessage(BattlecraftServer.prefixInv + ChatColor.GREEN + "Inventory cleared!");
				p.getInventory().clear();
				return true;
			}
			
			if (args.length == 1) {
				Player tar = Bukkit.getServer().getPlayer(args[0]);
				Inventory inv = tar.getInventory();
					p.sendMessage(BattlecraftServer.prefixInv + ChatColor.RED + "This player isnt online.");
				
				p.sendMessage(BattlecraftServer.prefixInv + ChatColor.GREEN + tar.getName() + "'s inventory has been cleared!");
				inv.clear();;
				return true;
				}
			}
		return true;
	}

}