package net.dev.art.essentials.events;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import net.dev.art.api.APIs.Paginas;
import net.dev.art.essentials.Main;
import net.dev.art.essentials.apis.SkinAPI;

@SuppressWarnings("all")
public class CommandBlock implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("verpls"))
			return;
		String[] msg = e.getMessage().split(" ");
		List<String> plugins = new ArrayList<>();
		plugins.add("ver" + msg);
		plugins.add("help" + msg);
		plugins.add("ver");
		plugins.add("me");
		plugins.add("pl" + msg);
		plugins.add("pl");
		plugins.add("plugins" + msg);
		plugins.add("plugins");

		for (String Loop : plugins) {
			if (msg[0].equalsIgnoreCase("/" + Loop)) {
				p.sendMessage("§e=-=-=-=-=-=-=-=-=-=| §bBLOQUEADO §e|=-=-=-=-=-=-=-=-=-=");
				p.sendMessage("§eDesenvolvido Por:§b zArturDev_");
				p.sendMessage("§eQuer Meus Plugins? Chama Discord Ai:§b arturgamer#1700");
				p.sendMessage("§e=-=-=-=-=-=-=-=-=-=| §bBLOQUEADO §e|=-=-=-=-=-=-=-=-=-=");
				e.setCancelled(true);
			}
		}
	}

	public static ArrayList<Player> playerEntrar = new ArrayList();
	public static HashMap<Player, String> captcha = new HashMap();
	Player p;

	private static HttpURLConnection getConnection(String url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setConnectTimeout(1000);
		connection.setReadTimeout(1000);

		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("User-Agent", "Premium-Checker");

		return connection;
	}

	public boolean isOriginal(String name) {
		try {
			HttpURLConnection conn = getConnection("https://api.mojang.com/users/profiles/minecraft/" + name);
			if (conn.getResponseCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = reader.readLine();
				if ((line != null) && (!line.equals("null")))
					return true;
			}
		} catch (IOException localException) {
		}
		return false;
	}

	public static String getTipo(Block b) {
		if (b.getType().equals(Material.COBBLESTONE)) {
			return "Basica";
		}
		if (b.getType().equals(Material.DIAMOND_BLOCK)) {
			return "Media";
		}
		if (b.getType().equals(Material.OBSIDIAN)) {
			return "Avançada";
		}
		return null;
	}

//	@EventHandler
//	public void onConsume(PlayerItemConsumeEvent e) {
//		Player p = e.getPlayer();
//		ItemStack is = new ItemStack(Material.POTION);
//		PotionMeta pm = (PotionMeta) is.getItemMeta();
//		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
//		pm.setDisplayName("§ePoçao Boladona Daquele Jeito");
//		pm.setLore(Arrays.asList("§aPoçao de Bruxo"));
//		is.setItemMeta(pm);
//		if (e.getItem().isSimilar(is)) {
//			p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 6 * 30, 5));
//			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 6 * 30, 5));
//			p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20 * 6 * 30, 5));
//			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 6 * 30, 5));
//			
//		}
//	}

	@EventHandler
	public void join(PlayerJoinEvent e) {
		String msg = "§aVocê Está Em Uma Conta Original";
		if (!isOriginal(e.getPlayer().getName())) {
			msg = "§cVocê Está Em Uma Conta Pirata";
		}
//		ItemStack is = new ItemStack(Material.POTION);
//		PotionMeta pm = (PotionMeta) is.getItemMeta();
//		pm.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
//		pm.setDisplayName("§ePoçao Boladona Daquele Jeito");
//		pm.setLore(Arrays.asList("§aPoçao de Bruxo"));
//		is.setItemMeta(pm);
//		e.getPlayer().getInventory().addItem(is );
		e.getPlayer().sendMessage(msg);
		String skin = Main.getInstance().getConfig().getString("DefaultSkin.Nick");
		boolean enable = Main.getInstance().getConfig().getBoolean("DefaultSkin.Habilitado");
		if (enable) {
			if (!isOriginal(e.getPlayer().getName())) {
				SkinAPI.changeSkin(e.getPlayer(), skin);
				SkinAPI.updateSkin(e.getPlayer());
			}
		}

	}

	@EventHandler
	public void leave(PlayerQuitEvent e) {
		this.playerEntrar.remove(e.getPlayer());
	}

	@EventHandler
	public void move(PlayerMoveEvent e) {
		Player p = e.getPlayer();

//		if (AutAPI.isLogado(e.getPlayer().getName())) {
//			if (this.playerEntrar.contains(e.getPlayer())) {
//				p.sendMessage(
//						"§cVocê precisa digitar o captcha §f" + (String) this.captcha.get(p) + "§cpara prosseguir.");
//				p.teleport(p);
//
//			}
//		}
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
//		if (AutAPI.isLogado(e.getPlayer().getName())) {
//			if (this.playerEntrar.contains(p)) {
//				if (e.getMessage().equalsIgnoreCase((String) this.captcha.get(p))) {
//
//					this.playerEntrar.remove(e.getPlayer());
//
//					p.sendMessage("§aVocê digitou o captcha corretamente e pode prosseguir.");
//
//				} else {
//					kickar(p);
//				}
//				e.setCancelled(true);
//			}
//		}
	}

	public static String getSaltString() {
		String ss = "qwertyuiopasdfghjklçzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder s = new StringBuilder();
		Random rnd = new Random();

		while (s.length() < 7) {
			int index = (int) (rnd.nextFloat() * ss.length());
			s.append(ss.charAt(index));
		}
		String string = s.toString();
		return string;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onWeatherChange(WeatherChangeEvent event) {

		boolean rain = event.toWeatherState();
		if (rain)
			event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onThunderChange(ThunderChangeEvent event) {

		boolean storm = event.toThunderState();
		if (storm)
			event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onClick(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player))
			return;
		Player p = (Player) event.getWhoClicked();
		// Get the current scroller inventory the player is looking at, if the player is
		// looking at one.
		if (!Paginas.users.containsKey(p.getUniqueId()))
			return;
		Paginas inv = Paginas.users.get(p.getUniqueId());
		if (event.getCurrentItem() == null)
			return;
		if (event.getCurrentItem().getItemMeta() == null)
			return;
		if (event.getCurrentItem().getItemMeta().getDisplayName() == null)
			return;
		// If the pressed item was a nextpage button
		if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aAvançar para proxima pagina")) {
			event.setCancelled(true);
			// If there is no next page, don't do anything
			if (inv.currpage >= inv.pages.size() - 1) {
				return;
			} else {
				// Next page exists, flip the page
				inv.currpage += 1;
				p.openInventory(inv.pages.get(inv.currpage));
			}
			// if the pressed item was a previous page button
		} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cVoltar para ultima pagina")) {
			event.setCancelled(true);
			// If the page number is more than 0 (So a previous page exists)
			if (inv.currpage > 0) {
				// Flip to previous page
				inv.currpage -= 1;
				p.openInventory(inv.pages.get(inv.currpage));
			}
		}
	}

}
