package net.dev.art.temp;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.temp.commands.SpawnerCommand;
import net.dev.art.temp.events.onSpawnerPlace;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public static Main getInstance() {
		return instance;
	}

	public static File config;
	public static YamlConfiguration c;

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onLoad() {
		mensagem("§eCarregando...");
	}

	@Override
	public void onEnable() {
		instance = this;

		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + getDescription().getName());
		send.sendMessage(
				"§eCriado Por: §b§l" + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + getDescription().getVersion());
		send.sendMessage("");

		sendConsoleInfos();
		Register();
	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		getCommand("spawner").setExecutor(new SpawnerCommand());
		e.registerEvents(new onSpawnerPlace(), this);
	}

	private void sendConsoleInfos() {
	}

	void GerarConfig() {
		config = new File(getDataFolder(), "config.yml");
		if (!config.exists()) {
			saveResource("config.yml", false);
			send.sendMessage(prefix + "§eCriando `§bconfig.yml§e`");
		}
		config = new File(getDataFolder(), "config.yml");
		c = YamlConfiguration.loadConfiguration(config);
		send.sendMessage(prefix + "§eCarregando `§bconfig.yml§e`");
	}

	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {

	}

}
