package org.battlecraft.piesrgr8.essentials;

import java.util.ArrayList;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerTp implements Listener {
	
	public static ArrayList<String> players = new ArrayList<String>();
	
	BattlecraftServer plugin;
	
	public PlayerTp(BattlecraftServer p) {
		this.plugin = p;
	}
	
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
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Player Teleportation"))
			return;

		//Player p = (Player) e.getWhoClicked();
		e.setCancelled(true);

		if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)
				|| !e.getCurrentItem().hasItemMeta()) {
			e.setCancelled(true);
			return;
		}
	}
}
