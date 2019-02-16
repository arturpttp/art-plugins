package net.dev.art.tuto;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.tuto.managers.ArtCommand;
import net.dev.art.tuto.utils.ArtLIB;

public abstract class ArtPlugin extends JavaPlugin implements ArtLIB {

	@Override
	public void onLoad() {
		aoCarregar();
	}

	@Override
	public void onEnable() {
		aoAbilitar();
		register();
	}

	@Override
	public void onDisable() {
		aoDesabilitar();
	}

	public void reload(ArtPlugin pl) {
		pl.aoDesabilitar();

		pl.aoCarregar();
		pl.aoAbilitar();

	}

	public abstract void aoCarregar();

	public abstract void aoAbilitar();

	public abstract void aoDesabilitar();

	public abstract void register();

	public abstract String getPrefix();

	public void autoRegister(JavaPlugin pl, String pacote) {
		for (Class<?> classes : ClassGetter.getClassesForPackage(pl, pacote)) {
			try {
				if (Listener.class.isAssignableFrom(classes) && classes != ArtCore.class
						&& classes != ArtPlugin.class) {
					Listener classe = (Listener) classes.newInstance();
					Bukkit.getPluginManager().registerEvents(classe, pl);
					console(getPrefix() + "§eCarregando listener: " + classes.getSimpleName());
				}
			} catch (Exception e) {
				console(getPrefix() + "§cErro ao carregar listener: §b" + classes.getSimpleName());
			}
			try {
				if (ArtCommand.class.isAssignableFrom(classes) && classes != ArtCommand.class) {
					ArtCommand command = (ArtCommand) classes.newInstance();
					((CraftServer) Bukkit.getServer()).getCommandMap().register(command.getName(), command);
					console(getPrefix() + "§eCarregando comando: §b" + command.getName() + " §eNa classe:§b "
							+ classes.getSimpleName());
				}
			} catch (Exception e) {
				e.printStackTrace();
				console(getPrefix() + "§cErro ao carregar Classe de comando: " + classes.getSimpleName());
			}
		}
	}

}
