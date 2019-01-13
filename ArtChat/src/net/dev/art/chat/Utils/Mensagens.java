package net.dev.art.chat.Utils;

import net.dev.art.chat.Main;
import net.dev.art.core.utils.ArtLib;

import org.bukkit.entity.Player;

public class Mensagens implements ArtLib{

	public static String prefix = Main.getInstance().prefix;
	public static String perm = "artchat.*";
	public static String NoPerm = prefix + "§cVoce Nao Tem Permissao Para Isso!";
	public static String NoPlayer = prefix + "§cComando Apenas Para Players!";

	public void mensagem(Player p, String mensagem) {
		p.sendMessage(prefix + mensagem);
	}

}
