package net.dev.art.eco.apis;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.FormatarAPI;
import net.dev.art.core.utils.ArtLib;
import net.dev.art.eco.Main;
import net.dev.art.grupos.api.GruposAPI;

public class CashAPI implements ArtLib {

	public static YamlConfiguration config = Main.getCash();

	public static double getCash(Player p) {
		if (config.getDouble(p.getName().toLowerCase() + "") < 0) {
			return 0.0;
		}
		return config.getDouble(p.getName().toLowerCase() + "");
	}

	public static double getCash(String p) {
		if (config.getDouble(p.toLowerCase() + "") < 0) {
			return 0.0;
		}
		return config.getDouble(p.toLowerCase() + "");
	}

	public static String getCashFormatado(Player p) {
		return FormatarAPI.doubleFormatado(getCash(p));
	}

	public static void setCash(Player p, Double quantia) {
		config.set(p.getName().toLowerCase() + "", quantia);
		Main.getInstance().saveCash();
	}

	public static void addCash(Player p, Double quantia) {
		setCash(p, getCash(p) + quantia);
		Main.getInstance().saveCash();
	}

	public static void removeCash(Player p, Double quantia) {
		setCash(p, getCash(p) - quantia);
		Main.getInstance().saveCash();
	}

	public static void sendHelp(Player p) {
		if (GruposAPI.hasPermission(p, "dono")) {
			p.sendMessage("§e=-=-=-=-=-=-=§b§l ArtEconomia - Cash §e=-=-=-=-=-=-=");
			p.sendMessage("§e/cash (jogador) §8 » §6Ver os Cash do jogador");
			p.sendMessage("§e/cash set (jogador) (quantia) §8 » §6setar os Cash do jogador");
			p.sendMessage("§e/cash add (jogador) (quantia) §8 » §6adicionar os Cash do jogador");
			p.sendMessage("§e/cash remove (jogador) (quantia) §8 » §6remover os Cash do jogador");
			p.sendMessage("§e/cash pay (jogador) (quantia) §8 » §6pagar para um player");
			p.sendMessage("§e/cash help §8 » §6ajuda dos comandos");
			p.sendMessage("§e=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		} else {
			p.sendMessage("§e=-=-=-=-=-=-=§b§l ArtEconomia - Cash §e=-=-=-=-=-=-=");
			p.sendMessage("§e/cash (jogador) §8 » §6Ver os Cash do jogador");
			p.sendMessage("§e=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		}

	}

	public static void payCash(Player p, Player t, Double quantia) {
		if (getCash(p) < quantia) {
			p.sendMessage("§cVocê Nao Tem Cash Suficiente");
			return;
		}
		removeCash(p, quantia);
		addCash(t, quantia);
		Main.getInstance().saveCash();
	}

}
