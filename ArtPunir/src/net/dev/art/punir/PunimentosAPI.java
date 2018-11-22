package net.dev.art.punir;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.CalendarioAPI;

public class PunimentosAPI {

	public static String data = CalendarioAPI.getData(System.currentTimeMillis()) + " as "
			+ CalendarioAPI.getHoras(System.currentTimeMillis());
	public static FileConfiguration config = Main.getInstance().getConfig();

	public static void Despunir(Player p, String type) {
		if (type.equalsIgnoreCase("ban")) {
			String path = "Bans." + p.getName().toLowerCase();
			config.set(path, null);
			Main.getInstance().saveConfig();
		} else if (type.equalsIgnoreCase("mute")) {
			String path = "Mutes." + p.getName().toLowerCase();
			config.set(path, null);
			Main.getInstance().saveConfig();
		}

	}
	
	public static void Despunir(String p, String type) {
		if (type.equalsIgnoreCase("ban")) {
			String path = "Bans." + p.toLowerCase();
			config.set(path, null);
			Main.getInstance().saveConfig();
		} else if (type.equalsIgnoreCase("mute")) {
			String path = "Mutes." + p.toLowerCase();
			config.set(path, null);
			Main.getInstance().saveConfig();
		}

	}

	public static void Punir(Player p, CommandSender k, Motivos m) {
		switch (m) {
		case Abuso_De_Capslock:
			MutarTemporariamente(p, k, m, 1);
			Main.getInstance().saveConfig();
			break;
		case AntiJogo:
			BanirTemporariamente(p, k, m, 24 * 2);
			Main.getInstance().saveConfig();
			break;
		case Bugs:
			BanirTemporariamente(p, k, m, 24 * 5);
			Main.getInstance().saveConfig();
			break;
		case Desinformação:
			MutarTemporariamente(p, k, m, 1);
			Main.getInstance().saveConfig();
			break;
		case Divulgação:
			MutarTemporariamente(p, k, m, 1);
			Main.getInstance().saveConfig();
			break;
		case Divulgação_Grave:
			MutarTemporariamente(p, k, m, 5);
			Main.getInstance().saveConfig();
			break;
		case Fake:
			Banir(p, k, m);
			Main.getInstance().saveConfig();
			break;
		case Falsificação_De_Provas:
			Banir(p, k, m);
			Main.getInstance().saveConfig();
			break;
		case Flood:
			MutarTemporariamente(p, k, m, 1);
			Main.getInstance().saveConfig();
			break;
		case Hack:
			Banir(p, k, m);
			Main.getInstance().saveConfig();
			break;
		case Nick_Inadequado:
			Banir(p, k, m);
			Main.getInstance().saveConfig();
			break;
		case Ofensa_Em_Geral:
			MutarTemporariamente(p, k, m, 1);
			Main.getInstance().saveConfig();
			break;
		case Ofensa_À_Staff:
			MutarTemporariamente(p, k, m, 1);
			Main.getInstance().saveConfig();
			break;
		case Roubo:
			Banir(p, k, m);
			Main.getInstance().saveConfig();
			break;
		case Roubo_De_Contas:
			Banir(p, k, m);
			Main.getInstance().saveConfig();
			break;
		case Spam:
			MutarTemporariamente(p, k, m, 1);
			Main.getInstance().saveConfig();
			break;
		default:
			break;

		}
		Main.getInstance().saveConfig();
	}

	public static int getDaysInMillis(int days) {
		return 86400000 * days;
	}

	public static int getHoursInMillis(int hours) {
		return 3600000 * hours;
	}

	public static int getDaysByMillis(int millis) {
		int day = millis / 24 / 60 / 60 / 1000;
		return day;
	}

	public static int getHoursByMillis(int millis) {
		int day = millis / 60 / 60 / 1000;
		return day;
	}

