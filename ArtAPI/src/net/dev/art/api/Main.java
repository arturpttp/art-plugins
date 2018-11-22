package net.dev.art.api;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.api.APIs.ArtSQL;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public static Main getInstance() {
		return instance;
	}

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onLoad() {
		mensagem("§eCarregando...");
	}

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + getDescription().getName());
		send.sendMessage(
				"§eCriado Por: §b§l" + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + getDescription().getVersion());
		send.sendMessage("");
		sendConsoleInfos();

	}

	private void sendConsoleInfos() {
		mensagem("§eRegistrando APIs");
		send.sendMessage("§a");
		mensagem("§eRegistrando `§b§lCalendarioAPI§e`");
		mensagem("§eRegistrando `§b§lConfigAPI§e`");
		mensagem("§eRegistrando `§b§lCooldownAPI§e`");
		mensagem("§eRegistrando `§b§lEncantamentoAPI§e`");
		mensagem("§eRegistrando `§b§lFormatarAPI§e`");
		mensagem("§eRegistrando `§b§ltemsAPI§e`");
		mensagem("§eRegistrando `§b§lTitleAPI§e`");
		mensagem("§eRegistrando `§b§lMensagensAPI§e`");
		send.sendMessage("§a");
	}

	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {
		new ArtSQL().close();
	}

}
