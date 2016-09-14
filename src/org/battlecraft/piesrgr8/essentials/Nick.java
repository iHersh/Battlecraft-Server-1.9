package org.battlecraft.piesrgr8.essentials;

public class Nick {
	
}

	/*
	static File f = new File("plugins/BattlecraftServer/players.yml");
	static YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("nickname")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Not a player!");
				return true;
			}
			Player p = (Player) sender;
			if (!p.hasPermission("bc.nick")) {
				p.sendMessage("You dont have permission to set your nickname");
				return true;
			}
			
			if (args.length == 0) {
				p.sendMessage("What will be your nickname?");
				return true;
			}
			
			if (args.length == 1) {
				String bc = "";
				for (String message : args) {
					bc = (bc + message + "");
				}
				p.sendMessage("Successfully set nick name!");
				yaml.createSection(p.getName() + ".nick");
				yaml.set(p.getName() + ".nick", bc);
				try {
					yaml.save(f);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	public static void nickname(Player p) {
		if (p.isOp()) {
			return;
		}
		
		if (yaml.contains(p.getName() + ".nick")) {
			p.setDisplayName(yaml.getString(p.getName() + ".nick"));
		}
	}
}
	*/