	public static boolean checkMute(Player p) {
		String path = "Mutes." + p.getName().toLowerCase();
		path += ".Milliseconds";
		long millis = config.getLong(path);
		if (isMutado(p)) {
			if (millis > System.currentTimeMillis()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean checkBan(Player p) {
		String path = "Bans." + p.getName().toLowerCase();
		long millis = config.getLong(path + ".Milliseconds");
		if (isBanido(p)) {
			if (config.contains(path + ".Milliseconds")) {
				if (millis > System.currentTimeMillis()) {
					return true;
				} else {
					return false;
				}
			} else if (config.contains(path)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public static Motivos getMuteType(Player p) {
		String path = "Bans." + p.getName().toLowerCase();
		Motivos m = null;
		if (isMutado(p)) {
			if (config.contains(path + ".Milliseconds")) {
				switch (path + ".Milliseconds") {
				case "Divulgação":
					m = Motivos.Divulgação;
					break;
				case "Flood":
					m = Motivos.Flood;
					break;
				case "Spam":
					m = Motivos.Spam;
					break;
				case "Desinformação":
					m = Motivos.Desinformação;
					break;
				case "Ofensa_Em_Geral":
					m = Motivos.Ofensa_Em_Geral;
					break;
				case "Ofensa_À_Staff":
					m = Motivos.Ofensa_À_Staff;
					break;
				case "Abuso_De_Capslock":
					m = Motivos.Abuso_De_Capslock;
					break;
				}
			} else {
				switch (path) {
				case "Divulgação":
					m = Motivos.Divulgação;
					break;
				case "Flood":
					m = Motivos.Flood;
					break;
				case "Spam":
					m = Motivos.Spam;
					break;
				case "Desinformação":
					m = Motivos.Desinformação;
					break;
				case "Ofensa_Em_Geral":
					m = Motivos.Ofensa_Em_Geral;
					break;
				case "Ofensa_À_Staff":
					m = Motivos.Ofensa_À_Staff;
					break;
				case "Abuso_De_Capslock":
					m = Motivos.Abuso_De_Capslock;
					break;
				}
			}
		}
		return m;
	}

	public static boolean isBanido(Player p) {
		return Main.getInstance().getConfig().contains("Bans." + p.getName().toLowerCase());
	}

	public static boolean isMutado(Player p) {
		return Main.getInstance().getConfig().contains("Mutes." + p.getName().toLowerCase());
	}

	public static void Kickar(Player p, CommandSender k) {
		p.kickPlayer("§cArtPunir \n \n Você Foi Avisado Na Proxima Vez Sera BANIDO \n Avisado Por: §r" + k.getName());

		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage("§c          ArtPunir - Avisos");
		Bukkit.broadcastMessage(" ");
		Bukkit.broadcastMessage("§c§l♦ §f" + p.getName() + " §cFoi Avisado e Kickado!");
		Bukkit.broadcastMessage("§c§l♦ §cAvisado Por: §f" + k.getName() + " §cNa Proxima Sera Banido!");
		Bukkit.broadcastMessage("§c§l♦ §cAvisado ás: §f" + CalendarioAPI.getData(System.currentTimeMillis()) + " as "
				+ CalendarioAPI.getHoras(System.currentTimeMillis()));
		Bukkit.broadcastMessage(" ");

	}

	public static void Banir(Player p, CommandSender k, Motivos m) {
		String mstring = m.toString().replace("_", " ");
		String path = "Bans." + p.getName().toLowerCase();
		try {
			config.set(path + ".Punidor", k.getName());
			config.set(path + ".Motivo", m.toString());
			Main.getInstance().saveConfig();
		} catch (Exception e) {
			k.sendMessage("ERRO AO SALVAR NA CONFIG");
		}
		

		p.kickPlayer("§bArtPunir\n\n§cVocê Foi Banido permanetemente" + "\n Motivo:§f " + mstring + "\n§cBanido Por:§f "
				+ k.getName() + "\n§cBanido ás: §f" + data);

		Bukkit.broadcastMessage("          §cArtPunir     ");
		Bukkit.broadcastMessage("          ");
		Bukkit.broadcastMessage("§c§l♦ §c§f" + p.getName() + " §cfoi banido permanetemente");
		Bukkit.broadcastMessage("§c§l♦ §c§cBanido por §f" + k.getName());
		Bukkit.broadcastMessage("§c§l♦ §c§cMotivo §f" + mstring);
		Bukkit.broadcastMessage("§c§l♦ §c§cBanido ás: §f" + data);
		Bukkit.broadcastMessage("    ");

	}

	public static void BanirTemporariamente(Player p, CommandSender k, Motivos m, int horas) {
		String mstring = m.toString().replace("_", " ");
		String path = "Bans." + p.getName().toLowerCase();
		int dias = (horas * 60 * 60 * 1000);
		config.set(path + ".Punidor", k.getName());
		config.set(path + ".Motivo", m.toString());
		config.set(path + ".Milliseconds", System.currentTimeMillis() + dias);
		Main.getInstance().saveConfig();

		p.kickPlayer("§bArtPunir\n \n §cVocê Foi Banido temporariamente Motivo:§f " + mstring + "\n §cBanido Por:§f"
				+ k.getName() + "\n§c Banido até:§f " + CalendarioAPI.getData(System.currentTimeMillis() + dias)
				+ "§c as §f" + CalendarioAPI.getHoras(System.currentTimeMillis() + dias));

		Bukkit.broadcastMessage("          §cArtPunir     ");
		Bukkit.broadcastMessage("          ");
		Bukkit.broadcastMessage("§c§l♦ §c§f" + p.getName() + " §cfoi banido temporariamente");
		Bukkit.broadcastMessage("§c§l♦ §c§cBanido por §f" + k.getName());
		Bukkit.broadcastMessage("§c§l♦ §c§cMotivo §f" + mstring);
		Bukkit.broadcastMessage("§c§l♦ §c§cBanido ás: §f" + data + "§c até §f"
				+ CalendarioAPI.getData(System.currentTimeMillis() + dias) + "§c as §f"
				+ CalendarioAPI.getHoras(System.currentTimeMillis() + dias));
		Bukkit.broadcastMessage("    ");

	}

	public static void Mutar(Player p, CommandSender k, Motivos m) {
		String mstring = m.toString().replace("_", " ");
		String path = "Mutes." + p.getName().toLowerCase();
		config.set(path + ".Punidor", k.getName());
		config.set(path + ".Motivo", m.toString());
		Main.getInstance().saveConfig();

		Bukkit.broadcastMessage("          §cArtPunir     ");
		Bukkit.broadcastMessage("          ");
		Bukkit.broadcastMessage("§c§l♦ §c§f" + p.getName() + " §cfoi banido permanetemente");
		Bukkit.broadcastMessage("§c§l♦ §c§cMutado por §f" + k.toString());
		Bukkit.broadcastMessage("§c§l♦ §c§cMotivo §f" + mstring);
		Bukkit.broadcastMessage("§c§l♦ §c§cMutado ás: §f" + data);
		Bukkit.broadcastMessage("    ");

	}

	public static void MutarTemporariamente(Player p, CommandSender k, Motivos m, int horas) {
		String mstring = m.toString().replace("_", " ");
		String path = "Mutes." + p.getName().toLowerCase();
		int dias = (horas * 60 * 60 * 1000);
		config.set(path + ".Punidor", k.getName());
		config.set(path + ".Motivo", m.toString());
		config.set(path + ".Milliseconds", System.currentTimeMillis() + dias);
		Main.getInstance().saveConfig();

		Bukkit.broadcastMessage("          §cArtPunir     ");
		Bukkit.broadcastMessage("          ");
		Bukkit.broadcastMessage("§c§l♦ §c§f" + p.getName() + " §cfoi mutado temporariamente");
		Bukkit.broadcastMessage("§c§l♦ §c§cMutado por §f" + k.getName());
		Bukkit.broadcastMessage("§c§l♦ §c§cMutado §f" + mstring);
		Bukkit.broadcastMessage("§c§l♦ §c§cMutado ás: §f" + data + "§c até §f"
				+ CalendarioAPI.getData(System.currentTimeMillis() + dias) + "§c as §f"
				+ CalendarioAPI.getHoras(System.currentTimeMillis() + dias));
		Bukkit.broadcastMessage("    ");

	}

}
