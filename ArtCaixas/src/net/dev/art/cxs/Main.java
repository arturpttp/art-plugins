package net.dev.art.cxs;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.cxs.commands.CommandTemplate;
import net.dev.art.cxs.utils.Mensagens;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public static Main getInstance() {
		return instance;
	}

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	public static File config;
	public static YamlConfiguration c;

	@Override
	public void onLoad() {
		mensagem("§eCarregando...");
	}

	public void createFile() {
		config = new File(getDataFolder(), "config.yml");
		if (!config.exists()) {
			saveResource("config.yml", false);
			send.sendMessage("§aConfig §8» " + "§eCriando `§bconfig.yml§e`");
		}
		config = new File(getDataFolder(), "config.yml");
		c = YamlConfiguration.loadConfiguration(config);
		send.sendMessage("§aConfig §8» " + "§eCarregando `§bconfig.yml§e`");
	}

	public static void saveConfigs() {
		File cxb = new File(getInstance().getDataFolder() + File.separator + "caixas", "basica.yml");
		if (!cxb.exists()) {
			instance.saveResource("caixas" + File.separator + "basica.yml", false);
			Mensagens.mensagem("§eCriando Arquivo Da CAIXA§b " + cxb.getName().replace(".yml", ""));
		}
		cxb = new File(instance.getDataFolder(), "basica.yml");
		Mensagens.mensagem("§eCarregando Arquivo Da CAIXA§b " + cxb.getName().replace(".yml", ""));
	}

	public static void saveFile() {
		try {
			c.save(config);
			Mensagens.mensagem("§eSavlando arquivo de configuracao `config.yml`");
		} catch (Exception e) {
			Mensagens.erro("Não foi possovel salvar o arquivo de configuracao `config.yml`");
		}
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

		createFile();
		saveConfigs();
		saveFile();
		sendConsoleInfos();
		Register();
	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		getCommand("caixa").setExecutor(new CommandTemplate());

	}

	private void sendConsoleInfos() {
	}

	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {

	}

}
