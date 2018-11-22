package net.dev.art.cxs.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.dev.art.cxs.Main;

public class Mensagens {

	public static String prefix = Main.getInstance().prefix;
	public static String perm = "artchat.*";
	public static String NoPerm = prefix + "§cVoce Nao Tem Permissao Para Isso!";
	public static String NoPlayer = prefix + "§cComando Apenas Para Players!";

	public static void mensagem(Player p, String mensagem) {
		p.sendMessage(prefix + mensagem);
	}

	public static void mensagem(String mensagem) {
		Bukkit.getConsoleSender().sendMessage(prefix + mensagem);
	}

	public static void erro(Player p, String mensagem) {
		p.sendMessage(prefix + "§c" + mensagem);
	}

	public static void erro(String mensagem) {
		Bukkit.getConsoleSender().sendMessage(prefix + "§c" + mensagem);
	}

}
