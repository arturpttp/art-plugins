package net.dev.art.st;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.api.APIs.FormatarAPI;
import net.dev.art.core.objects.ArtPlayer;
import net.dev.art.core.objects.ProgressBar;
import net.dev.art.core.utils.API;
import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.rank.Rank;
import net.dev.art.rank.RanksAPI;
import net.dev.green.grupos.APIs.GruposAPI;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public static Main getInstance() {
		return instance;
	}

	public static File config;
	public static YamlConfiguration c;
	public boolean scoreEnable = getConfig().getBoolean("Settings.HabilitarScore");
	public boolean tabEnable = getConfig().getBoolean("Settings.HabilitarTab");

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onLoad() {
		mensagem("§eCarregando...");
	}

	@Override
	public void onEnable() {
		instance = this;
		scoreEnable = true;
		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + getDescription().getName());
		send.sendMessage(
				"§eCriado Por: §b§l" + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + getDescription().getVersion());
		send.sendMessage("");
		GerarConfig();
		run();
		Register();

	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();

	}

	private void updater() {
		new BukkitRunnable() {
			public void run() {
				for (Player on : Bukkit.getOnlinePlayers()) {
					score(on);
				}
			}
		}.runTaskTimer(this, 20, 20);
	}

	private void run() {
		new BukkitRunnable() {
			public void run() {
				if (!tabEnable)
					return;
				new TabList("§b§lArtTAblist \n §eVenha se divertir conosco!",
						"§aAcesse meu site: \n §bwww.zartur-dev.com").sendTab();
			}
		}.runTaskTimer(this, 20, 20);

		new BukkitRunnable() {
			public void run() {
				if (!scoreEnable)
					return;
				updater();
			}
		}.runTaskTimer(this, 20, 20);
	}

	private void score(Player p) {
		ScoreBoard b = new ScoreBoard(p, "§bScoreboard");

		int onl = Bukkit.getOnlinePlayers().size();
		int off = Bukkit.getMaxPlayers();
		Rank rank = RanksAPI.getCurrentRank(p);
		Rank nextrank = RanksAPI.getNextRank(p.getName());
		String rankname = rank.getPrefix().replace("&", "§");
		String barra = "";
		try {
			barra = new ProgressBar(CoinsAPI.getCoins(p), nextrank.getPrice()).getProgressBar();
		} catch (Exception e) {
			barra = "§7Você já está no ultimo rank";
		}

		b.definirLinha("§bNick: §e" + p.getName(), 15);
		b.definirLinha("§bRank: " + rankname, 14);
		b.definirLinha("§bProgresso: §b" + barra, 13);
		b.definirLinha("§bGrupo: " + GruposAPI.getPrefix(p), 12);
		b.definirLinha("§1  ", 11);
		b.definirLinha("§bCoins: §e" + CoinsAPI.getCoinsFormatado(p), 10);
		b.definirLinha("§bCash: §e" + CashAPI.getCashFormatado(p), 9);
		b.definirLinha("§9 ", 8);
		b.definirLinha("§bClan: §7Sem Clan", 7);
		b.definirLinha("§b", 6);
		b.definirLinha("§bOnline: §1" + onl + "§8/§4" + off, 5);
		b.definirLinha("§asite.com.br", 4);

		b.enviar();
//		p.sendMessage(new ProgressBar(CoinsAPI.getCoins(p), nextrank.getPrice()).getBar());
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
	}

	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {

	}

}
