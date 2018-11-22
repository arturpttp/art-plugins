package net.dev.art.essentials.apis;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Item;

import net.dev.art.essentials.Main;

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
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("   \u00A7e* \u00A7e\u00A7lREINICIANDO");
			Bukkit.broadcastMessage("   \u00A7e* \u00A7eServidor reiniciando em 2 minutos!");
			Bukkit.broadcastMessage(" ");
		}

		if (quantidade == 3) {
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("   \u00A7e* \u00A7e\u00A7lREINICIANDO");
			Bukkit.broadcastMessage("   \u00A7e* \u00A7eServidor reiniciando em 1 minuto!");
			Bukkit.broadcastMessage(" ");
		}

		if (quantidade == 2) {
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("   \u00A7e* \u00A7e\u00A7lREINICIANDO");
			Bukkit.broadcastMessage("   \u00A7e* \u00A7eServidor reiniciando em 30 segundos!");
			Bukkit.broadcastMessage(" ");

		}

		if (quantidade == 1) {
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("   \u00A7e* \u00A7e\u00A7lREINICIANDO");
			Bukkit.broadcastMessage("   \u00A7e* \u00A7eServidor reiniciando!");
			Bukkit.broadcastMessage(" ");
		}

		if (quantidade == 0) {
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "stop");
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
