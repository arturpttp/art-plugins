package net.dev.art.econ;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.ArtPlugin;
import net.dev.art.core.managers.DBManager;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public static DBManager db;

	@Override
	public void aoCarregar() {
		db = new DBManager("root", "", "localhost", "artplugins");
		db.createTable("contas", "nick varchar(50),coins varchar(50),cash varchar(50),soul varchar(50)");
		debug("Criando tabelas...");
	}

	@Override
	public void aoIniciar() {
		instance = this;
		autoRegister(instance, "net.dev.art.econ");
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public void Register() {
	}

	@Override
	public String getPrefix() {
		return "§bArtEco §8» ";
	}

}
