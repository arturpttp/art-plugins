package net.dev.art.dbc.warp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.dbc.warp.cmds.delWarp;
import net.dev.art.dbc.warp.cmds.setWarp;
import net.dev.art.dbc.warp.cmds.warp;
import net.dev.art.dbc.warp.cmds.warps;

public class Main extends JavaPlugin implements MessagesManager {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public static Map<String, Warp> warps = new HashMap<>();

	public File w;
	public FileConfiguration warp;

	public String prefix;

	@Override
	public void onEnable() {

		instance = this;
		saveDefaultConfig();

		try {
			prefix = getConfig().getString("Settings.Prefix").replace("&", "§");
		} catch (NullPointerException e) {
			Bukkit.getConsoleSender().sendMessage("§cConfig.yml->Settings->Prefix");
		}

		send("§eCarregando arquivo de configuracao `§bconfig.yml§e`.");

		w = new File(getDataFolder(), "warps.yml");
		if (!w.exists()) {
			saveResource("warps.yml", false);
			send("§eCriando arquivo de configuracao `§bwarps.yml§e`.");
		}

		w = new File(getDataFolder(), "warps.yml");
		warp = YamlConfiguration.loadConfiguration(w);
		send("§eCarregando arquivo de configuracao `§bwarps.yml§e`.");

		loadWarps();
		Commands();
	}

	void Commands() {
		getCommand("setWarp").setExecutor(new setWarp());
		getCommand("delWarp").setExecutor(new delWarp());
		getCommand("Warp").setExecutor(new warp());
		getCommand("Warps").setExecutor(new warps());
	}

	void saveWarps() {
		if (warps.size() <= 0) {
			send("§cNenhuma warp para salvar!");
		} else {
			for (Entry<String, Warp> entry : warps.entrySet()) {
				LocManager.setWarp(entry.getValue());
			}
		}
	}

	void loadWarps() {
		if (warp.contains("Warps")) {
			for (String name : warp.getConfigurationSection("Warps").getKeys(false)) {
				double x = warp.getDouble("Warps." + name + ".X");
				double y = warp.getDouble("Warps." + name + ".Y");
				double z = warp.getDouble("Warps." + name + ".Z");
				float yaw = (float) warp.getDouble("Warps." + name + ".Yaw");
				float pitch = (float) warp.getDouble("Warps." + name + ".Pitch");
				String worldName = warp.getString("Warps." + name + ".World");

				World world = Bukkit.getWorld(worldName);

				Location loc = new Location(world, x, y, z, yaw, pitch);

				warps.put(name, new Warp(name, loc));

			}
		} else {
			send("§cSem warps para carregar");
		}
	}

	@Override
	public void onDisable() {
		saveWarps();
	}

}
