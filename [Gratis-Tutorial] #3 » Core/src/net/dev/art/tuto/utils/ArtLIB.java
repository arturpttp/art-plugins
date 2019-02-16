package net.dev.art.tuto.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public interface ArtLIB {

	public default void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}

	public default void broadcast(String msg) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(msg);
		}
	}

	public default void broadcastWithPerm(String msg, String perm) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!p.hasPermission(perm))
				return;
			p.sendMessage(msg);
		}
	}

}
