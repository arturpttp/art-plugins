package net.dev.art.eco.apis;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.FormatarAPI;
import net.dev.art.eco.Main;
import net.dev.art.eco.utils.Mensagens;
import net.dev.art.grupos.api.GruposAPI;

public class CoinsAPI extends Mensagens {

	public static YamlConfiguration config = Main.getCoins();

	public static double getCoins(Player p) {
		if (config.getDouble(p.getName().toLowerCase() + "") < 0) {
			return 0.0;
		}
		return config.getDouble(p.getName().toLowerCase() + "");
	}
	
	public static double getCoins(String p) {
		if (config.getDouble(p.toLowerCase() + "") < 0) {
			return 0.0;
		}
		return config.getDouble(p.toLowerCase() + "");
	}

	public static String getCoinsFormatado(Player p) {
		return FormatarAPI.doubleFormatado(getCoins(p));
	}

	public static void setCoins(Player p, Double quantia) {
		config.set(p.getName().toLowerCase() + "", quantia);
		Main.getInstance().saveCoins();
	}

	public static void addCoins(Player p, Double quantia) {
		setCoins(p, getCoins(p) + quantia);
		Main.getInstance().saveCoins();
	}

	public static void removeCoins(Player p, Double quantia) {
		setCoins(p, getCoins(p) - quantia);
		Main.getInstance().saveCoins();
	}

	public static void sendHelp(Player p) {
		if (GruposAPI.hasPermission(p, "dono")) {
			p.sendMessage("§e=-=-=-=-=-=-=§b§l ArtEconomia - Coins §e=-=-=-=-=-=-=");
			p.sendMessage("§e/coins (jogador) §8 » §6Ver os coins do jogador");
			p.sendMessage("§e/coins set (jogador) (quantia) §8 » §6setar os coins do jogador");
			p.sendMessage("§e/coins add (jogador) (quantia) §8 » §6adicionar os coins do jogador");
			p.sendMessage("§e/coins remove (jogador) (quantia) §8 » §6remover os coins do jogador");
			p.sendMessage("§e/coins pay (jogador) (quantia) §8 » §6pagar para um player");
			p.sendMessage("§e/coins help §8 » §6ajuda dos comandos");
			p.sendMessage("§e=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		} else {
			p.sendMessage("§e=-=-=-=-=-=-=§b§l ArtEconomia - Coins §e=-=-=-=-=-=-=");
			p.sendMessage("§e/coins (jogador) §8 » §6Ver os coins do jogador");
			p.sendMessage("§e=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		}

	}

	public static void payCoins(Player p, Player t, Double quantia) {
		if (getCoins(p) < quantia) {
			mensagem("§cVocê Nao Tem Coins Suficiente", p);
			return;
		}
		removeCoins(p, quantia);
		addCoins(t, quantia);
		Main.getInstance().saveCoins();
	}

}
