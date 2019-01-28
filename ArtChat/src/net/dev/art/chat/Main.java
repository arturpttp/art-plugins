package net.dev.art.chat;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.chat.Comandos.ChatCommand;
import net.dev.art.chat.Comandos.GlobalChat;
import net.dev.art.chat.Comandos.StaffChatCommand;
import net.dev.art.chat.Comandos.TellCommand;
import net.dev.art.chat.Listeners.ChatLocal;
import net.dev.art.core.utils.ArtLib;

public class Main extends JavaPlugin implements ArtLib {
	
	String token = "NTE3MTE1MDg3MTgyNDk1NzQ1.Dw5F9g.HrUA-hxtjILgbL2JT7c-iVz8NVk";
	public static Main instance;
	public static File config;
	public static YamlConfiguration c;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();
	public String prefix = "§b" + getDescription().getName() + "§8 § ";

	public static Main getInstance() {
		return instance;
	}

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
		e.registerEvents(new ChatLocal(), this);
		getCommand("chat").setExecutor(new ChatCommand());
		getCommand("sc").setExecutor(new StaffChatCommand());
		getCommand("g").setExecutor(new GlobalChat());
		getCommand("tell").setExecutor(new TellCommand());

	}

	private void sendConsoleInfos() {
		// mensagem("§eRegistrando APIs");
		// send.sendMessage("§a");
		// mensagem("§eRegistrando `§b§lCalendarioAPI§e`");
		// send.sendMessage("§a");
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
