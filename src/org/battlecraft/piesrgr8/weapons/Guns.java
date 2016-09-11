package org.battlecraft.piesrgr8.weapons;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Guns implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_AIR)) {
			return;
		}

		if (!(e.getItem().getType() == Material.TNT)) {
			return;
		}

		Player p = (Player) e.getPlayer();
		TNTPrimed t = (TNTPrimed) p.getWorld().spawn(p.getLocation(), TNTPrimed.class);
		t.setVelocity(p.getLocation().getDirection().normalize().multiply(2));
		if (p.getGameMode() != GameMode.CREATIVE) {
			e.getPlayer().getInventory().removeItem(new ItemStack(Material.TNT, 1));
		}
		e.getPlayer().getLocation().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_BIG_FALL, 10, 10);
	}
}