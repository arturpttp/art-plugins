package net.dev.art.facs.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.facs.Main;
import net.dev.art.grupos.api.GruposAPI;

public class onHit implements Listener {

	public List<String> delay = new ArrayList<>();

	@EventHandler
	void hit(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player p = (Player) e.getEntity();
			Player dm = (Player) e.getDamager();
			if (Main.players.get(p.getName()).hasFaction()) {
				if (Main.players.get(p.getName()).getFaction()
						.equalsIgnoreCase(Main.players.get(dm.getName()).getFaction())) {
					e.setCancelled(true);
				} else {
					if (!delay.contains(dm.getName())) {
						dm.sendMessage(GruposAPI.getGrupo(p.getName()).getPrefix() + " " + p.getName()
								+ " §6Está com §c" + p.getHealth() + "♥");
						delay.add(dm.getName());
					}
					Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
						new BukkitRunnable() {
							int dl = 5;

							@Override
							public void run() {
								if (delay.contains(dm.getName())) {
									dl--;
									if (dl == 0) {
										delay.remove(dm.getName());
										dl--;
									}

								}
							}
						}.runTaskTimer(Main.getInstance(), 20, 20);
					});

				}
			}
		}
	}
}
