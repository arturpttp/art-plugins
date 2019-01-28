package net.dev.art.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import net.dev.art.core.utils.ArtLib;

public class PluginsManager {

	public static void mensagem(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}

	public static void enable(String plugin) {
		Plugin pl = null;
		try {
			pl = Bukkit.getPluginManager().getPlugin(plugin);
			Bukkit.getPluginManager().enablePlugin(pl);
			mensagem("§bPluginManager§8 » §ePlugin `§b" + plugin + "§e` Habilitado");
		} catch (NullPointerException e) {
			mensagem("§cPlugin `§f" + plugin + "§c` Não encontrado!");
		}
	}

	public static void disable(String plugin) {
		Plugin pl = null;
		try {
			pl = Bukkit.getPluginManager().getPlugin(plugin);
			Bukkit.getPluginManager().disablePlugin(pl);
			mensagem("§bPluginManager§8 » §ePlugin `§b" + plugin + "§e` Desabilitado");
		} catch (NullPointerException e) {
			mensagem("§cPlugin `§f" + plugin + "§c` Não encontrado!");
		}
	}

	public static void reload(String plugin) {
		Plugin pl = null;
		try {
			pl = Bukkit.getPluginManager().getPlugin(plugin);
			disable(plugin);
			enable(plugin);
		} catch (NullPointerException e) {
			mensagem("§cPlugin `§f" + plugin + "§c` Não encontrado!");
		}
		mensagem("§bPluginManager§8 » §ePlugin `§b" + plugin + "§e` Recarregado");
	}

}
