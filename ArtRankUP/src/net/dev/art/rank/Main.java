package net.dev.art.rank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.api.APIs.TitleAPI;
import net.dev.art.eco.apis.CoinsAPI;

public class Main extends JavaPlugin implements Listener {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public static Main getInstance() {
		return instance;
	}

	public static File config;
	public static YamlConfiguration c;

	public static File rank;
	public static YamlConfiguration r;

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	public List<Rank> ranks = new ArrayList<>();
	public HashMap<String, Rank> rankplayer = new HashMap<>();

	@Override
	public void onLoad() {
		mensagem("§eCarregando...");
	}

	@Override
	public void onEnable() {
		instance = this;

		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + getDescription().getName());
		send.sendMessage(
				"§eCriado Por: §b§l" + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + getDescription().getVersion());
		send.sendMessage("");

		GerarConfig();
		Register();
		carregarRanks();

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (RanksAPI.getCurrentRank(p) == null) {
				RanksAPI.setRank(p.getName(), 1);
			} else {
				RanksAPI.setRank(p.getName(), RanksAPI.getCurrentRank(p));
			}
		}
	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		e.registerEvents(this, this);
		getCommand("rankup").setExecutor(new RankUP());
		getCommand("rankupnpc").setExecutor(new RankUP());
		getCommand("setrank").setExecutor(new setRank());

	}

	public void saveDefaultRanksCF() {
		r.options().copyDefaults(true);
	}

	public static void saveRanksCF() {
		try {
			r.save(rank);
		} catch (IOException e) {
			getInstance().send.sendMessage(getInstance().prefix + "§cNão Foi Possivel Salvar a Config `ranks.yml`");
		}
	}

	private void carregarRanks() {
		for (String name : r.getConfigurationSection("Ranks").getKeys(false)) {
			String prefix = r.getString("Ranks." + name + ".Prefix");
			double price = r.getDouble("Ranks." + name + ".Price");
			int position = r.getInt("Ranks." + name + ".Position");
			Rank r = new Rank(name, position, price, prefix);
			ranks.add(r);
			mensagem("§eCarregando Rank `" + r.getPrefix().replace("&", "§") + "§e`");
		}
	}

	void GerarConfig() {
		config = new File(getDataFolder(), "config.yml");
		if (!config.exists()) {
			saveResource("config.yml", false);
			send.sendMessage(prefix + "§eCriando `§bconfig.yml§e`");
		}
		config = new File(getDataFolder(), "config.yml");
		c = YamlConfiguration.loadConfiguration(config);
		send.sendMessage(prefix + "§eCarregando `§bconfig.yml§e`");

		rank = new File(getDataFolder(), "ranks.yml");
		if (!rank.exists()) {
			saveResource("ranks.yml", false);
			send.sendMessage(prefix + "§eCriando `§branks.yml§e`");
		}
		rank = new File(getDataFolder(), "ranks.yml");
		r = YamlConfiguration.loadConfiguration(rank);
		send.sendMessage(prefix + "§eCarregando `§branks.yml§e`");

	}

	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (RanksAPI.getCurrentRank(p) == null) {
				RanksAPI.setRank(p.getName(), 1);
			} else {
				RanksAPI.setRank(p.getName(), RanksAPI.getCurrentRank(p));
			}
		}
	}

	public void mensagem(Player p, String mensagem) {
		p.sendMessage(prefix + mensagem);
	}

	@EventHandler
	void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (RanksAPI.getCurrentRank(p) == null) {
			RanksAPI.setRank(p.getName(), 1);
		} else {
			RanksAPI.setRank(p.getName(), RanksAPI.getCurrentRank(p));
		}
	}

	private Color getColor(int i) {
		Color c = null;
		if (i == 1) {
			c = Color.AQUA;
		}
		if (i == 2) {
			c = Color.BLACK;
		}
		if (i == 3) {
			c = Color.BLUE;
		}
		if (i == 4) {
			c = Color.FUCHSIA;
		}
		if (i == 5) {
			c = Color.GRAY;
		}
		if (i == 6) {
			c = Color.GREEN;
		}
		if (i == 7) {
			c = Color.LIME;
		}
		if (i == 8) {
			c = Color.MAROON;
		}
		if (i == 9) {
			c = Color.NAVY;
		}
		if (i == 10) {
			c = Color.OLIVE;
		}
		if (i == 11) {
			c = Color.ORANGE;
		}
		if (i == 12) {
			c = Color.PURPLE;
		}
		if (i == 13) {
			c = Color.RED;
		}
		if (i == 14) {
			c = Color.SILVER;
		}
		if (i == 15) {
			c = Color.TEAL;
		}
		if (i == 16) {
			c = Color.WHITE;
		}
		if (i == 17) {
			c = Color.YELLOW;
		}

		return c;
	}

	void spawnFogos(Player p) {
		Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();

		Random r = new Random();

		int rt = r.nextInt(17) + 1;
		Type type = Type.BALL;
		if (rt == 1)
			type = Type.BALL;
		if (rt == 2)
			type = Type.BALL_LARGE;
		if (rt == 3)
			type = Type.BURST;
		if (rt == 4)
			type = Type.CREEPER;
		if (rt == 5)
			type = Type.STAR;

		int r1i = r.nextInt(17) + 1;
		int r2i = r.nextInt(17) + 1;
		Color c1 = getColor(r1i);
		Color c2 = getColor(r2i);

		FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type)
				.trail(r.nextBoolean()).build();

		fwm.addEffect(effect);

		int rp = r.nextInt(2) + 1;
		fwm.setPower(rp);

		fw.setFireworkMeta(fwm);
	}

	@EventHandler
	void onClickInv(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;
		if (e.getInventory().getTitle().equalsIgnoreCase("§6§lRank§5§lUP")) {
			e.setCancelled(true);
			Rank r = RanksAPI.getNextRank(p.getName());
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSim")) {
				if (CoinsAPI.getCoins(p) < r.getPrice()) {
					p.closeInventory();
					mensagem(p, "§cVocê não tem coins suficiente você precisa de mais §f"
							+ (r.getPrice() - CoinsAPI.getCoins(p)));
					return;
				}
				
				CoinsAPI.removerCoins(p, r.getPrice());
				
				RanksAPI.RankUP(p.getName());
				spawnFogos(p);
				spawnFogos(p);
				spawnFogos(p);
				spawnFogos(p);
				spawnFogos(p);
				p.closeInventory();
				for (Player on : Bukkit.getOnlinePlayers()) {
					;
					TitleAPI.sendActionBar(
							"§b" + p.getName() + "§a upou para o rank: " + r.getPrefix().replace("&", "§"), on);
				}
				mensagem(p, "§aVocê upou para o rank: " + r.getPrefix().replace("&", "§") + "§a com sucesso");
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cNão")) {
				p.closeInventory();
			}

		}
	}

	@EventHandler
	void onClickEntity1(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		Entity en = e.getEntity();
		if (en.getName().equalsIgnoreCase("§6§lRank§5§lUP")) {
			if (p.hasPermission("matarrank")) {
				if (p.getItemInHand().getType() == Material.STICK && p.getGameMode() == GameMode.CREATIVE
						&& p.isSneaking()) {
					en.remove();
				}
			} else {
				RankUP.AbrirRankUP(p);
			}
		}
	}

	@EventHandler
	void onClickEntity(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		Rank r = RanksAPI.getNextRank(p.getName());
		if (r == null) {
			mensagem(p, "§cVocê já alcançou o ultimo RANK!");
			return;
		}
		if (e.getRightClicked().getName().equalsIgnoreCase("§6§lRank§5§lUP")) {
			RankUP.AbrirRankUP(p);
		}
	}

}
