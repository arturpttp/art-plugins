package net.dev.art.aut;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.api.APIs.CalendarioAPI;
import net.dev.art.api.APIs.TitleAPI;
import net.dev.art.punir.PunimentosAPI;

public class Eventos implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		AutAPI.deslogar(e.getPlayer().getName());
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;
		if (e.getInventory().getTitle().equalsIgnoreCase("§eAutenticador Info"))
			e.setCancelled(true);
	}
	
	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if (!AutAPI.isRegistrado(p.getName())) {
			p.sendMessage("§aRegistre-se por favor: /register <senha> <confirma>");
			TitleAPI.sendTitle(p, "§bArtAutenticador",
					"§eVocê Deve Se Registrar Para Proseguir! §f/register <senha> <confirmasenha>");
		} else {
			p.sendMessage("§aLogue-se por favor: /login <senha>");
		}
		new BukkitRunnable() {

			@Override
			public void run() {
				if (!AutAPI.isLogado(p.getName())) {
					p.kickPlayer("§cVocê demorou para entrar");
				}
			}
		}.runTaskLater(Main.getInstance(), 20 * 15);
	}

	@EventHandler
	public void naoMover(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (!AutAPI.isLogado(p.getName())) {
			p.teleport(e.getFrom());
		}
		if (AutAPI.isLogado(p.getName()))
			return;
		if (!AutAPI.isRegistrado(p.getName())) {
			p.sendMessage("§aRegistre-se por favor: /register <senha> <confirma>");
			TitleAPI.sendTitle(p, "§bArtAutenticador",
					"§eVocê Deve Se Registrar Para Proseguir! §f/register <senha> <confirmasenha>");
		} else {
			p.sendMessage("§aLogue-se por favor: /login <senha>");
		}
	}

	@EventHandler
	public void command(PlayerCommandPreprocessEvent e) {
		String p = e.getPlayer().getName();
		if (!AutAPI.isRegistrado(p)) {
			if (e.getMessage().startsWith("/register")) {

			} else {
				e.getPlayer().sendMessage("§cSem Comandos Para Você.. Registre-se Primeiro");
			}
		}
		if (!AutAPI.isLogado(p)) {
			if (e.getMessage().startsWith("/login")) {

			} else {
				e.getPlayer().sendMessage("§cSem Comandos Para Você.. logue-se Primeiro");
			}
		}
	}

	@EventHandler
	public void naoLevarDano(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (!AutAPI.isLogado(p.getName())) {
				e.setCancelled(true);
			}
		}
	}

}

