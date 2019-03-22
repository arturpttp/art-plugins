package net.dev.art.dbc.lx;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LagManager {
	public static String prefix = "§bArtClearLag §8» ";
	public static List<String> WolrdsBlackList = new ArrayList<>();

	public static void anuncio(final int quantidade) {
		if (quantidade == 0) {
			return;
		}

		if (quantidade == 5) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(prefix + "§cLimparei os drops em 5 minutos");
			}
		}

		if (quantidade == 4) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(prefix + "§cLimparei os drops em 2 minutos");
			}
		}

		if (quantidade == 3) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(prefix + "§cLimparei os drops em 1 minutos");
			}

		}

		if (quantidade == 2) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(prefix + "§cLimparei os drops em 30 segundos");
			}
		}

		if (quantidade == 1) {
			int i = 0;

			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(prefix + "§cLimpando os Drops");
			}

			Bukkit.getWorlds().forEach(w -> {
				if (WolrdsBlackList.contains(w.getName()))
					return;
				for (Entity e : w.getEntities()) {
					if (e instanceof Creature) {
						Creature creature = (Creature) e;
						creature.remove();
					}
				}
				for (Item item : w.getEntitiesByClass(Item.class)) {
					item.remove();
				}

			});

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
	};

}
