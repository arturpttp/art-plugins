package net.dev.art.mcd;

import java.io.File;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.ArtPlugin;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

public class Main extends ArtPlugin {

	public static Main instance;

	String token = "NTQxMDg2ODMwNDYwNjY1ODU2.DzaV7g.wdjAnArfiDCBmKw_Y9c6_a8Hz48";

	public JDA bot;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		autoRegister(instance, "net.dev.art.mcd");
		startBot();
	}

	private void startBot() {
		try {
			bot = new JDABuilder(AccountType.BOT).setToken(token).setAutoReconnect(true)
					.setGame(Game.playing("sendo desenvolvido")).build();
			getBot().addEventListener(new AdminChat());
			mensagem("§aBot iniciado com sucesso");
		} catch (LoginException e) {
			mensagem("§cErro ao iniciar o bot");
		}
	}

	@Override
	public void aoDisabilitar() {
		getBot().shutdown();
		getBot().shutdownNow();
	}

	@Override
	public void Register() {
	}

	@Override
	public String getPrefix() {
		return "§bArtTemplate §8» ";
	}

	public JDA getBot() {
		return bot;
	}

	public void setBot(JDA bot) {
		this.bot = bot;
	}

}
