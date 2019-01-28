package net.dev.art.core.utils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public interface ArtListener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e);
	
}
