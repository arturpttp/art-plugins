package net.dev.art.maquinas;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.ArtPlugin;
import net.dev.art.maquinas.managers.MaquinaManager;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		autoRegister(instance, "net.dev.art.maquinas");
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public void Register() {
	}

	@Override
	public String getPrefix() {
		return "§bArtMaquinas §8» ";
	}

}
