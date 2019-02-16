package net.dev.art.tuto.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayersListener implements Listener {

	@EventHandler
	void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPlayedBefore()) {
			e.setJoinMessage("§a" + p.getName() + " Seja bem vindo. essa e sua primeira vez no servidor divirta-se");
		} else {
			e.setJoinMessage("§a" + p.getName() + " Seja bem vindo devolta ao nosso server!");
		}

	}

}
