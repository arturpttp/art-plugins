package net.dev.art.punir;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.punir.comandos.Despunir;
import net.dev.art.punir.comandos.Kick;
import net.dev.art.punir.comandos.Punir;
import net.dev.art.punir.listeners.Join;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public static Main getInstance() {
		return instance;
	}

	public static File config;
	public static YamlConfiguration c;

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onLoad() {
		mensagemConsole("§eCarregando...");
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
		Register();
		this.saveDefaultConfig();
		Debuger();
		
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(getInstance(), new Runnable() {
			
			@Override
			public void run() {
				Join.desmutarAll();
			}
		}, 20, 20);

	}

	private void Debuger() {

	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		setCommand("punir", new Punir());
		setCommand("kickar", new Kick());
		setCommand("desmutar", new Despunir());
		setCommand("desbanir", new Despunir());
		e.registerEvents(new Join(), this);
	}

	void setCommand(String cmd, CommandExecutor classe) {
		getCommand(cmd).setExecutor(classe);
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

	public void mensagemConsole(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

	public void mensagem(Player p, String mensagem) {
		p.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {
	}

}
