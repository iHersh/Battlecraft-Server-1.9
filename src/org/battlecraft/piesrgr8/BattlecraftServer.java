package org.battlecraft.piesrgr8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.battlecraft.piesrgr8.config.Spawning;
import org.battlecraft.piesrgr8.essentials.AntiSwear;
import org.battlecraft.piesrgr8.essentials.Chat;
import org.battlecraft.piesrgr8.essentials.Commands;
import org.battlecraft.piesrgr8.essentials.PlayerTp;
import org.battlecraft.piesrgr8.fake.SilentJoin;
import org.battlecraft.piesrgr8.fake.Spammer;
import org.battlecraft.piesrgr8.hub.Hub;
import org.battlecraft.piesrgr8.hub.Launchers;
import org.battlecraft.piesrgr8.hub.Menus;
import org.battlecraft.piesrgr8.hub.NavGame;
import org.battlecraft.piesrgr8.inventory.RestoreInventory;
import org.battlecraft.piesrgr8.listeners.BCBlockListener;
import org.battlecraft.piesrgr8.listeners.DeathListener;
import org.battlecraft.piesrgr8.listeners.PlayerListener;
import org.battlecraft.piesrgr8.players.ListPlayer;
import org.battlecraft.piesrgr8.players.Piesrgr8;
import org.battlecraft.piesrgr8.poll.Poll;
import org.battlecraft.piesrgr8.shop.Shop;
import org.battlecraft.piesrgr8.shop.ShopMaterial;
import org.battlecraft.piesrgr8.shop.ShopMaterialStone;
import org.battlecraft.piesrgr8.shop.ShopMaterialWood;
import org.battlecraft.piesrgr8.shop.ShopTool;
import org.battlecraft.piesrgr8.staff.StaffList;
import org.battlecraft.piesrgr8.utils.Cooldown;
import org.battlecraft.piesrgr8.utils.Dynamicmotd;
import org.battlecraft.piesrgr8.utils.PacketUtil;
import org.battlecraft.piesrgr8.utils.PlayerCountMessage;
import org.battlecraft.piesrgr8.utils.Test;
import org.battlecraft.piesrgr8.weapons.Guns;
import org.battlecraft.piesrgr8.world.WorldHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.milkbowl.vault.economy.Economy;

public class BattlecraftServer extends JavaPlugin implements CommandExecutor {

	Logger log = Logger.getLogger("Minecraft");
	BattlecraftServer plugin = this;

	public static Economy econ = null;

	public static List<String> message1 = new ArrayList<String>();

	private static JavaPlugin instance;

	// ALL PREFIXES FOR SEVERAL CLASSES!

	public static String prefixMain = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC" + ChatColor.GRAY
			+ "] ";

	public static String prefixHealth = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC"
			+ ChatColor.GREEN + "" + ChatColor.BOLD + "Health" + ChatColor.GRAY + "] ";

	public static String prefixGamemode = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC"
			+ ChatColor.BLUE + "" + ChatColor.BOLD + "Gamemode" + ChatColor.GRAY + "] ";

	public static String prefixPunish = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC"
			+ ChatColor.BLUE + "" + ChatColor.BOLD + "Punish" + ChatColor.GRAY + "] ";

	public static String prefixWorld = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC"
			+ ChatColor.BLUE + "" + ChatColor.BOLD + "World" + ChatColor.GRAY + "] ";

	public static String prefixKit = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC" + ChatColor.BLUE
			+ "" + ChatColor.BOLD + "Kit" + ChatColor.GRAY + "] ";

	public static String prefixStealth = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC"
			+ ChatColor.BLUE + "" + ChatColor.BOLD + "Stealth" + ChatColor.GRAY + "] ";

	public static String prefixHub = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC" + ChatColor.BLUE
			+ "" + ChatColor.BOLD + "Hub" + ChatColor.GRAY + "] ";

	public static String prefixWeapons = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC"
			+ ChatColor.BLUE + "" + ChatColor.BOLD + "Weapons" + ChatColor.GRAY + "] ";

	public static String prefixPolls = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC"
			+ ChatColor.BLUE + "" + ChatColor.BOLD + "Poll" + ChatColor.GRAY + "] ";

	public static String prefixError = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC"
			+ ChatColor.DARK_RED + "" + ChatColor.BOLD + "Error" + ChatColor.GRAY + "] ";

	public static String prefixStaff = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC"
			+ ChatColor.BLUE + "" + ChatColor.BOLD + "Staff" + ChatColor.GRAY + "] ";

	public static String prefixShop = ChatColor.GRAY + "[" + ChatColor.RED + "" + ChatColor.BOLD + "BC" + ChatColor.BLUE
			+ "" + ChatColor.BOLD + "Shop" + ChatColor.GRAY + "] ";

	public static String prefixCooldown = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "" + ChatColor.BOLD + "COOLDOWN"
			+ ChatColor.GRAY + "] ";

	@Override
	public void onEnable() {
		if (!setupEconomy()) {
			getLogger().severe("The Battlecraft Server Plugin was shut down due to economy issues!");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		Cooldown.cooldownTime = new HashMap<Player, Integer>();
		Cooldown.cooldownTask = new HashMap<Player, BukkitRunnable>();
		getLogger().info("The Battlecraft Server Plugin is awake and alive!");
		log = this.getLogger();
		Poll.savePollYaml(this);
		ListPlayer.savePlayerList(this);
		PlayerCountMessage.playerCountMessage(this);
		StaffList.saveStaffYaml(this);
		Spawning.saveSpawnYaml(this);
		getConfig();
		getConfig().options().copyDefaults(true);
		saveConfig();

		registerEvents();
		Commands.registerCommands(this);
	}

	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Hub(this), this);
		pm.registerEvents(new SilentJoin(this), this);
		pm.registerEvents(new PlayerListener(this), this);
		pm.registerEvents(new BCBlockListener(), this);
		pm.registerEvents(new Piesrgr8(this), this);
		pm.registerEvents(new AntiSwear(this), this);
		pm.registerEvents(new WorldHandler(this), this);
		pm.registerEvents(new Guns(this), this);
		pm.registerEvents(new Poll(this), this);
		pm.registerEvents(new PacketUtil(this), this);
		pm.registerEvents(new DeathListener(this), this);
		pm.registerEvents(new Spammer(this), this);
		pm.registerEvents(new Test(this), this);
		pm.registerEvents(new RestoreInventory(this), this);
		pm.registerEvents(new Dynamicmotd(this), this);
		pm.registerEvents(new Menus(this), this);
		pm.registerEvents(new NavGame(this), this);
		pm.registerEvents(new PlayerTp(this), this);
		pm.registerEvents(new Launchers(this), this);
		pm.registerEvents(new Chat(this), this);

		// FOR SHOP
		pm.registerEvents(new Shop(this), this);
		pm.registerEvents(new ShopMaterial(this), this);
		pm.registerEvents(new ShopMaterialWood(this), this);
		pm.registerEvents(new ShopMaterialStone(this), this);
		pm.registerEvents(new ShopTool(this), this);
	}

	@Override
	public void onDisable() {
		saveConfig();
		Poll.savePollYaml(this);
		ListPlayer.savePlayerList(this);
		PlayerCountMessage.playerCountMessage(this);
		StaffList.saveStaffYaml(this);
		Spawning.saveSpawnYaml(this);
		getLogger().info("The Battlecraft Server Plugin is asleep!");
		plugin = null;
	}

	public static JavaPlugin getInstance() {
		return instance;
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
}