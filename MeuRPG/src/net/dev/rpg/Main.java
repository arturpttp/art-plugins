package net.dev.rpg;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.rpg.APIs.ManaAPI;
import net.dev.rpg.Managers.ClassesManager;
import net.dev.rpg.Managers.ManaManager;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public String prefix = "§b" + getDescription().getName() + "§8 » ";
	
	public static Main getInstance() {
		return instance;
	}

	public Metodos m;
	
	public static File coins;
	public static YamlConfiguration c;

	public static File config;
	public static YamlConfiguration cf;

	public static File data;
	public static YamlConfiguration d;

	@Override
	public void onLoad() {
		instance = this;
	}

	@Override
	public void onEnable() {
		send.sendMessage("");
		send.sendMessage("§aLigando Plugin: " + getDescription().getName());
		send.sendMessage("§aCriado Por: " + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§aVersao: " + getDescription().getVersion());
		send.sendMessage("");
		this.m = new Metodos();
		GerarConfigs();
		Register();
		sendConsoleMessages();
		for (Player p : Bukkit.getOnlinePlayers()) {
			ManaAPI.mana.put(p, 100);
			ManaAPI.sendMana(p);
			ManaAPI.regenMana(p);
		}
	}

	
	private void sendConsoleMessages() {		
		send.sendMessage("§a");
		
		mensagem("§eRegistrando Eventos e Comandos");
		
		send.sendMessage("§a");
		
		mensagem("§eRegistrando Commando `§b§lclasse§e`");
		
		send.sendMessage("§a");
		
		mensagem("§eRegistrando API `§b§lClassesAPI§e`");
		mensagem("§eRegistrando API `§b§lCoinsAPI§e`");
		mensagem("§eRegistrando API `§b§lManaAPI§e`");
		mensagem("§eRegistrando API `§b§lQuestsAPI§e`");
		
		send.sendMessage("§a");
		
		mensagem("§eCarregando Classe `§b§lMAGO§e`");
		mensagem("§eCarregando Classe `§b§lARQUEIRO§e`");
		mensagem("§eCarregando Classe `§b§lGUERREIRO§e`");
		mensagem("§eCarregando Classe `§b§lPYROMANCER§e`");
		mensagem("§eCarregando Classe `§b§lNECROMANCER§e`");
		mensagem("§eCarregando Classe `§b§lHYDROMANCER§e`");
		mensagem("§eCarregando Classe `§b§lGEOMANCER§e`");
		mensagem("§eCarregando Classe `§b§lAEROMANCER§e`");
	}
	
	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}
	
	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		getCommand("classe").setExecutor(new ClassesManager());
		e.registerEvents(new ClassesManager(), this);
		e.registerEvents(new ManaManager(), this);
	}

	private void GerarConfigs() {
		coins = new File(getDataFolder(), "coins.yml");
		if (!coins.exists()) {
			saveResource("coins.yml", false);
			send.sendMessage(prefix + "§eCriando `§bcoins.yml§e`");
		}
		coins = new File(getDataFolder(), "coins.yml");
		c = YamlConfiguration.loadConfiguration(coins);
		send.sendMessage(prefix + "§eCarregando `§bcoins.yml§e`");

		config = new File(getDataFolder(), "config.yml");
		if (!config.exists()) {
			saveResource("config.yml", false);
			send.sendMessage(prefix + "§eCriando `§bconfig.yml§e`");
		}
		config = new File(getDataFolder(), "config.yml");
		cf = YamlConfiguration.loadConfiguration(config);
		send.sendMessage(prefix + "§eCarregando `§bconfig.yml§e`");

		data = new File(getDataFolder(), "data.yml");
		if (!data.exists()) {
			saveResource("data.yml", false);
			send.sendMessage(prefix + "§eCriando `§bdata.yml§e`");
		}
		data = new File(getDataFolder(), "data.yml");
		d = YamlConfiguration.loadConfiguration(data);
		send.sendMessage(prefix + "§eCarregando `§bdata.yml§e`");

	}

	public void saveCoinsConfig() {
		try {
			c.save(coins);
			send.sendMessage(prefix + "§eSalvando `coins.yml`");
		} catch (IOException e) {
			send.sendMessage(prefix + "§cNão Foi Possivel Salvar `coins.yml`");
		}
	}

	public void saveData() {
		try {
			d.save(data);
			send.sendMessage(prefix + "§eSalvando `data.yml`");
		} catch (IOException e) {
			send.sendMessage(prefix + "§cNão Foi Possivel Salvar `data.yml`");
		}
	}

	@Override
	public void onDisable() {
		send.sendMessage("");
		send.sendMessage("§cDesligando Plugin: " + getDescription().getName());
		send.sendMessage("§cCriado Por: " + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§cVersao: " + getDescription().getVersion());
		send.sendMessage("");
		saveData();
	}

	public Metodos getMetodos() {
		return m;
	}
	
	
}

