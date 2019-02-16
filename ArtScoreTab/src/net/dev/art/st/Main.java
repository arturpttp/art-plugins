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
import net.dev.art.core.objects.ProgressBar;
import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.facs.manager.PlayersManager;
import net.dev.art.facs.objects.Faction;
import net.dev.art.facs.objects.FactionPlayer;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.rank.Rank;
import net.dev.art.rank.RanksAPI;

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
				new TabList("§b§lArtTAblist \n §eVenha se divertir conosco!", "§aAcesse meu site: \n §bwww.artstore.tk")
						.sendTab();
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
		if (nextrank != null) {
			barra = new ProgressBar(CoinsAPI.getCoins(p), nextrank.getPrice()).getProgressBar();
		} else {
			barra = "§7Nenhum";
		}

		String fc = "";
		FactionPlayer player = PlayersManager.getPlayer(p.getName());
		Faction fac = null;
		String banco;
		String cargo = "";
		String online = "";
		if (player.hasFaction()) {
			fac = PlayersManager.getPlayer(p.getName()).getFac();
			String tag = "[" + fac.getTag() + "]";
			String nome = fac.getNome();
			fc = nome;
			if (fac.getBanco() <= 999999) {
				banco = "" + fac.getBanco();
			} else {
				banco = FormatarAPI.doubleFormatado(fac.getBanco());
			}
			cargo = "<" + player.getCargo().getSimbolo() + "> " + player.getCargo().getNome();
			online = (fac.getOnlineMembers() != null) ? "§a" + fac.getOnlineMembers().size() + "§7/§f10"
					: "§a0" + "§7/§f10";
		} else {
			fc = "Sem Facçao";
			banco = "--/--";
			cargo = "--/--";
			online = "--/--";
		}

		b.definirLinha("§e ", 15);
		b.definirLinha("  " + "§bRank: " + rankname, 14);
		b.definirLinha("  " + "§bProgresso: §b" + barra, 13);
		b.definirLinha("  " + "§bGrupo: " + GruposAPI.getGrupo(p.getName()).getPrefix(), 12);
		b.definirLinha("§1  ", 11);
		b.definirLinha("  " + "§bFac: §7" + fc, 10);
		b.definirLinha("  " + "§f » §7Banco: §f" + banco, 9);
		b.definirLinha("  " + "§f » §7Cargo: §f" + cargo, 8);
		b.definirLinha("  " + "§f » §7Online: §f" + online, 7);
		b.definirLinha("  " + "§6 ", 6);
		b.definirLinha("  " + "§bCoins: §e" + CoinsAPI.getCoinsFormatado(p), 5);
		b.definirLinha("  " + "§bCash: §e" + CashAPI.getCashFormatado(p), 4);
		b.definirLinha("§9 ", 3);
		b.definirLinha("  " + "§asite.com.br", 2);
		b.definirLinha(" §8  ", 1);
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
