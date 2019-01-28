package net.dev.art.factions;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.ArtPlugin;
import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.objects.Config;
import net.dev.art.core.utils.ClassGetter;
import net.dev.art.factions.api.Menu;
import net.dev.art.factions.objetos.Faction;
import net.dev.art.factions.objetos.FactionPlayer;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public HashMap<FactionPlayer, Faction> factions = new HashMap<>();

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		autoRegister(instance, "net.dev.art.factions");
		regMenu(instance, "net.dev.art.factions");
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public void Register() {
	}

	@Override
	public String getPrefix() {
		return "§bArtFactions §8» ";
	}

	public void regMenu(JavaPlugin pl, String pacote) {
		for (Class<?> classes : ClassGetter.getClassesForPackage(pl, pacote)) {
			try {
				if (Menu.class.isAssignableFrom(classes)) {
					Menu classe = (Menu) classes.newInstance();
					classe.register(pl);
					console(getPrefix() + "§eCarregando Menu: §b" + classes.getSimpleName());
				}
			} catch (Exception e) {
				console(getPrefix() + "§cErro ao carregar Menu: " + classes.getSimpleName());
			}
		}
	}

}
