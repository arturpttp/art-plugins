package net.dev.art.core;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface ArtLib {

	public boolean debug = true;

	public default void broadcast(String broadcast) {
		Bukkit.broadcastMessage(broadcast);
	}

	public default void mensagem(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}

	public default void debug(String msg) {
		if (debug) {
			console("§a[DEBUG] §8» §e" + msg);
		}
	}

	public default void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}

	public default void mensagem(CommandSender p, String msg) {
		p.sendMessage(msg);
	}

	public default void noPerm(CommandSender sender) {
		sender.sendMessage("§cVoce Nao Tem Permissao Para Isso!");
	}

	public default void noPlayer(CommandSender sender) {
		sender.sendMessage("§cComando Apenas Para Players!");
	}

}
