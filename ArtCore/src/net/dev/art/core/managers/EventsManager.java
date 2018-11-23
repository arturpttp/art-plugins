package net.dev.art.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class EventsManager implements Listener {
	
	private transient boolean registred;
	private transient Plugin plugin;
	
	public EventsManager() {
		setPlugin(defaultPlugin());
	}

	public Plugin defaultPlugin() {
		return JavaPlugin.getProvidingPlugin(getClass());
	}

	public EventsManager(Plugin plugin) {
		register(plugin);
	}

	/**
	 * Registra o Listener para o Plugin
	 * 
	 * @param plugin
	 *            Plugin
	 */
	public void register(Plugin plugin) {
		unregisterListener();
		this.registred = true;
		setPlugin(plugin);
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	public void unregisterListener() {
		if (registred) {
			HandlerList.unregisterAll(this);
			this.registred = false;
		}
	}

	public boolean isRegistered() {
		return registred;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

}


