package net.dev.art.drops.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.dev.art.drops.Main;

public class PlayerJoin implements Listener {

	@EventHandler
	void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!Main.getInstance().getConfig().contains(p.getName().toLowerCase())) {
			Main.getInstance().getConfig().set(p.getName().toLowerCase(), "");
		}
		p.welcome();
	}

}
