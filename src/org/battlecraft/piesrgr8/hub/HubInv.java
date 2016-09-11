package org.battlecraft.piesrgr8.hub;

import org.battlecraft.piesrgr8.essentials.Rulebook;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HubInv {
	
	public static void hubInv(Player p) {
		Inventory inv = p.getInventory();
		
		ItemStack ns = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = ns.getItemMeta();
		
		ItemStack ns1 = new ItemStack(Material.END_CRYSTAL);
		ItemMeta meta1 = ns1.getItemMeta();
		
		meta.setDisplayName(ChatColor.YELLOW + "Game Selector");
		ns.setItemMeta(meta);
		
		meta1.setDisplayName(ChatColor.BLUE + "Quick Game");
		ns1.setItemMeta(meta1);
		
		inv.addItem(ns);
		inv.addItem(Rulebook.rulebook(p));
		inv.addItem(ns1);
	}
}
