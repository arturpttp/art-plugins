package net.dev.art.utilidades.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.utilidades.Main;

public class CombatLog implements Listener {

	public static List<Player> players = new ArrayList<>();

	@EventHandler
	public void aoSair(PlayerQuitEvent e) {

		Player p = e.getPlayer();
		if (!Main.getInstance().getConfig().getBoolean("combatlog-enable")) {
			return;
		}
		if (players.contains(p)) {

			p.damage(1000);
			players.remove(p);
			Bukkit.broadcastMessage("§cO Jogador §f" + p.getName() + " §cSaiu");

		}
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void aoDigitarComandos(PlayerCommandPreprocessEvent e) {

		Player p = e.getPlayer();
		if (!Main.getInstance().getConfig().getBoolean("combatlog-enable")) {
			return;
		}
		if (players.contains(p)) {
			List<String> cmdper = new ArrayList<>();
			cmdper.add("/gm");
			for (String cmd : cmdper) {
				if (e.getMessage().toLowerCase().startsWith(cmd.toLowerCase())) {
					return;
				}
			}
			e.setCancelled(true);
			p.sendMessage("§cVocê não pode digitar comandos em combate");

		}
	}

	@EventHandler
	public void aoMorrer(PlayerDeathEvent e) {

		if (e.getEntity() instanceof Player) {

			Player morreu = (Player) e.getEntity();

			if (e.getEntity().getKiller() instanceof Player) {
				players.remove(morreu);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void aoBater(EntityDamageByEntityEvent e) {

		if (!Main.getInstance().getConfig().getBoolean("combatlog-enable")) {
			return;
		}

		Integer time = 15;
		if (e.getEntity() instanceof Player) {

			if (e.getDamager() instanceof Player) {

				Player entity = (Player) e.getEntity();
				Player damager = (Player) e.getDamager();

				if (!players.contains(entity)) {

					players.add(entity);
					entity.sendMessage("§aVocê entrou em combate aguarde§f " + time + " §apara sair");

					new BukkitRunnable() {

						public void run() {

							players.remove(entity);
							entity.sendMessage("§aVocê saiu de combate!");
							entity.playSound(entity.getLocation(), Sound.LEVEL_UP, 1, 1);

						}
					}.runTaskLaterAsynchronously(Main.getInstance(), 20 * time);
				}

				if (!players.contains(damager)) {

					players.add(damager);
					damager.sendMessage("§aVocê entrou em combate aguarde§f " + time + " §apara sair");

					new BukkitRunnable() {

						public void run() {

							players.remove(damager);
							damager.sendMessage("§aVocê saiu de combate!");
							damager.playSound(damager.getLocation(), Sound.LEVEL_UP, 1, 1);
							cancel();
						}
					}.runTaskLaterAsynchronously(Main.getInstance(), 20 * time);

				}
			}
		}
	}
}
