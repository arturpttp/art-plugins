package net.dev.art.punir.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.dev.art.api.APIs.CalendarioAPI;
import net.dev.art.punir.PunimentosAPI;

public class Join implements Listener {

	public static void desmutarAll() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			String path = "Bans." + p.getName().toLowerCase();
			FileConfiguration config = net.dev.art.punir.Main.getInstance().getConfig();
			long millis = config.getLong(path + ".Milliseconds");
			if (config.contains(path + ".Milliseconds")) {
				if (millis < System.currentTimeMillis()) {
					PunimentosAPI.Despunir(p, "mute");
				}
			}
		}
	}

	@EventHandler
	void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String path = "Bans." + p.getName().toLowerCase();
		FileConfiguration config = net.dev.art.punir.Main.getInstance().getConfig();

		if (PunimentosAPI.isBanido(p)) {
			String punidor = config.getString(path + ".Punidor");
			String mstring = config.getString(path + ".Motivo").replace("_", " ");
			String message = "";
			if (config.contains(path + ".Milliseconds")) {
				long millis = config.getLong(path + ".Milliseconds");
				message = "§bArtPunir\n \n §cVocê Foi Banido temporariamente Motivo:§f " + mstring
						+ "\n §cBanido Por:§f " + punidor + "\n§c Banido até:§f " + CalendarioAPI.getData(millis)
						+ "§c as §f" + CalendarioAPI.getHoras(millis);
			} else if (config.contains(path)) {
				message = "§bArtPunir\n \n §cVocê Foi Banido permanentemente Motivo:§f " + mstring
						+ "\n §cBanido Por:§f " + punidor;
			}
			p.kickPlayer(message);
			long millis = config.getLong(path + ".Milliseconds");
			if (millis < System.currentTimeMillis()) {
				PunimentosAPI.Despunir(p, "mute");
				return;
			}
			p.kickPlayer(message);
		}

	}
}
