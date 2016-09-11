package org.battlecraft.piesrgr8.particles;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Particles implements CommandExecutor{

	BattlecraftServer plugin;
	
	public Particles(BattlecraftServer p) {
		this.plugin = p;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("particle")) {
			if (args.length == 0) {
			sender.sendMessage(BattlecraftServer.prefixMain + ChatColor.RED + "Please specify a particle #");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("1")) {
				sender.sendMessage("Playing!");
				sphere(p, true);
				return true;
			}
			if (args[0].equalsIgnoreCase("stop")) {
				sender.sendMessage("Stopped!");
				sphere(p, false);
				return true;
			}
		}
	}
		return true;
	}
	
	public void sphere(Player p, boolean b) {
		if (b) {
		final Location loc = p.getLocation();
		Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
			double phi = 0;
			public void run() {
				phi += Math.PI/10;
				for (double theta = 0; theta <= 2*Math.PI; theta += Math.PI/40) {
					double r = 1.5;
					double x = r*Math.cos(theta)*Math.sin(phi);
					double y = r*Math.cos(phi) + 1.5;
					double z = r*Math.sin(theta)*Math.sin(phi);
					loc.add(x, y, z);
					ParticleEffect.DRIP_WATER.display(0, 0, 0, 1, 1, loc, 20);
				}
			}
		},20, 2);
	}
}
}