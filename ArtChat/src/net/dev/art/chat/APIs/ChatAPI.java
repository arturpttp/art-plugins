package net.dev.art.chat.APIs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.TitleAPI;
import net.dev.art.chat.Main;
import net.dev.art.grupos.api.GruposAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatAPI {

	public static void Mutar(String canal) {
		if (canal.equalsIgnoreCase("global")) {
			Main.getInstance().getConfig().set("Mutados.Global", true);
			Main.getInstance().saveConfig();
		} else if (canal.equalsIgnoreCase("local")) {
			Main.getInstance().getConfig().set("Mutados.Local", true);
			Main.getInstance().saveConfig();
		} else if (canal.equalsIgnoreCase("staff")) {
			Main.getInstance().getConfig().set("Mutados.Staff", true);
			Main.getInstance().saveConfig();
		}
	}

	public static String append(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < args.length; x++) {
			stringBuilder.append(args[x] + " ");
		}
		return stringBuilder.toString();
	}

	public static void sendHelp(Player p) {
		p.sendMessage("§e=-=-=-=-=-=-=§b§l ArtChat §e=-=-=-=-=-=-=");
		p.sendMessage("§e/chat limpar §8 » §6Limpar o chat");
		p.sendMessage("§e/chat mutar (local|staff|global) §8 » §6mutar um canal");
		p.sendMessage("§e/chat desmutar (local|staff|global) §8 » §6desmutar um canal mutado");
		p.sendMessage("§e/chat help §8 » §6Ajuda do chat");
		p.sendMessage("§e/chat info §8 » §6Informação Dos Canais");
		p.sendMessage("§e=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}

	public static void DesMutar(String canal) {
		if (canal.equalsIgnoreCase("global")) {
			Main.getInstance().getConfig().set("Mutados.Global", false);
			Main.getInstance().saveConfig();
		} else if (canal.equalsIgnoreCase("local")) {
			Main.getInstance().getConfig().set("Mutados.Local", false);
			Main.getInstance().saveConfig();
		} else if (canal.equalsIgnoreCase("staff")) {
			Main.getInstance().getConfig().set("Mutados.Staff", false);
			Main.getInstance().saveConfig();
		}
	}

	public static boolean isMutado(String canal) {
		if (canal.equalsIgnoreCase("global")) {
			return Main.getInstance().getConfig().getBoolean("Mutados.Global");
		} else if (canal.equalsIgnoreCase("local")) {
			return Main.getInstance().getConfig().getBoolean("Mutados.Local");
		} else if (canal.equalsIgnoreCase("staff")) {
			return Main.getInstance().getConfig().getBoolean("Mutados.Staff");
		}
		return false;
	}

	public static String mensagem(Player p, String mensagem) {
		if (GruposAPI.hasPermission(p, "dono")) {
			return mensagem.replace("&", "§");
		} else {
			return mensagem;
		}
	}

	public static void sendLocal(Player p, BaseComponent formato) {

		String prefix = GruposAPI.getGrupo(p.getName()).getPrefix();
		String alone = "§cSem Players Perto De Você";
		String s = "S";

		if (Bukkit.getOnlinePlayers().size() == 1) {
			p.spigot().sendMessage(formato);
			p.sendMessage(alone);
			TitleAPI.sendActionBar(alone, p);
			return;
		} else {
			p.spigot().sendMessage(formato);
		}

		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (todos.getWorld().getName().equalsIgnoreCase(p.getWorld().getName())) {
				if (todos.getLocation().distance(p.getLocation()) <= 50) {
					if (!todos.getName().equalsIgnoreCase(p.getName())) {
						todos.spigot().sendMessage(formato);
						if (s.equalsIgnoreCase("S")) {
							s = "N";
						}
					}
				}
			}
		}
		if (s.equalsIgnoreCase("S")) {
			p.sendMessage(alone);
			TitleAPI.sendActionBar(alone, p);
		}
	}

	public static void sendLocal(Player p, String formato) {

		String prefix = GruposAPI.getGrupo(p.getName()).getPrefix();
		String alone = "§cSem Players Perto De Você";
		String s = "S";

		if (Bukkit.getOnlinePlayers().size() == 1) {
			p.sendMessage(formato);
			p.sendMessage(alone);
			TitleAPI.sendActionBar(alone, p);
			return;
		} else {
			p.sendMessage(formato);
		}

		for (Player todos : Bukkit.getOnlinePlayers()) {
			if (todos.getWorld().getName().equalsIgnoreCase(p.getWorld().getName())) {
				if (todos.getLocation().distance(p.getLocation()) <= 50) {
					if (!todos.getName().equalsIgnoreCase(p.getName())) {
						todos.sendMessage(formato);
						if (s.equalsIgnoreCase("S")) {
							s = "N";
						}
					}
				}
			}
		}
		if (s.equalsIgnoreCase("S")) {
			p.sendMessage(alone);
			TitleAPI.sendActionBar(alone, p);
		}
	}

	public static void LimparChat() {
		for (int i = 0; i < 150; i++) {
			Bukkit.broadcastMessage("");
		}
	}

	public static void sendStaff(String msg) {
		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (GruposAPI.hasPermission(ps, "dono")) {
				ps.sendMessage(msg);
			}
		}
	}

	public static void sendStaff(TextComponent msg) {
		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (GruposAPI.hasPermission(ps, "dono")) {
				ps.spigot().sendMessage(msg);
			}
		}
	}

	public static void sendGlobal(String msg) {
		for (Player ps : Bukkit.getOnlinePlayers()) {
			ps.sendMessage(msg);
		}
	}

	public static void sendGlobal(TextComponent msg) {
		for (Player ps : Bukkit.getOnlinePlayers()) {
			ps.spigot().sendMessage(msg);
		}
	}

	public static void sendInfo(Player p) {
		String local = "";
		String global = "";
		String staff = "";
		if (isMutado("local")) {
			local = "§eChat Local: Status: §cMUTADO!";
		} else {
			local = "§eChat Local: Status: §aDESMUTADO!";
		}

		if (isMutado("global")) {
			global = "§eChat Global: Status: §cMUTADO!";
		} else {
			global = "§eChat Global: Status: §aDESMUTADO!";
		}

		if (isMutado("staff")) {
			staff = "§eChat Staff: Status: §cMUTADO!";
		} else {
			staff = "§eChat Staff: Status: §aDESMUTADO!";
		}

		p.sendMessage("§e=-=-=-=-=-=-=§b§l ArtChat §e=-=-=-=-=-=-=");
		p.sendMessage(local);
		p.sendMessage(global);
		p.sendMessage(staff);
		p.sendMessage("§e=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

	}

}
