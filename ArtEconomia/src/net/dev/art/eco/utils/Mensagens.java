package net.dev.art.eco.utils;

import org.bukkit.entity.Player;

import net.dev.art.eco.Main;

public class Mensagens {

	public static String prefix = Main.getInstance().prefix;
	public static String NoPerm = prefix + "§cVoce Nao Tem Permissao Para Isso!";
	public static String NoPlayer = prefix + "§cComando Apenas Para Players!";
	
	public void mensagem(Player p, String mensagem) {
		p.sendMessage(prefix + mensagem);
	}
	
	public static void mensagem(String mensagem, Player p) {
		p.sendMessage(prefix + mensagem);
	}
	
	
	
}
