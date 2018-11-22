package net.dev.green.grupos.Eventos;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.dev.green.grupos.APIs.GruposAPI;
import net.dev.green.grupos.APIs.GruposAPI.GruposTipos;

public class GrupoEvent implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		Player p = e.getPlayer();
		
		GruposAPI.setGrupo(p, GruposAPI.getGrupo(p));
		if (GruposAPI.getGrupo(p) != GruposTipos.Membro) {
			Bukkit.broadcastMessage("§a§lGreenzMC§8 » " + GruposAPI.getPrefix(GruposAPI.getGrupo(p)) + " " + p.getName() + " §eEntrou No Server!");
		}
		
	}
	
}
