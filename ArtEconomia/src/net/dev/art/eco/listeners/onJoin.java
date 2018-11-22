package net.dev.art.eco.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;

public class onJoin implements Listener {

	@EventHandler
	void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!CashAPI.config.contains(p.getName().toLowerCase())) {
			CashAPI.setCash(p, 0.0);
		}

		if (!CoinsAPI.config.contains(p.getName().toLowerCase())) {
			CoinsAPI.setCoins(p, 0.0);
		}
	}
	
}
