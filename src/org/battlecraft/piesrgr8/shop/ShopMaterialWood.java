package org.battlecraft.piesrgr8.shop;

import java.util.Arrays;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.milkbowl.vault.economy.EconomyResponse;

public class ShopMaterialWood implements Listener {

	BattlecraftServer plugin;

	public ShopMaterialWood(BattlecraftServer p) {
		this.plugin = p;
	}

	@SuppressWarnings("deprecation")
	public static void openGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.YELLOW + "Shop - Wood");

		// Creating the items and registering them.

		ItemStack block1 = new ItemStack(Material.WOOD);
		ItemMeta block_1 = block1.getItemMeta();

		byte plankType2 = 1;
		ItemStack block2 = new ItemStack(Material.WOOD, 1, plankType2);
		ItemMeta block_2 = block2.getItemMeta();

		byte plankType3 = 2;
		ItemStack block3 = new ItemStack(Material.WOOD, 1, plankType3);
		ItemMeta block_3 = block3.getItemMeta();

		byte plankType4 = 3;
		ItemStack block4 = new ItemStack(Material.WOOD, 1, plankType4);
		ItemMeta block_4 = block4.getItemMeta();

		byte plankType5 = 4;
		ItemStack block5 = new ItemStack(Material.WOOD, 1, plankType5);
		ItemMeta block_5 = block5.getItemMeta();

		byte plankType6 = 5;
		ItemStack block6 = new ItemStack(Material.WOOD, 1, plankType6);
		ItemMeta block_6 = block5.getItemMeta();

		ItemStack custom = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
		ItemMeta custom1 = custom.getItemMeta();

		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta back1 = back.getItemMeta();

		// Setting the meta, or names, of all of the blocks that will be added
		// to the inventory.

		block_1.setDisplayName(ChatColor.YELLOW + "Oak Wood Planks");
		block_1.setLore(Arrays.asList(ChatColor.GREEN + "Price: " + ChatColor.GOLD + "$2.50"));
		block1.setItemMeta(block_1);

		block_2.setDisplayName(ChatColor.YELLOW + "Spruce Wood Planks");
		block_2.setLore(Arrays.asList(ChatColor.GREEN + "Price: " + ChatColor.GOLD + "$2.50"));
		block2.setItemMeta(block_2);

		block_3.setDisplayName(ChatColor.YELLOW + "Birch Wood Planks");
		block_3.setLore(Arrays.asList(ChatColor.GREEN + "Price: " + ChatColor.GOLD + "$2.50"));
		block3.setItemMeta(block_3);

		block_4.setDisplayName(ChatColor.YELLOW + "Jungle Wood Planks");
		block_4.setLore(Arrays.asList(ChatColor.GREEN + "Price: " + ChatColor.GOLD + "$2.50"));
		block4.setItemMeta(block_4);

		block_5.setDisplayName(ChatColor.YELLOW + "Acacia Wood Planks");
		block_5.setLore(Arrays.asList(ChatColor.GREEN + "Price: " + ChatColor.GOLD + "$2.50"));
		block5.setItemMeta(block_5);

		block_6.setDisplayName(ChatColor.YELLOW + "Dark Oak Wood Planks");
		block_6.setLore(Arrays.asList(ChatColor.GREEN + "Price: " + ChatColor.GOLD + "$2.50"));
		block6.setItemMeta(block_6);

		back1.setDisplayName(ChatColor.RED + "Back");
		back.setItemMeta(back1);

		custom1.setDisplayName(" ");
		custom.setItemMeta(custom1);

		// Set the items in their places.
		// THEY WILL BE SET IN SETS OF 9's!

		// ITEMS BEING SET
		inv.setItem(0, block1);
		inv.setItem(1, block2);
		inv.setItem(2, block3);
		inv.setItem(3, block4);
		inv.setItem(4, block5);
		inv.setItem(5, block6);

		// RANDOM AIR SLATES
		inv.setItem(6, custom);
		inv.setItem(7, custom);
		inv.setItem(8, custom);
		inv.setItem(9, custom);
		inv.setItem(10, custom);
		inv.setItem(11, custom);
		inv.setItem(12, custom);
		inv.setItem(13, custom);
		inv.setItem(14, custom);
		inv.setItem(15, custom);
		inv.setItem(16, custom);
		inv.setItem(17, custom);

		// LINE 3
		inv.setItem(18, custom);
		inv.setItem(19, custom);
		inv.setItem(20, custom);
		inv.setItem(21, custom);
		inv.setItem(22, custom);
		inv.setItem(23, custom);
		inv.setItem(24, custom);
		inv.setItem(25, custom);
		inv.setItem(26, custom);

		// LINE 4
		inv.setItem(27, custom);
		inv.setItem(28, custom);
		inv.setItem(29, custom);
		inv.setItem(30, custom);
		inv.setItem(31, custom);
		inv.setItem(32, custom);
		inv.setItem(33, custom);
		inv.setItem(34, custom);
		inv.setItem(35, custom);

		// LINE 5 (ITEMS REMOVED FROM THIS LIST)
		inv.setItem(36, custom);
		inv.setItem(37, custom);
		inv.setItem(38, custom);
		inv.setItem(39, custom);
		inv.setItem(40, custom);
		inv.setItem(41, custom);
		inv.setItem(42, custom);
		inv.setItem(43, custom);
		inv.setItem(44, custom);

		// LINE 6 (ITEMS REMOVED FROM THIS LIST)
		inv.setItem(45, back);
		inv.setItem(46, custom);
		inv.setItem(47, custom);
		inv.setItem(48, custom);
		inv.setItem(49, custom);
		inv.setItem(50, custom);
		inv.setItem(51, custom);
		inv.setItem(52, custom);
		inv.setItem(53, custom);

		p.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick1(InventoryClickEvent e) {
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Shop - Wood"))
			return;

		Player p = (Player) e.getWhoClicked();
		Inventory inv = p.getInventory();
		e.setCancelled(true);

		if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR)
				|| !e.getCurrentItem().hasItemMeta()) {
			e.setCancelled(true);
			return;
		}

		switch (e.getCurrentItem().getType()) {
		case WOOD:
			EconomyResponse r = BattlecraftServer.econ.withdrawPlayer(p, 2.50);
			double bal = BattlecraftServer.econ.getBalance(p);
			if (bal >= 2.50 && r.transactionSuccess()) {
				inv.addItem(new ItemStack(Material.WOOD));
				p.sendMessage(BattlecraftServer.prefixShop + ChatColor.GREEN + "You have bought " + ChatColor.YELLOW
						+ "wood " + ChatColor.GREEN + "for " + ChatColor.GOLD + "$2.50");
			} else {
				p.sendMessage(BattlecraftServer.prefixShop + ChatColor.RED + "You are low on funds!");
			}
			break;

		case ARROW:
			ShopMaterial.openGUI(p);
			break;

		default:
			e.setCancelled(true);
			break;
		}
	}
}
