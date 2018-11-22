package net.dev.rpg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Metodos {

	public PluginManager pm = Bukkit.getServer().getPluginManager();
	
	public PluginManager getPluginManager() {
		return pm;
	}
	
}
