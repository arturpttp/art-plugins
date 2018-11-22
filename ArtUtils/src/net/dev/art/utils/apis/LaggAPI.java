package net.dev.art.utils.apis;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import net.dev.art.core.ActionBar;
import net.dev.art.utils.Main;

public class LaggAPI {

	public static void clearDrops(World world) {
		for (Item entity : world.getEntitiesByClass(Item.class)) {
			entity.remove();
		}
	}

	public static void anuncio(final int quantidade) {
		if (quantidade == 0) {
			return;
		}

		if (quantidade == 5) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cLimparei os drops em 5 minutos");
			}
			new ActionBar(Main.getInstance().prefix + "§cLimparei os drops em 5 minutos").sendToAll();
		}

		if (quantidade == 4) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cLimparei os drops em 2 minutos");
			}
			new ActionBar(Main.getInstance().prefix + "§cLimparei os drops em 2 minutos").sendToAll();
		}

		if (quantidade == 3) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cLimparei os drops em 1 minutos");
			}
			new ActionBar(Main.getInstance().prefix + "§cLimparei os drops em 1 minutos").sendToAll();

		}

		if (quantidade == 2) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cLimparei os drops em 30 segundos");
			}
			new ActionBar(Main.getInstance().prefix + "§cLimparei os drops em 30 segundos").sendToAll();
		}

		if (quantidade == 1) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.sendMessage(Main.getInstance().prefix + "§cLimpando os Drops");
			}
			new ActionBar(Main.getInstance().prefix + "§cLimpando os Drops").sendToAll();
			for (Player on : Bukkit.getOnlinePlayers()) {
				clearDrops(on.getWorld());
			}
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
