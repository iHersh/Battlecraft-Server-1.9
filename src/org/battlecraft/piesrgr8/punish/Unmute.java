package org.battlecraft.piesrgr8.punish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Unmute implements CommandExecutor{
	
	
	String prefix = ChatColor.RED + "" + ChatColor.BOLD
			+ "BC" + ChatColor.BLUE + "" + ChatColor.BOLD + "Punish" + ChatColor.WHITE + " - ";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	
	if (cmd.getLabel().equalsIgnoreCase("unmute")){
		if(!sender.hasPermission("bc.unmute")) {
			sender.sendMessage(prefix + ChatColor.RED + "You don't have permission to mute people!");
			return true;
		}
		
				if (args.length == 0) {
					sender.sendMessage(prefix + ChatColor.RED + "Please specify a player!");
					return true;
				}
	            for (Player target : Bukkit.getOnlinePlayers()) {
	            if (target == null) {
	                    sender.sendMessage(prefix + ChatColor.RED + "Could not find player " + args[0] + "!");
	            }else{
	            Mute.muted.remove(target);
	            Bukkit.getServer().getPluginManager().callEvent(new Misc(target, Type.UNMUTE));
	            Bukkit.getServer().broadcastMessage(prefix + ChatColor.YELLOW + "Player " + target.getName() + " has been unmuted by " + sender.getName() + "!");
	            
	            }
	            }
	}
	return false;
	}
}
	            
	            
	            
	            