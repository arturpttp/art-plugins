package net.dev.art.core.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CaptchaEvents implements Listener {

	ArrayList<Player> playerEntrar = new ArrayList<>();
	HashMap<Player, String> captcha = new HashMap<>();
	Player p;

	@EventHandler
	public void join(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("captcha.bypass"))
			return;
		this.playerEntrar.add(e.getPlayer());
		this.captcha.put(e.getPlayer(), getSaltString());
	}

	@EventHandler
	public void leave(PlayerQuitEvent e) {
		this.playerEntrar.remove(e.getPlayer());
	}

	@EventHandler
	public void move(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (this.playerEntrar.contains(e.getPlayer())) {
			p.sendMessage("§cVocê precisa digitar o captcha §f" + (String) this.captcha.get(p) + "§cpara prosseguir.");
			p.teleport(p);

		}
	}

	void kickar(Player p) {
		try {
			String motivo = "§cVocê digitou seu captcha errado e foi desconectado do servidor";
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + p.getName() + " " + motivo);
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cNão Foi Possovel Kickar o Player:§f " + p.getName());
		}
	}

	@EventHandler
	public void falar(AsyncPlayerChatEvent e) {

		Player p = e.getPlayer();

		if (this.playerEntrar.contains(p)) {
			if (e.getMessage().equalsIgnoreCase((String) this.captcha.get(p))) {

				this.playerEntrar.remove(e.getPlayer());

				p.sendMessage("§aVocê digitou o captcha corretamente e pode prosseguir.");

			} else {
				kickar(p);
			}
			e.setCancelled(true);
		}
	}

	protected String getSaltString() {
		String ss = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder s = new StringBuilder();
		Random rnd = new Random();

		while (s.length() < 7) {
			int index = (int) (rnd.nextFloat() * ss.length());
			s.append(ss.charAt(index));
		}
		String string = s.toString();
		return string;
	}

}
