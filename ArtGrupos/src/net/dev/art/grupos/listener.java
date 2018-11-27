package net.dev.art.grupos;

import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.grupos.api.NamesAPI;
import net.dev.art.grupos.objetos.Grupo;

public class listener implements Listener {
	@EventHandler
	void onJoin(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		try {
			GruposAPI.setGrupo(p.getName(), Main.getInstance().config.getString(p.getName()));
		} catch (Exception e2) {
			GruposAPI.setGrupo(p.getName(), new Grupo("§8§lMEMBRO", "Membro", Arrays.asList("membro")));
		}

	}
}
