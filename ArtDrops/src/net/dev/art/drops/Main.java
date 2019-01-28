package net.dev.art.drops;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.ArtPlugin;
import net.dev.art.drops.manager.DropsManager;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public static DropsManager manager;

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		manager = new DropsManager();
		instance = this;
		autoRegister(instance, "net.dev.art.drops");
		saveDefaultConfig();
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public void Register() {
	}

	public void reload() {
		debug("§aRecarregando plugin §bArtDrops");
		aoDisabilitar();

		new BukkitRunnable() {
			@Override
			public void run() {
				aoCarregar();
				aoIniciar();
			}
		}.runTaskTimer(this, 5, 5);
		debug("§aCarregamento do plugin §bArtDrops§a Completo");
	}

	@Override
	public String getPrefix() {
		return "§bArtDrops §8» ";
	}

	public static DropsManager getManager() {
		return manager;
	}

}
