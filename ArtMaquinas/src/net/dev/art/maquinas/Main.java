package net.dev.art.maquinas;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.maquinas.apis.MaquinasAPI;
import net.dev.art.maquinas.managers.Events;

public class Main extends JavaPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onEnable() {
//		MaquinasAPI.loadMaquinas();
		Bukkit.getPluginManager().registerEvents(new Events(), this);
		saveDefaultConfig();
		new BukkitRunnable() {

			@Override
			public void run() {
				MaquinasAPI.saveMaquinas();
			}
		}.runTaskTimer(instance, 20 * 60 * 60, 20 * 60 * 60);
	}

	@Override
	public void onDisable() {
		MaquinasAPI.saveMaquinas();
	}

}
