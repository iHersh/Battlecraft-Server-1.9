package org.battlecraft.piesrgr8.shop;

import java.io.File;
import java.util.List;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Kiosk implements Listener, CommandExecutor{
	
	BattlecraftServer plugin;
	
	public Kiosk(BattlecraftServer p) {
		this.plugin = p;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kiosk")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("You are not a player!");
				return true;
			}
			Player p = (Player) sender;
			openGUI(p);
			p.sendMessage("Opened Kiosk!");
			return true;
		}
		return true;
	}
	
	public static void openGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Kiosk");

		// Creating the items and registering them.

		File f = new File("plugins//BattlecraftServer//Kiosk//" + p.getName() + ".yml");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
		List<?> list = yaml.getList("Bought");
		
		for (int i = 0; list.size() > i; i++) {
		inv.setItem(i, (ItemStack) list.get(i));
		}
		
		p.openInventory(inv);
	}
}
