package net.dev.art.profile.apis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.FormatarAPI;
import net.dev.art.api.APIs.ItemsAPI;
//import net.dev.art.aut.AutAPI;
import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.green.grupos.APIs.GruposAPI;
import net.dev.green.grupos.APIs.GruposAPI.GruposTipos;

public class ProfileAPI {

	public static void AbrirPerfil(Player p, String target) {
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "§ePerfil");

		inv.setItem(4, getHead(target));
		inv.setItem(11, getEcoItem(target));
		inv.setItem(15, getStatisticsItem(target));
		inv.setItem(22, getContactItem());

		p.openInventory(inv);
	}

	public static ItemStack getContactItem() {
		return ItemsAPI.add(Material.NAME_TAG, "§eContatos", "§7Entre Em Contato Com o Desenvolvedor",
				"§9Discord: arturgamer#1700", "§bTwitter: @zArturDev_");
	}

	public static String getClan(String p) {
		return "§8Nenhum";
	}

	public static String getKills(String p) {
		Player t = Bukkit.getPlayer(p);
		if (t == null)
			return "§cManutenção...";
		return t.getStatistic(Statistic.PLAYER_KILLS) + "";
	}

	public static String getDeaths(String p) {
		Player t = Bukkit.getPlayer(p);
		if (t == null)
			return "§cManutenção...";
		return t.getStatistic(Statistic.DEATHS) + "";
	}

	public static String getCoins(String p) {
		return FormatarAPI.doubleFormatado(CoinsAPI.getCoins(p));
	}

	public static String getCash(String p) {
		return FormatarAPI.doubleFormatado(CashAPI.getCash(p));
	}

	public static ItemStack getEcoItem(String p) {
		String coins = getCoins(p);
		String cash = getCash(p);
		ItemStack eco = ItemsAPI.add(Material.GOLD_INGOT, "§eEconomia De: §b" + p, "§eCoins: §2$" + coins,
				"§eCash: §2$" + cash);
		return eco;
	}

//	public static String getSenha(String p) {
//		String senha = "§cManutenção...";
//		if (!AutAPI.isRegistrado(p)) {
//			return senha;
//		} else {
//			return senha = AutAPI.getSenha(p);
//		}
//	}

	public static ItemStack getStatisticsItem(String p) {
		String clan = getClan(p);
		String kills = getKills(p);
		String deaths = getDeaths(p);
//		String senha = getSenha(p);
//		String ip = AutAPI.getIP(p);
		ItemStack i = ItemsAPI.add(Material.PAPER, "§eStatisticas De: §b" + p, "§eClan: " + clan, "§eKills:§b " + kills,
				"§eDeaths: §b" + deaths, "§eSenha: §b" + "§cManutenção...", "§eIP: §b" + "§cManutenção....");
		return i;
	}

	public static String getGrupo(String p) {
		if (GruposAPI.getGrupo(p) == null) {
			return GruposAPI.getPrefix(GruposAPI.getGrupoByName(GruposAPI.getGrupoName(GruposTipos.Membro)));
		} else {
			return GruposAPI.getPrefix(GruposAPI.getGrupo(p));
		}
	}

	public static ItemStack getHead(String p) {
		Player player = Bukkit.getPlayer(p);
		String grupo = getGrupo(p);
		String status = "§aOnline";
		if (player == null) {
			status = "§cOffline";
		}
		ItemStack head = ItemsAPI.head("§ePerfil De: §b" + p, p, "§eGrupo: " + grupo, "§eStatus: " + status);
		return head;
	}

}
