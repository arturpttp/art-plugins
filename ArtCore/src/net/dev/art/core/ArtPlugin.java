package net.dev.art.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.objects.API;
import net.dev.art.core.objects.ArtInventory;
import net.dev.art.core.objects.ArtItem;
import net.dev.art.core.objects.Config;
import net.dev.art.core.objects.Mensagem;
import net.dev.art.core.utils.ArtLib;
import net.dev.art.core.utils.ClassGetter;

public abstract class ArtPlugin extends JavaPlugin implements Listener, ArtLib {

	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public Plugin getPlugin() {
		return JavaPlugin.getPlugin(this.getClass());
	}

	public static File config;
	public static YamlConfiguration c;

	public List<String> comandos = new ArrayList<>();
	public List<String> eventos = new ArrayList<>();
	public List<Mensagem> mensagens = new ArrayList<>();
	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onLoad() {
		aoCarregar();
	}

	public void reload(ArtPlugin pl) {
		String name = "";
		try {
			name = pl.getDescription().getName();
		} catch (Exception e) {
			name = "ArtPlugin";
		}
		debug("§cDesligando Plugin §b" + name);
		pl.aoDisabilitar();

		debug("Habilitando Plugin §b" + name);
		pl.aoCarregar();
		pl.aoIniciar();

	}

	public void addMensagem(String name, String msg) {
		mensagens.add(new Mensagem(name, msg));
	}

	public String getMensagem(String name) {
		for (Mensagem msg : mensagens) {
			if (msg.getName().equalsIgnoreCase(name)) {
				return msg.getMensagem();
			}
		}
		return "§cA Mesagem não existe.";
	}

	@Override
	public void onEnable() {
		aoIniciar();
		Register();
		ArtItem.register(getPlugin());
		sendConsoleInfos();
//		setEvent(this);
	}

	public void sendIniciando(ArtPlugin instance) {
		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + instance.getDescription().getName());
		send.sendMessage("§eCriado Por: §b§l"
				+ instance.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + instance.getDescription().getVersion());
		send.sendMessage("");
	}

	public void register(JavaPlugin pl) {
		autoRegister(pl, "net.dev.art");
	}

	public void autoRegister(JavaPlugin pl, String pacote) {
		for (Class<?> classes : ClassGetter.getClassesForPackage(pl, pacote)) {
			try {
				if (Listener.class.isAssignableFrom(classes)) {
					Listener classe = (Listener) classes.newInstance();
					Bukkit.getPluginManager().registerEvents(classe, pl);
					console(getPrefix() + "§eCarregando listener: " + classes.getSimpleName());
				}
			} catch (Exception e) {
				console(getPrefix() + "§cErro ao carregar listener: " + classes.getSimpleName());
			}
			try {
				if (ArtCommand.class.isAssignableFrom(classes) && classes != ArtCommand.class) {
					ArtCommand command = (ArtCommand) classes.newInstance();
					((CraftServer) Bukkit.getServer()).getCommandMap().register(command.getName(), command);
					console(getPrefix() + "§eCarregando comando: §b" + command.getName() + " §eNa classe:§b "
							+ classes.getSimpleName());
				}
			} catch (Exception e) {
				console(getPrefix() + "§cErro ao carregar Classe de comando: " + classes.getSimpleName());
			}

			try {
				if (Config.class.isAssignableFrom(classes) && classes != Config.class) {
					Config config = (Config) classes.newInstance();
					config.reloadConfig();
					console(getPrefix() + "§eCarregando config: §b" + config.getName());
				}
			} catch (Exception e) {
				console(getPrefix() + "§cERRO: ao tentar carregar classe da config: §b" + classes.getSimpleName());
			}

		}
	}

	public abstract String getPrefix();

	public abstract void aoCarregar();

	public abstract void aoIniciar();

	public abstract void aoDisabilitar();

	public abstract void Register();

	public void setCommand(String cmd, CommandExecutor executor) {
		getCommand(cmd).setExecutor(executor);
		comandos.add(cmd);
	}

	public void setEvent(Listener classe) {
		PluginManager e = Bukkit.getPluginManager();
		e.registerEvents(classe, this);
	}

	private void sendConsoleInfos() {

		if (comandos.size() != 0) {
			mensagem("§eRegistrando Comandos...");
			for (String comando : comandos) {
				mensagem("§eRegistrando Comando `§b" + comando + "§e`");
			}

		}

		mensagem("§eRegistrando Eventos...");

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

	public void mensagem(Player p, String mensagem) {
		p.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {
		aoDisabilitar();
	}

}
