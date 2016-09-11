package org.battlecraft.piesrgr8.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class Test implements Listener, CommandExecutor {

	BattlecraftServer plugin;
	
	public final HashMap<Location, String> signs = new HashMap<Location, String>();
	public final ArrayList<String> items = new ArrayList<String>();

	public Test(BattlecraftServer p) {
		this.plugin = p;
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (e.getLine(0).equalsIgnoreCase("Buy")) {
			signs.put(e.getBlock().getLocation(), p.getName());
			p.sendMessage("Added a buy sign!");
			e.setLine(0, ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BUY" + ChatColor.GRAY + "]");
		}
		
		if(e.getLine(1).equalsIgnoreCase("Arrow")) {
			items.add(e.getLine(1));
		}
	}
	/*
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
		if(signs.containsKey(e.getClickedBlock().getLocation())) {
			if (items.equals("Arrow")) {
				p.getInventory().addItem(new ItemStack(Material.ARROW, 1));
				p.sendMessage("You bought arrow");
			}
		}
		}
	}
	*/

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("test")) {
			if (!sender.hasPermission("bc.test")) {
				sender.sendMessage("No permission");
				return true;
			}

			if (args.length == 0) {
				sender.sendMessage("This class is testing buy signs and sell signs!");
				return true;
			}
		}
		return true;
	}
}