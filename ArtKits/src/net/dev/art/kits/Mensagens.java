package net.dev.art.kits;

import org.bukkit.entity.Player;

public class Mensagens {

	public static String prefix = Main.prefix;
	public static String perm = "artessentials.*";
	public static String NoPerm = prefix + "§cVoce Nao Tem Permissao Para Isso!";
	public static String NoPlayer = prefix + "§cComando Apenas Para Players!";
	
	public void mensagem(Player p, String mensagem) {
		p.sendMessage(prefix + mensagem);
	}
	
	
	
}
