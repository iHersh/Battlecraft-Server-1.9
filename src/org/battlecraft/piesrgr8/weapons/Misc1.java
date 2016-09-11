package org.battlecraft.piesrgr8.weapons;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.inventory.ItemStack;

public class Misc1 {
	
	ItemStack s;
	WeapTypes t;
	
	BattlecraftServer plugin;
	
	public Misc1(BattlecraftServer p) {
		this.plugin = p;
	}
	
	public Misc1(ItemStack s, WeapTypes t) {
		this.s = s;
		this.t = t;
	}
	
	public ItemStack getItem() {
		return s;
	}
	
	public WeapTypes getType() {
		return t;
	}
}
