package org.battlecraft.piesrgr8.essentials;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Health implements CommandExecutor{
	
	String prefix = BattlecraftServer.prefixHealth;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	if (cmd.getName().equalsIgnoreCase("heal")) {
    		if (!sender.isOp()){
    			sender.sendMessage(prefix + ChatColor.RED + "You don't have permission to heal yourself!");
    			return true;
    		}
    		Player p = (Player) sender;
    		if(args.length==0){
    			p.setHealth(20);
        		p.setFoodLevel(20);
        		p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
        		p.sendMessage(prefix + ChatColor.GREEN + "" + ChatColor.BOLD + "HEALED!");
        		return true;
    		}
    		    
    		if (args.length == 1){
    			
    			for (Player target : Bukkit.getOnlinePlayers()){
    				if (target == null) {
    					p.sendMessage(prefix + ChatColor.RED + "That player isn't on the server!");
    					return true;
    				}
    				if (target.getName().equalsIgnoreCase(args[0])){
    				if (target.getHealth() == 20D){
    					p.sendMessage(prefix + ChatColor.GREEN + target.getName() + " is already at full health!");
    				}else{
    					target.setHealth(20);
    					target.setFoodLevel(20);
    					target.playSound(target.getLocation(), Sound.BLOCK_LEVER_CLICK, 10, 1);
    					target.sendMessage(prefix + ChatColor.GREEN + p.getName() + " healed you!");
    					p.sendMessage(prefix + ChatColor.GREEN + args[0] + " has been healed!");
    				}
    				
    			}
    		}
    	}
    }
    		
    		
    if (cmd.getName().equalsIgnoreCase("death")) {
    	Player p = (Player) sender;
    	if (!p.isOp()){
    		sender.sendMessage(ChatColor.RED + "You don't have permission to kill yourself!");
    	}else{
    	p.setHealth(0);
    	p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_DEATH, 1, 1);
    	p.sendMessage(prefix + ChatColor.RED + "" + ChatColor.BOLD + "KILLED!");
    	return true;
	}
    }
    	return true;
    }
}