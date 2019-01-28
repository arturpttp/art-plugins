package net.dev.art.facs;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.ArtPlugin;
import net.dev.art.core.managers.DBManager;
import net.dev.art.core.objects.ArtPlayer;
import net.dev.art.core.objects.Mensagem;
import net.dev.art.core.utils.Configs;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.facs.manager.ArtFacManager;
import net.dev.art.facs.manager.FactionsManager;
import net.dev.art.facs.manager.MySQLManager;
import net.dev.art.facs.objects.Faction;
import net.dev.art.facs.objects.FactionPlayer;

public class Main extends ArtPlugin {

	/* Todas as variaves principais */
	public static Main instance;

	public static HashMap<String, FactionPlayer> players;
	public static HashMap<String, Faction> factions;

	public static HashMap<Integer, Faction> topValor;
	public static HashMap<Integer, Faction> RankingCoins;
	public static HashMap<Integer, Faction> RankingKDR;
	public static HashMap<Integer, Faction> RankingSpawners;
	public static HashMap<Integer, Faction> RankingPoder;
	public Configs factionsCF;
	public Configs playersCF;
	public Configs config;
	public DBManager db = null;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void aoCarregar() {

	}

	private void startVariables() {
		instance = this;
		/* HashMaps */
		players = new HashMap<>();
		factions = new HashMap<>();
		topValor = new HashMap<>();
		RankingCoins = new HashMap<>();
		RankingKDR = new HashMap<>();
		RankingSpawners = new HashMap<>();
		RankingPoder = new HashMap<>();
		/* Configs */
		factionsCF = new Configs("factions.yml", this);
		factionsCF.saveDefaultConfig();
		playersCF = new Configs("players.yml", this);
		playersCF.saveDefaultConfig();
		config = new Configs("config.yml", this);
		config.saveDefaultConfig();
		db = new DBManager("root", "", "localhost", "artplugins");
		db.openConnection();
		try {
			if (db.getConnection() == null || db.getConnection().isClosed()) {
				db.openConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.createTable("FactionPlayerDB",
				"nick varchar(50),fac varchar(50), money text, kills integer, deaths integer, power integer, maxpower integer");
		db.createTable("FactionFacDB", "name varchar(50), lider varchar(50), money text, base text, membros text,"
				+ " aliados text, inimigos text, capitoes text, recrutas text, tag varchar(50)");

		db.createTable("FactionChunkDB", "xz varchar(50), fac varchar(50)");
		db.createTable("FactionLocDB", "nome varchar(50), loc text");
		debug("Criando tabelas...");
	}

	@Override
	public void aoIniciar() {
		startVariables();
		autoRegister(instance, "net.dev.art.facs");
		Bukkit.getScheduler().runTask(this, () -> {
			loadPlayers();
			loadFacs();
		});
		for (Faction aFac : factions.values()) {
			for (Player on : Bukkit.getOnlinePlayers()) {
				if (players.get(on.getName()).getFac() == aFac
						&& players.get(on.getName()).getFaction() == aFac.getNome()) {
					aFac.addOnline(on.getName());
				}
			}
		}
	}

	private void saveFacs() {
		int facs = 0;
		for (Entry<String, Faction> entry : factions.entrySet()) {
			String FName = entry.getKey();
			Faction ff = entry.getValue();
			double banco = ff.getBanco();
			String membros = ArtFacManager.stripList(ff.getMembros());
			String aliados = ArtFacManager.stripList(ff.getAliados());
			String inimigos = ArtFacManager.stripList(ff.getInimigos());
			String capitoes = ArtFacManager.stripList(ff.getCapitoes());
			String recrutas = ArtFacManager.stripList(ff.getRecrutas());
			String tag = ff.getTag();
			String lider = ff.getLider();
			String base = (ff.getBase() == null) ? "?" : ArtFacManager.serializeLoc(ff.getBase());
			try {
				if (db.getConnection() == null || db.getConnection().isClosed()) {
					db.openConnection();
				}
				if (MySQLManager.contains("factionfacdb", "name", FName)) {
					db.execute("UPDATE `factionfacdb` SET `name`='" + FName + "',`lider`='" + lider + "',`money`='"
							+ banco + "'," + "`base`='" + base + "',`membros`='" + membros + "',`aliados`='" + aliados
							+ "',`inimigos`='" + inimigos + "',`capitoes`='" + capitoes + "'," + "`recrutas`='"
							+ recrutas + "' WHERE name='" + FName + "'");
				} else {
					db.execute(
							"INSERT INTO `factionfacdb` (`ID`, `name`, `lider`, `money`, `base`, `membros`, `aliados`, `inimigos`, `capitoes`, `recrutas`, `tag`) VALUES "
									+ "(NULL, '" + FName + "', '" + lider + "', '" + banco + "', '" + base + "', '"
									+ membros + "', " + "'" + aliados + "', '" + inimigos + "', '" + capitoes + "', '"
									+ recrutas + "', '" + tag + "');");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		debug("§eForam salvas §b" + facs + " §eFactions no `§bMySQL§e`");
	}

	private void savePlayers() {
		int facs = 0;
		for (Entry<String, FactionPlayer> entry : players.entrySet()) {
			FactionPlayer fp = entry.getValue();
			String name = entry.getKey();
			String fac = fp.getFaction();
			String money = "0.0";
			try {
				money = "" + CoinsAPI.getCoins(name);
			} catch (NoClassDefFoundError e) {
				money = "0.0";
			}
			int kills = fp.getKills();
			int deaths = fp.getDeaths();
			int power = fp.getPower();
			int maxpower = fp.getMaxPower();
			try {
				if (db.getConnection() == null || db.getConnection().isClosed()) {
					db.openConnection();
				}
				if (MySQLManager.contains("factionplayerdb", "nick", name)) {
					db.execute("UPDATE `factionplayerdb` SET `nick`='" + name + "',`fac`='" + fp.getFaction()
							+ "',`money`='" + CoinsAPI.getCoins(name) + "',`kills`='" + fp.getKills() + "',`deaths`='"
							+ fp.getDeaths() + "',`power`='" + fp.getPower() + "',`maxpower`='" + fp.getMaxPower()
							+ "' WHERE `nick`='" + name + "'");
				} else {
					db.execute(
							"INSERT INTO `factionplayerdb` (`ID`, `nick`, `fac`, `money`, `kills`, `deaths`, `power`, `maxpower`) VALUES "
									+ "(NULL, '" + name + "', '" + fac + "', '" + money + "', '" + kills + "', '"
									+ deaths + "', '" + power + "', '" + maxpower + "');");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			facs++;
		}
		debug("§eForam salvos §b" + facs + "§e Players no `§bMySQL§e`");
	}

	private void loadFacs() {
		int facs = 0;
		for (Faction f : MySQLManager.getAllFactions()) {
			Faction fac = new Faction(f.getID(), f.getAliados(), f.getInimigos(), f.getBanco(), f.getCapitoes(),
					f.getMembros(), f.getRecrutas(), f.getNome(), f.getLider(), f.getTag());
			factions.put(f.getNome(), fac);
			facs++;
		}
		debug("§eForam carregadas §b" + facs + "§e Faccoes.");
	}

	private void loadPlayers() {
		int facs = 0;
		for (FactionPlayer fp : MySQLManager.getAllPlayers()) {
			FactionPlayer p = new FactionPlayer(fp.getID(), fp.getName(), fp.getFaction(), fp.getKills(),
					fp.getDeaths(), fp.getPower(), fp.getMaxPower());
			players.put(fp.getName(), p);
			facs++;
		}
		debug("§eForam carregados §b" + facs + "§e Players.");
	}

	@Override
	public void aoDisabilitar() {
		saveFacs();
		savePlayers();
		Bukkit.getOnlinePlayers().forEach(on -> {
			on.kickPlayer("§cServidor reiniciando!");
		});
		Main.getInstance().playersCF.saveConfig();
		db.closeConnection();
	}

	@Override
	public void Register() {
	}

	@Override
	public String getPrefix() {
		return "§bArtFac §8» ";
	}

}
