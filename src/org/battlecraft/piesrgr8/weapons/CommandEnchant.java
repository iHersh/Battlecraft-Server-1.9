package org.battlecraft.piesrgr8.weapons;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.utils.Debug;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandEnchant implements CommandExecutor {

	BattlecraftServer plugin;

	public CommandEnchant(BattlecraftServer p) {
		this.plugin = p;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("enchant")) {
			if (!sender.isOp()) {
				sender.sendMessage("You cannot enchant anything since you're not op!");
				return true;
			}

			if (args.length == 0) {
				sender.sendMessage("Now hold an item in your hand and type the enchantment id and the level.");
				return true;
			}

			if (args.length == 1) {
				sender.sendMessage(Enchantment.getByName(args[0]) + " - You must include a level.");
				return true;
			}

			if (args.length >= 2) {
				Debug.debugBroadcast("Level " + args[2]);
				Player p = (Player) sender;
				ItemStack it = p.getInventory().getItemInHand();
				
				if (it.equals(Material.AIR)) {
					p.sendMessage("You must be holding an item to enchant.");
				}

				it.addUnsafeEnchantment(Enchantment.getByName(args[1]), Integer.parseInt(args[2]));
				p.sendMessage("You have successfully enchanted an item!");
				return true;
			}
		}
		return true;
	}
}