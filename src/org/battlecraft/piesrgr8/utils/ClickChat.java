package org.battlecraft.piesrgr8.utils;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_9_R2.PlayerConnection;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;

public class ClickChat implements CommandExecutor {

	BattlecraftServer plugin;

	public ClickChat(BattlecraftServer p) {
		this.plugin = p;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("website")) {

			Player p = (Player) sender;

			PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
			PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer
					.a("{\"text\":\"CLICK HERE TO OPEN THE WEBSITE!\",\"color\":\"aqua\",\"bold\":true,"
							+ "\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://bcpvp101.enjin.com/\"},"
							+ "\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click here to open the website!\",\"color\":\"gold\"}]}}}"));

			connection.sendPacket(packet);
		}
		return true;
	}
	
	public static void textChatHover(Player p, String arg1, String arg2) {
		PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer
				.a("{\"text\":\""+ arg1 +"\",\"color\":\"yellow\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\""+ arg2 +"\",\"color\":\"green\"}]}}}"));

		connection.sendPacket(packet);
	}
}
