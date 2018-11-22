package net.dev.rpg.Managers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.dev.rpg.APIs.ManaAPI;

public class ManaManager implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!ManaAPI.mana.containsKey(p)) {
			ManaAPI.mana.put(p, 100);
			ManaAPI.sendMana(p);
			ManaAPI.regenMana(p);
		}
	}

	@EventHandler
	public void evento(AsyncPlayerChatEvent e) {
		if (e.getMessage().contains("Mana")){
			ManaAPI.removeMana(e.getPlayer(), 10);;
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (ManaAPI.mana.containsKey(p)) {
			ManaAPI.mana.remove(p);
		}
	}

}
