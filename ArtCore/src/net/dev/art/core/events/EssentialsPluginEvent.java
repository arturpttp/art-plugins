package net.dev.art.core.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

import net.dev.art.core.ArtPlugin;
import net.dev.art.core.utils.ArtLib;

public class EssentialsPluginEvent implements Listener, ArtLib {

	@EventHandler
	void onEnable(PluginEnableEvent e) {
		if (e.getPlugin() instanceof ArtPlugin) {
			ArtPlugin pl = (ArtPlugin) e.getPlugin();
			console("§b" + pl.getName() + " §8» " + "§aVersão:§b" + pl.getDescription().getVersion()
					+ " §aHABILITADO§a!");
		}
	}

	@EventHandler
	void onDissable(PluginDisableEvent e) {
		if (e.getPlugin() instanceof ArtPlugin) {
			ArtPlugin pl = (ArtPlugin) e.getPlugin();
			console("§b" + pl.getName() + " §8» " + "§cVersão:§b" + pl.getDescription().getVersion()
					+ " §cDISABILITADO§c!");
		}
	}

}
