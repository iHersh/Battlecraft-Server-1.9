package org.battlecraft.piesrgr8.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.ChatColor;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;

public class PlayerCountMessage {

	private static List<WrappedGameProfile> message = new ArrayList<WrappedGameProfile>();

	BattlecraftServer plugin;

	public PlayerCountMessage(BattlecraftServer p) {
		this.plugin = p;
	}

	@SuppressWarnings("deprecation")
	public static void playerCountMessage(BattlecraftServer plugin) {
		if (!new File(plugin.getDataFolder(), "RESET.FILE").exists()) {
			try {
				new File(plugin.getDataFolder(), "RESET.FILE").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		plugin.saveConfig();

		for (String str : plugin.getConfig().getStringList("pcm")) {
			message.add(new WrappedGameProfile("1", ChatColor.translateAlternateColorCodes('&', str)));
		}

		ProtocolLibrary.getProtocolManager()
				.addPacketListener(new PacketAdapter(plugin, ListenerPriority.NORMAL,
						Arrays.asList(new PacketType[] { PacketType.Status.Server.OUT_SERVER_INFO }),
						new ListenerOptions[] { ListenerOptions.ASYNC }) {

					public void onPacketSending(PacketEvent e) {
						e.getPacket().getServerPings().read(0).setPlayers(PlayerCountMessage.message);
					}
				});
	}
}