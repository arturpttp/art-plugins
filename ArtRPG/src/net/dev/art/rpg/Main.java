package net.dev.art.rpg;

import java.io.File;

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
		autoRegister(instance, "net.dev.art.rpg");
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public void Register() {
	}

	@Override
	public String getPrefix() {
		return "§bArtRPG §8» ";
	}

	public void autoRegister(JavaPlugin pl, String pacote) {
		for (Class<?> classes : ClassGetter.getClassesForPackage(pl, pacote)) {
			try {
				if (Listener.class.isAssignableFrom(classes)) {
					Listener classe = (Listener) classes.newInstance();
					Bukkit.getPluginManager().registerEvents(classe, pl);
					console(getPrefix() + "§eCarregando listener: " + classes.getSimpleName());
				}
			} catch (Exception e) {
				console(getPrefix() + "§cErro ao carregar listener: " + classes.getSimpleName());
			}
			try {
				if (ArtCommand.class.isAssignableFrom(classes) && classes != ArtCommand.class) {
					ArtCommand command = (ArtCommand) classes.newInstance();
					((CraftServer) Bukkit.getServer()).getCommandMap().register(command.getName(), command);
					console(getPrefix() + "§eCarregando comando: §b" + command.getName() + " §eNa classe:§b "
							+ classes.getSimpleName());
				}
			} catch (Exception e) {
				console(getPrefix() + "§cErro ao carregar Classe de comando: " + classes.getSimpleName());
			}

		}
	}

}
