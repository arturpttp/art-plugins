package net.dev.art.utilidades.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

	@EventHandler
	void evento(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		int online = Bukkit.getOnlinePlayers().size();
		p.sendMessage("");
		p.sendMessage("                §e§lArtServer              ");
		p.sendMessage("");
		p.sendMessage("§b* Server Em fase §e§nBeta 1.0");
		p.sendMessage("§b* Bem Vindo Ao Server §b" + p.getName());
		p.sendMessage("§b* A Staff Nunca Pedira Sua Senha");
		p.sendMessage("§b* Há Atualmente §b" + online + " §bJogadores Online");
		p.sendMessage("§b* Visite Nosso Site Entrando Em: §e§nwww.artstore.com");
		p.sendMessage("");

	}

}
