package net.dev.art.aut;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		e.registerEvents(new Eventos(), this);
		setCommand("login", new LoginCommand());
		setCommand("autinfo", new InfoCommand());
		setCommand("register", new RegisterCommand());
		setCommand("changepassword", new ChangePasswordCommand());
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
		for (Player on : Bukkit.getOnlinePlayers()) {
			AutAPI.deslogar(on.getName());
		}
	}

}
