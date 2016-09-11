package org.battlecraft.piesrgr8.essentials;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerTp implements Listener {
	
	public static ArrayList<String> players = new ArrayList<String>();
	
	public static void openGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 18, ChatColor.YELLOW + "Player Teleportation");

		for(int i = 0; i < players.size(); i++){ //Where players is an array of the players in-game
			  String playerName = players.get(i);
			  ItemStack item = new ItemStack(Material.SKULL_ITEM);
			  ItemMeta meta = item.getItemMeta();
			 
			  meta.setDisplayName("Teleport to " + playerName);
			  item.setItemMeta(meta);
			  inv.setItem(i, item);
			}

		p.openInventory(inv);
	}
}
