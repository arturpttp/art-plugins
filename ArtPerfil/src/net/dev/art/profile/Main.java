package net.dev.art.profile;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.profile.managers.ProfileManger;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();
	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getPluginManager().registerEvents(new ProfileManger(), this);
		getCommand("perfil").setExecutor(new ProfileManger());
	}

	@Override
	public void onDisable() {

	}

}
