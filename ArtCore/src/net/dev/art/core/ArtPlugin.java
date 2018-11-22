package net.dev.art.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class ArtPlugin extends JavaPlugin implements Listener,ArtLib {

	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public Plugin getPlugin() {
		return this;
	}

	public static File config;
	public static YamlConfiguration c;

	protected static APIsManager APIsManager;

	public List<String> comandos = new ArrayList<>();
	public List<String> eventos = new ArrayList<>();

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	public static APIsManager getAPIsManager() {
		return APIsManager;
	}

	@Override
	public void onLoad() {
		aoCarregar();
	}

	@Override
	public void onEnable() {
		APIsManager = new APIsManager(this);
		aoIniciar();
		Register();
		sendConsoleInfos();
		setEvent(this);
	}

	public void sendIniciando(ArtPlugin instance) {
		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + instance.getDescription().getName());
		send.sendMessage("§eCriado Por: §b§l"
				+ instance.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + instance.getDescription().getVersion());
		send.sendMessage("");
	}

	public abstract String getPrefix();
	
	public abstract void aoCarregar();

	public abstract void aoIniciar();

	public abstract void aoDisabilitar();

	public abstract void Register();

	public void setCommand(String cmd, CommandExecutor executor) {
		getCommand(cmd).setExecutor(executor);
		comandos.add(cmd);
	}

	public void setEvent(Listener classe) {
		PluginManager e = Bukkit.getPluginManager();
		e.registerEvents(classe, this);
	}

	private void sendConsoleInfos() {

		if (comandos.size() != 0) {
			mensagem("§eRegistrando Comandos...");
			for (String comando : comandos) {
				mensagem("§eRegistrando Comando `§b" + comando + "§e`");
			}

		}

		mensagem("§eRegistrando Eventos...");

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

	public void mensagem(Player p, String mensagem) {
		p.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {
		aoDisabilitar();
	}

}
