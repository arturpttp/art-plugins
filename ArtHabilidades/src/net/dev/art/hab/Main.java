package net.dev.art.hab;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.api.APIs.FormatarAPI;
import net.dev.art.hab.commands.Habilidades;
import net.dev.art.hab.events.BlindadoEvent;
import net.dev.art.hab.events.JoinConfigPut;
import net.dev.art.hab.events.TDSEvent;
import net.dev.art.hab.utils.Habilidade;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public static Main getInstance() {
		return instance;
	}

	public static File config;
	public static YamlConfiguration c;

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	public List<Habilidade> habilidades = new ArrayList<>();

	public HashMap<HashMap<Player, String>, Integer> inv = new HashMap<>();

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
		loadHabilidades();
		Register();
	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		getCommand("habilidades").setExecutor(new Habilidades());
		e.registerEvents(new BlindadoEvent(), this);
		e.registerEvents(new TDSEvent(), this);
		e.registerEvents(new Habilidades(), this);
		e.registerEvents(new JoinConfigPut(), this);

	}

	private void loadHabilidades() {
		Habilidade blindado = new Habilidade("Blindado", 10000);
		habilidades.add(blindado);
		Habilidade tds = new Habilidade("Trevo_Da_Sorte", 10000);
		habilidades.add(tds);
		Habilidade ferreiro = new Habilidade("Ferreiro", 10000);
		habilidades.add(ferreiro);

		mensagem("§eCarregando Abilidade`§b" + blindado.getName().replace("_", " ") + "§e`! Preco: §b"
				+ FormatarAPI.doubleFormatado(blindado.getPrice()));

		mensagem("§eCarregando Abilidade`§b" + tds.getName().replace("_", " ") + "§e`! Preco: §b"
				+ FormatarAPI.doubleFormatado(tds.getPrice()));

		mensagem("§eCarregando Abilidade`§b" + ferreiro.getName().replace("_", " ") + "§e`! Preco: §b"
				+ FormatarAPI.doubleFormatado(ferreiro.getPrice()));

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
