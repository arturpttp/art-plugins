package net.dev.art.hab.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.dev.art.hab.Main;

public class JoinConfigPut implements Listener {

	private void setOnConfig(boolean b, Player p) {
		Main.getInstance().getConfig().set(p.getName() + ".Blindado", b);
		Main.getInstance().getConfig().set(p.getName() + ".TrevoDaSorte", b);
		Main.getInstance().getConfig().set(p.getName() + ".Ferreiro", b);
		Main.getInstance().saveConfig();
	}

	@EventHandler
	void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		setOnConfig(false, p);
	}

}
