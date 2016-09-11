package org.battlecraft.piesrgr8.punish;

import java.util.ArrayList;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Mute implements CommandExecutor{
	
	public static BattlecraftServer plugin;
	
	public static ArrayList<Player> muted = new ArrayList<Player>();
	
	String prefix = ChatColor.RED + "" + ChatColor.BOLD
			+ "BC" + ChatColor.BLUE + "" + ChatColor.BOLD + "Punish" + ChatColor.WHITE + " - ";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("mute")) {
		if (!sender.hasPermission("bc.mute")) {
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
                    return true;
            }
            if (target.hasPermission("bc.mutebypass")) {
            	sender.sendMessage(prefix + ChatColor.RED + "This player cannot be muted!");
            	
            }else{
            muted.add(target);
            Bukkit.getServer().getPluginManager().callEvent(new Misc(target, Type.MUTE));
            Bukkit.getServer().broadcastMessage(prefix + ChatColor.YELLOW + "Player " + target.getName() + " has been muted by " + sender.getName() + "!");
		}
	}
	}
		return false;
}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
			Player p = e.getPlayer();
			if (!p.hasPermission("bc.mutebypass") && (muted.contains(p)))
				p.sendMessage(prefix + ChatColor.RED + "You are currently muted!");
				e.setCancelled(true);
				return;
			}
}