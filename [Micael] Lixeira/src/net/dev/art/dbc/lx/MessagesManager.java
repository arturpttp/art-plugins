package net.dev.art.dbc.lx;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public interface MessagesManager {

	default void send(String msg) {
		Bukkit.getConsoleSender().sendMessage(Main.getInstance().prefix + msg);
	}

	default void send(Player p, String msg) {
		p.sendMessage(Main.getInstance().prefix + msg);
	}

	default void sendError(String error) {
		send("§c" + error);
	}

}
