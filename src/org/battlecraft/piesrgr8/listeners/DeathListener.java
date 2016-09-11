package org.battlecraft.piesrgr8.listeners;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener{
	
	BattlecraftServer plugin;
	
	public DeathListener (BattlecraftServer p) {
		this.plugin = p;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity().getPlayer();
		Player k = e.getEntity().getKiller();
		
		if (p.getLastDamageCause().getCause() == DamageCause.FALL) {
			e.setDeathMessage(ChatColor.BLUE + p.getName() + ChatColor.YELLOW + " took too much fall damage!");
	}
		
		if (p.getLastDamageCause().getCause() == DamageCause.DROWNING) {
			e.setDeathMessage(ChatColor.BLUE + p.getName() + ChatColor.YELLOW + " suffocated in water!");
		}
		
		if (p.getLastDamageCause().getCause() == DamageCause.FIRE || p.getLastDamageCause().getCause() == DamageCause.FIRE_TICK
				|| p.getLastDamageCause().getCause() == DamageCause.LAVA) {
			e.setDeathMessage(ChatColor.BLUE + p.getName() + ChatColor.YELLOW + " was burned alive!");
		}
		
		if (p.getLastDamageCause().getCause() == DamageCause.ENTITY_ATTACK) {
			e.setDeathMessage(ChatColor.RED + k.getName() + ChatColor.YELLOW + " killed " + ChatColor.BLUE + p.getName() +
					ChatColor.YELLOW + " with a " + k.getItemInHand());
		}
		
		if (p.getLastDamageCause().getCause() == DamageCause.VOID) {
			e.setDeathMessage(ChatColor.BLUE + p.getName() + ChatColor.YELLOW + " entered a black hole in space!");
		}
	}
}
