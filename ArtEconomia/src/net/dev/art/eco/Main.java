package net.dev.art.eco;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.eco.commands.CashCommand;
import net.dev.art.eco.commands.CoinsCommand;
import net.dev.art.eco.listeners.onJoin;

public class Main extends JavaPlugin{

	public static Main instance;
	
	public static Main getInstance() {
		return instance;
	}
	
	public static File coins;
	public static YamlConfiguration c;
	public static File cash;
	public static YamlConfiguration cs;
	
	public String prefix = "§b" + getDescription().getName() + "§8 » ";
	ConsoleCommandSender send = Bukkit.getConsoleSender();
	
	@Override
	public void onEnable() {
		instance = this;
		GerarConfig();
		getCommand("coins").setExecutor(new CoinsCommand());
		getCommand("cash").setExecutor(new CashCommand());
		PluginManager e = Bukkit.getPluginManager();
		e.registerEvents(new onJoin(), this);
	}
	
	void GerarConfig() {
		coins = new File(getDataFolder(), "coins.yml");
		if (!coins.exists()) {
			saveResource("coins.yml", false);
			send.sendMessage(prefix + "§eCriando `§bcoins.yml§e`");
		}
		coins = new File(getDataFolder(), "coins.yml");
		c = YamlConfiguration.loadConfiguration(coins);
		send.sendMessage(prefix + "§eCarregando `§bcoins.yml§e`");
		///////////////////////////////
		cash = new File(getDataFolder(), "cash.yml");
		if (!cash.exists()) {
			saveResource("cash.yml", false);
			send.sendMessage(prefix + "§eCriando `§bcash.yml§e`");
		}
		cash = new File(getDataFolder(), "cash.yml");
		cs = YamlConfiguration.loadConfiguration(cash);
		send.sendMessage(prefix + "§eCarregando `§bcash.yml§e`");
	}
	
	@Override
	public void onDisable() {
	}
	
	public static YamlConfiguration getCoins() {
		return c;
	}

	public void saveCoins() {
		try {
			getCoins().save(coins);
			send.sendMessage(prefix + "§eSalvando `coins.yml`");
		} catch (IOException e) {
			send.sendMessage(prefix + "§cNão Foi Possivel Salvar `coins.yml`");
		}
	}
	
	public static YamlConfiguration getCash() {
		return cs;
	}

	public void saveCash() {
		try {
			cs.save(cash);
			send.sendMessage(prefix + "§eSalvando `cash.yml`");
		} catch (IOException e) {
			send.sendMessage(prefix + "§cNão Foi Possivel Salvar `cash.yml`");
		}
	}
	
}
