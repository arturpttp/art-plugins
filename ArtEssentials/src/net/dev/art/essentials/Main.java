package net.dev.art.essentials;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.essentials.apis.Configs;
import net.dev.art.essentials.apis.SkinAPI;
import net.dev.art.essentials.commands.AvisoAnuncio;
import net.dev.art.essentials.commands.Build;
import net.dev.art.essentials.commands.CasaCommands;
import net.dev.art.essentials.commands.Clear;
import net.dev.art.essentials.commands.Duvidas;
import net.dev.art.essentials.commands.Fly;
import net.dev.art.essentials.commands.Gamemode;
import net.dev.art.essentials.commands.Heal;
import net.dev.art.essentials.commands.KitsManager;
import net.dev.art.essentials.commands.ReloreHand;
import net.dev.art.essentials.commands.RenameHand;
import net.dev.art.essentials.commands.Spawn;
import net.dev.art.essentials.commands.StaffManager;
import net.dev.art.essentials.commands.Teste;
import net.dev.art.essentials.commands.WarpCommand;
import net.dev.art.essentials.commands.WorldCommand;
import net.dev.art.essentials.commands.setSpawn;
import net.dev.art.essentials.events.CommandBlock;
import net.dev.art.essentials.objetos.Casa;
import net.dev.art.essentials.objetos.Warp;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();
	public String prefix = "§b" + getDescription().getName() + "§8 » ";
	public static ArrayList<Player> builds = new ArrayList<>();
	public static HashMap<String, Warp> warpsHash = new HashMap<>();
	public static HashMap<String, List<Casa>> casas = new HashMap<>();

	public static File staff;
	public static YamlConfiguration s;

	public static File duvidas;
	public static YamlConfiguration d;

	public static Configs spawn;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onLoad() {
		mensagem("§eCarregando...");
	}

	@Override
	public void onEnable() {
		instance = this;
		spawn = new Configs("spawn.yml", getInstance());
		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + getDescription().getName());
		send.sendMessage(
				"§eCriado Por: §b§l" + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + getDescription().getVersion());
		send.sendMessage("");
		Bukkit.createWorld(new WorldCreator("Lobby"));
		Bukkit.createWorld(new WorldCreator("Mina"));

		saveDefaultConfig();
		sendConsoleInfos();
		Register();
		Debug();
		reloadWarps();
		loadCasas();
		
		
	}

	private void loadCasas() {
	}

	private void Debug() {

		for (World w : Bukkit.getWorlds()) {
			w.setDifficulty(Difficulty.PEACEFUL);
		}

		for (Player on : Bukkit.getOnlinePlayers()) {
			Fly.fly.remove(on);
			on.setAllowFlight(false);
			on.setGameMode(GameMode.SURVIVAL);
			String skin = getConfig().getString("DefaultSkin.Nick");
			boolean enable = getConfig().getBoolean("DefaultSkin.Habilitado");
			if (enable) {
				SkinAPI.changeSkin(on, skin);
				SkinAPI.updateSkin(on);
			}

		}

	}

	private void saveCasas() {
		for (Entry<String, List<Casa>> casas : casas.entrySet()) {
			String name = casas.getKey();
			List<Casa> casa = casas.getValue();
			File file = new File(getDataFolder(), "Casas/" + name + ".yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			for (Casa c : casa) {
				String path = name + ".Location.";
				cfg.set(name + ".Name", c.getNome());
				cfg.set(path + "X", c.getLocation().getX());
				cfg.set(path + "Y", c.getLocation().getY());
				cfg.set(path + "Z", c.getLocation().getZ());
				cfg.set(path + "Yaw", c.getLocation().getYaw());
				cfg.set(path + "Pitch", c.getLocation().getPitch());
				cfg.set(path + "World", c.getLocation().getWorld().getName());
				cfg.set(path + "Publica", c.isPublica());
				cfg.set(path + "Criacao", c.getCriação());
				cfg.set(path + "Proprietario", c.getProprietario());
			}

			try {
				cfg.save(file);
				mensagem("§eSalvando Casas!");
			} catch (IOException e) {
				mensagem("§cErro ao salvar casas!");
			}
		}

	}

	private void saveWarps() {
		for (Entry<String, Warp> warp : warpsHash.entrySet()) {
			String name = warp.getKey();
			Warp w = warp.getValue();
			File file = new File(getDataFolder(), "Warps/" + name + ".yml");
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			String path = "Location.";
			cfg.set("Name", w.getName());
			cfg.set(path + "X", w.getLocation().getX());
			cfg.set(path + "Y", w.getLocation().getY());
			cfg.set(path + "Z", w.getLocation().getZ());
			cfg.set(path + "Yaw", w.getLocation().getYaw());
			cfg.set(path + "Pitch", w.getLocation().getPitch());
			cfg.set(path + "World", w.getLocation().getWorld().getName());
			try {
				cfg.save(file);
				mensagem("§eSalvando Warps!");
			} catch (IOException e) {
				mensagem("§cErro ao salvar warps!");
			}
		}
	}

	private void reloadWarps() {
		File folder = new File(getDataFolder(), "/Warps/");
		folder.mkdirs();
		if (folder.listFiles().length == 0)
			return;
		for (File file : folder.listFiles()) {
			YamlConfiguration cf = YamlConfiguration.loadConfiguration(file);
			String name = cf.getString("Name");
			ConfigurationSection sec = cf.getConfigurationSection("Location");

			World world = Bukkit.getWorld(sec.getString("World"));
			double x = sec.getDouble("X");
			double y = sec.getDouble("Y");
			double z = sec.getDouble("Z");
			float yaw = (float) sec.getDouble("Yaw");
			float pitch = (float) sec.getDouble("Pitch");
			Location loc = new Location(world, x, y, z, yaw, pitch);
			Warp w = new Warp(name, loc);
			warpsHash.put(name, w);
		}
	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		getCommand("gm").setExecutor(new Gamemode());
		getCommand("kits").setExecutor(new KitsManager());
		getCommand("anuncio").setExecutor(new AvisoAnuncio());
		getCommand("aviso").setExecutor(new AvisoAnuncio());
		getCommand("clear").setExecutor(new Clear());
		getCommand("fly").setExecutor(new Fly());
		getCommand("heal").setExecutor(new Heal());
//		getCommand("tell").setExecutor(new TellCommand());
		getCommand("rename").setExecutor(new RenameHand());
		getCommand("relore").setExecutor(new ReloreHand());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("setspawn").setExecutor(new setSpawn());
		getCommand("online").setExecutor(new Teste());
		getCommand("mundo").setExecutor(new WorldCommand());

		getCommand("duvidas").setExecutor(new Duvidas());
		getCommand("warp").setExecutor(new WarpCommand());
		getCommand("casa").setExecutor(new CasaCommands());

		getCommand("staff").setExecutor(new StaffManager());
		e.registerEvents(new StaffManager(), getInstance());

		getCommand("build").setExecutor(new Build());
		e.registerEvents(new BuildEvents(), this);

		e.registerEvents(new KitsManager(), getInstance());
		e.registerEvents(new CommandBlock(), getInstance());
		e.registerEvents(new Duvidas(), getInstance());

	}

	private void sendConsoleInfos() {
		GerarConfig();
		ArrayList<String> comandos = new ArrayList<>();
		comandos.add("build");
		comandos.add("warp");
		comandos.add("nada");
		send.sendMessage("§a");
		mensagem("§eRegistrando Comandos...");
		send.sendMessage("§a");

		for (String cmd : comandos) {
			mensagem("§eRegistrando Comando `§b§l" + cmd + "§e`");
		}
		send.sendMessage("§a");

		mensagem("§eCarregando Informacoes Do Server...");
		send.sendMessage("§a");
		mensagem("§eVersao Minecraft `§b§l" + getMinecraftVersion() + "§e`");
		mensagem("§eVersao API `§b§l" + getApiVersion() + "§e`");
		mensagem("§eTipo Do JAR`§b§l" + getJarType() + "§e`");
		send.sendMessage("§a");
	}

	public String getMinecraftVersion() {
		String info = Bukkit.getVersion();
		return info.split("MC: ")[1].split("\\)")[0];
	}

	public String getApiVersion() {
		String info = Bukkit.getBukkitVersion();
		String[] version = info.split("-");
		return (version[0] + "-" + version[1]);
	}

	public String getJarType() {
		String info = Bukkit.getVersion();
		String version = info.split("git-")[1];
		return version.split("-")[0];
	}

	void GerarConfig() {
		staff = new File(getDataFolder(), "staff.yml");
		if (!staff.exists()) {
			saveResource("staff.yml", false);
			send.sendMessage(prefix + "§eCriando `§bstaff.yml§e`");
		}
		staff = new File(getDataFolder(), "staff.yml");
		s = YamlConfiguration.loadConfiguration(staff);
		send.sendMessage(prefix + "§eCarregando `§bstaff.yml§e`");

		duvidas = new File(getDataFolder(), "duvidas.yml");
		if (!duvidas.exists()) {
			saveResource("duvidas.yml", false);
			send.sendMessage(prefix + "§eCriando `§bduvidas.yml§e`");
		}
		duvidas = new File(getDataFolder(), "duvidas.yml");
		d = YamlConfiguration.loadConfiguration(duvidas);
		send.sendMessage(prefix + "§eCarregando `§bduvidas.yml§e`");

	}

	public static YamlConfiguration getStaff() {
		return s;
	}

	public void saveStaff() {
		try {
			getStaff().save(staff);
			send.sendMessage(prefix + "§eSalvando `staff.yml`");
		} catch (IOException e) {
			send.sendMessage(prefix + "§cNão Foi Possivel Salvar `staff.yml`");
		}
	}

	public static YamlConfiguration getDuvidas() {
		return d;
	}

	public void saveDuvidas() {
		try {
			getDuvidas().save(duvidas);
			send.sendMessage(prefix + "§eSalvando `duvidas.yml`");
		} catch (IOException e) {
			send.sendMessage(prefix + "§cNão Foi Possivel Salvar `duvidas.yml`");
		}
	}

	public void saveSpawn() {
		try {
			getDuvidas().save(duvidas);
			send.sendMessage(prefix + "§eSalvando `spawn.yml`");
		} catch (IOException e) {
			send.sendMessage(prefix + "§cNão Foi Possivel Salvar `spawn.yml`");
		}
	}

	public static void setLocation(YamlConfiguration file, Location l) {
		file.set("X", l.getX());
		file.set("Y", l.getY());
		file.set("Z", l.getZ());
		file.set("Yaw", l.getYaw());
		file.set("Pitch", l.getPitch());
		file.set("World", l.getWorld().getName());
	}

	public static Location getLocation(YamlConfiguration file) {
		double x = file.getDouble("X");
		double y = file.getDouble("Y");
		double z = file.getDouble("Z");
		float pitch = (float) file.getDouble("Pitch");
		float yaw = (float) file.getDouble("Yaw");
		World world = Bukkit.getWorld("World");
		return new Location(world, x, y, z, yaw, pitch);
	}

	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {
		saveWarps();
		saveCasas();
	}

}
