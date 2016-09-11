package org.battlecraft.piesrgr8.listeners;

import java.io.File;
import java.io.IOException;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.battlecraft.piesrgr8.essentials.PlayerTp;
import org.battlecraft.piesrgr8.utils.PacketUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import net.minecraft.server.v1_9_R2.PacketPlayOutCustomSoundEffect;
import net.minecraft.server.v1_9_R2.SoundCategory;

public class PlayerListener implements Listener {
	
	BattlecraftServer plugin;
	
	public PlayerListener(BattlecraftServer p) {
		this.plugin = p;
	}
	
	YamlConfiguration yaml = new YamlConfiguration();

	@EventHandler
	public void onItemEnchant(EnchantItemEvent e) {
		Player p = e.getEnchanter();
		p.sendMessage(ChatColor.GOLD + "" + "You have the " + ChatColor.GOLD + "" + ChatColor.ITALIC
				+ e.getEnchantsToAdd().toString() + ChatColor.GOLD + " enchantment!");
		for (Player player : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) player).getHandle().playerConnection.sendPacket(
					new PacketPlayOutCustomSoundEffect("notify.notifyitemenchant", SoundCategory.MASTER, p.getLocation().getBlockX(),
							p.getLocation().getBlockY(), p.getLocation().getBlockZ(), 100000.0F, 1.0F));
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		e.setJoinMessage(p.getDisplayName() + ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + " joined");
		
		PlayerTp.players.add(p.getName());
		
		if (plugin.getConfig().getBoolean("titleonjoin") == true) {
		PacketUtil.onJoin(plugin, p);
		}
		
		if (!p.getWorld().getName().equalsIgnoreCase("Hub1")) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
				p.teleport(new Location(Bukkit.getServer().getWorld("Hub1"), 1041, 11, 586));	
				}
			}, 25);
		}
		
		yaml.createSection(p.getName());
		yaml.createSection(p.getName() + ".muted");
		yaml.createSection(p.getName() + ".banned");
		yaml.addDefault(p.getName() + ".muted", false);
		yaml.addDefault(p.getName() + ".banned", false);
		try {
			yaml.save(new File(plugin.getDataFolder() + "players.yml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (p.hasPlayedBefore() == false) {
			Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + ChatColor.YELLOW + " has joined for the first time!");
			yaml.createSection(p.getName());
			yaml.createSection(p.getName() + ".muted");
			yaml.createSection(p.getName() + ".banned");
			yaml.addDefault(p.getName() + ".muted", false);
			yaml.addDefault(p.getName() + ".banned", false);
			try {
				yaml.save(new File(plugin.getDataFolder() + "players.yml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(p.getDisplayName() + ChatColor.DARK_RED + "" + ChatColor.ITALIC + " left");
		PlayerTp.players.remove(p.getName());
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		if (e.getResult() == Result.KICK_FULL && e.getPlayer().hasPermission("bc.full")) {
			e.allow();
		}
	}
}