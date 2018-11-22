package net.dev.green.grupos;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mensagens {

	public static String prefix = Main.getInstance().prefix;
	
	public static void sendToConsole(String msg , CommandSender s) {
		String m = prefix + msg;
		s.sendMessage(m);
	}
	
	public static void sendToPlayer(String msg , Player p) {
		String m = prefix + msg;
		p.sendMessage(m);
	}
	
	public static void sendPerm(String grupo , CommandSender s) {
		String m = prefix + "§cVocê Prescisa Ser Do Grupo " + grupo + " §cOu Superior Para Usar Isso!";
		s.sendMessage(m);
	}
	
	public static void sendPerm(String grupo , Player p) {
		String m = prefix + "§cVocê Prescisa Ser Do Grupo " + grupo + " §cOu Superior Para Usar Isso!";
		p.sendMessage(m);
	}
	
}
