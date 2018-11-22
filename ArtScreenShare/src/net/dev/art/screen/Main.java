package net.dev.art.screen;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new ScreeShare(), this);
		getCommand("ss").setExecutor(new ScreeShare());
	}

	@Override
	public void onDisable() {

	}

}
