package org.battlecraft.piesrgr8.utils;

import org.battlecraft.piesrgr8.BattlecraftServer;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignColors implements Listener{
	
	/*
	 * THIS METHOD WAS BORROWED FROM IHERSH
	 * CREDZ TO THIG GUY RIGHT HRRR!
	 */

	BattlecraftServer plugin;
	
	public SignColors(BattlecraftServer p) {
		this.plugin = p;
	}
	
	@EventHandler
	public void signCreation(SignChangeEvent e) {
		 e.setLine(0, color(e.getLine(0)));
	     e.setLine(1, color(e.getLine(1)));
	     e.setLine(2, color(e.getLine(2)));
	     e.setLine(3, color(e.getLine(3)));
	}
	
	
	 public static String color(String msg){
		  msg = ChatColor.translateAlternateColorCodes('&', msg);
		  return msg;
		 }
}
