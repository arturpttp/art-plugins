package net.dev.art.utils.apis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.objects.ActionBar;
import net.dev.art.utils.Main;

public class RestartAPI {

	public static void anuncio(final int quantidade) {
		if (quantidade == 0) {
			return;
		}

		if (quantidade == 5) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cReiniciando Servidor em 5 minutos");
			}
			new ActionBar(Main.getInstance().prefix + "§cReiniciando Servidor em 5 minutos").sendToAll();
		}

		if (quantidade == 4) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cReiniciando Servidor em 2 minutos");
			}
			new ActionBar(Main.getInstance().prefix + "§cReiniciando Servidor em 2 minutos").sendToAll();
		}

		if (quantidade == 3) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cReiniciando Servidor em 1 minutos");
			}
			new ActionBar(Main.getInstance().prefix + "§cReiniciando Servidor em 1 minutos").sendToAll();

		}

		if (quantidade == 2) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cReiniciando Servidor em 30 segundos");
			}
			new ActionBar(Main.getInstance().prefix + "§cReiniciando Servidor em 30 segundos").sendToAll();
		}

		if (quantidade == 1) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cReiniciando Servidor");
			}
			new ActionBar(Main.getInstance().prefix + "§cReiniciando Servidor").sendToAll();
			for (Player on : Bukkit.getOnlinePlayers()) {
				on.kickPlayer("§bArtUtils \n §cReiniciando Servidor");
			}
			
			new BukkitRunnable() {
				public void run() {
					Bukkit.getServer().shutdown();
				}
			}.runTaskTimer(Main.getInstance(), 20, 20);
		}

		Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
			public void run() {
				if (quantidade == 0) {
					return;
				}
				if (quantidade > 0) {
					anuncio(quantidade - 1);
				}

			}
		}, 600L);
	}

}
