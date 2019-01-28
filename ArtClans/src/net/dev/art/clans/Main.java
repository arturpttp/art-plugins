package net.dev.art.clans;

import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.clans.apis.ClansAPI;
import net.dev.art.core.ArtPlugin;
import net.dev.art.core.utils.Configs;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public static ClansAPI clansAPI = new ClansAPI();

	@Override
	public void aoCarregar() {

	}

	public static Configs config = new Configs("config.yml", instance);
	public static Configs clans = new Configs("clans.yml", instance);
	public static Configs players = new Configs("players.yml", instance);

	@Override
	public void aoIniciar() {
		instance = this;
		autoRegister(instance, "net.dev.art.clans");
		saveDefaultConfig();
		clans.saveDefaultConfig();
		players.saveDefaultConfig();
	}

	@Override
	public void aoDisabilitar() {
	}

	@Override
	public void Register() {
	}

	public void reload() {
		debug("§aRecarregando plugin §bArtTemplate");
		aoDisabilitar();

		new BukkitRunnable() {
			@Override
			public void run() {
				aoCarregar();
				aoIniciar();
			}
		}.runTaskTimer(this, 5, 5);
		debug("§aCarregamento do plugin §bArtClans§a Completo");
	}

	@Override
	public String getPrefix() {
		return "§bArtClans §8» ";
	}

	public static ClansAPI getClansManager() {
		return clansAPI;
	}

}
