package net.dev.art.maquinas.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.dev.art.maquinas.Main;

public class Mensagens {

	public String prefix = Main.getInstance().prefix;

	public void mensagem(String msg) {
		msg += prefix;
		Bukkit.getConsoleSender().sendMessage(msg);
	}

	public void mensagem(Player p, String msg) {
		msg += prefix;
		p.sendMessage(msg);
	}

}
