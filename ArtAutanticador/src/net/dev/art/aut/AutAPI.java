package net.dev.art.aut;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class AutAPI {

	public static Main instance = Main.getInstance();

	public static Main getInstance() {
		return instance;
	}

	public static FileConfiguration config = getInstance().getConfig();

	public static List<String> jogadoreslogados = new ArrayList<>();

	public static void registrar(String p, String senha) {
		config.set(p.toLowerCase(), senha);
		instance.saveConfig();
	}

	public static boolean isLogado(String p) {
		return jogadoreslogados.contains(p);
	}

	public static boolean isRegistrado(String p) {
		return config.contains(p.toLowerCase());
	}

	public static void logar(String p) {
		jogadoreslogados.add(p);
	}

	public static void deslogar(String p) {
		jogadoreslogados.remove(p);
	}

	public static String getSenha(String p) {
		return config.getString(p.toLowerCase());
	}

	public static void removerConta(String conta) {
		config.set(conta.toLowerCase(), null);
		instance.saveConfig();
	}

	public static void trocarSenha(String p, String novaSenha) {
		config.set(p.toLowerCase(), novaSenha);
		instance.saveConfig();
	}

	public static String getIP(String p) {
		Player player = Bukkit.getPlayer(p);
		String ip = "";
		try {
			ip = player.getAddress().getAddress().getHostAddress().replaceAll("/", "");
		} catch (Exception e) {
			ip = "Localhost";
		}
		return "§b" + ip;
	}

	public static void sendThis(String p) {
		int on = Bukkit.getOnlinePlayers().size();
		Player player = Bukkit.getPlayer(p);
		for (int i = 0; i < 200; i++) {
			player.sendMessage("");
		}
		player.sendMessage("§e* Caso achar algum bug, fale com nossa equipe.");
		player.sendMessage("§e* Nunca use a mesma senha desse servidor em outros servidores.");
		player.sendMessage("§e* Há no momento §b" + on + " §ejogadores onlines no servidores.");
	}

}
