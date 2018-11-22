package net.dev.art.utils.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import net.dev.art.core.ArtItem;
import net.dev.art.core.ArtPlayer;
import net.dev.art.utils.Main;
import net.dev.green.grupos.APIs.GruposAPI;

public class AdminAPI {

	public static List<Player> presos = new ArrayList<>();
	public static List<Player> admins = new ArrayList<>();

	public static void sendInfos(Player p, Player t) {
		ArtPlayer artplayer = new ArtPlayer(t);
		double coins = artplayer.getCoins();
		double cash = artplayer.getCash();
		String grupo = GruposAPI.getPrefix(artplayer.getGrupo());
		String rank = artplayer.getRank().getPrefix().replace("&", "§");
		double healt = artplayer.getPlayer().getHealth();

		p.sendMessage(" ");
		p.sendMessage("       §bArtUtils §8» ADMIN §8» Informações de: " + artplayer.getPlayer().getName());
		p.sendMessage("§8» §eCoins:§b " + coins);
		p.sendMessage("§8» §eCash:§b " + cash);
		p.sendMessage("§8» §eGrupo: " + grupo);
		p.sendMessage("§8» §eRank: " + rank);
		p.sendMessage("§8» §cVida ♥: " + healt);
		p.sendMessage(" ");

	}

	public static void hide(Player p) {
		for (Player adm : admins) {
			p.hidePlayer(adm);
		}
	}

	public static void prison(Player player) {
		presos.add(player);

		Location loc = player.getLocation();
		loc = loc.add(0, 10, 0);

		player.playSound(player.getLocation(), Sound.WITHER_SPAWN, 2, 1);
		loc.clone().add(0, 0, 0).getBlock().setType(Material.BEDROCK);
		loc.clone().add(0, 3, 0).getBlock().setType(Material.BEDROCK);
		loc.clone().add(0, 1, -1).getBlock().setType(Material.BEDROCK);
		loc.clone().add(-1, 1, 0).getBlock().setType(Material.BEDROCK);
		loc.clone().add(1, 1, 0).getBlock().setType(Material.BEDROCK);
		loc.clone().add(0, 1, 1).getBlock().setType(Material.BEDROCK);
		player.teleport(loc.clone().add(-0.4, 1, -0.4));
		player.sendMessage("§cVoce foi Aprisionado!");
	}

	public static void removePrison(Player player) {
		presos.remove(player);
		Location loc = player.getLocation();
		player.sendMessage("§aVoce foi liberto da Prisão!");
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 2, 1);
		loc.clone().add(0, -1, 0).getBlock().setType(Material.AIR);
		loc.clone().add(0, 2, 0).getBlock().setType(Material.AIR);
		loc.clone().add(0, 0, 1).getBlock().setType(Material.AIR);
		loc.clone().add(1, 0, 0).getBlock().setType(Material.AIR);
		loc.clone().add(0, 0, -1).getBlock().setType(Material.AIR);
		loc.clone().add(-1, 0, 0).getBlock().setType(Material.AIR);
	}

	/*
	 * Kill Aura Auto-Soup Anti-Knockback No-Fall Troca rapida Jail Abrir inventário
	 * Crashador Info
	 */

	public static void addAdminItems(Player p) {

		ArtItem sopa = new ArtItem(Material.MUSHROOM_SOUP).nome("§eTeste De: §bAuto-Soup").Glowing(true);
		ArtItem killaura = new ArtItem(Material.GOLD_SWORD).nome("§eTeste De: §bKill Aura").Glowing(true);
		ArtItem antikb = new ArtItem(Material.STICK).encantamento(Enchantment.KNOCKBACK, 10)
				.nome("§eTeste De: §bKnockback").Glowing(true);
		ArtItem nofall = new ArtItem(Material.FEATHER).nome("§eTeste De: §bNoFall").Glowing(true);
		ArtItem troca = new ArtItem(Material.MAGMA_CREAM).nome("§eTroca Rapida").Glowing(true);
		ArtItem jail = new ArtItem(Material.IRON_FENCE).nome("§ePrender Jogador").Glowing(true);
		ArtItem abririnv = new ArtItem(Material.BOOK).nome("§eAbrir Inventario de um jogador").Glowing(true);
		ArtItem crashar = new ArtItem(Material.FIREBALL).nome("§eCrashar jogador").Glowing(true);
		ArtItem info = new ArtItem(Material.PAPER).nome("§eInformações do Jogador").Glowing(true);

		PlayerInventory inv = p.getInventory();

		inv.addItem(sopa);
		inv.addItem(killaura);
		inv.addItem(antikb);
		inv.addItem(nofall);
		inv.addItem(troca);
		inv.addItem(jail);
		inv.addItem(abririnv);
		inv.addItem(crashar);
		inv.addItem(info);

	}

	public static void admin(Player p) {
		if (admins.contains(p)) {
			admins.remove(p);
			p.getInventory().clear();
			p.sendMessage(Main.getInstance().prefix + "§cVocê saiu do modo §fADMIN§c!");
		} else {
			List<ItemStack> its = new ArrayList<>();
			for (ItemStack is : p.getInventory().getContents()) {
				its.add(is);
			}
			for (Player on : Bukkit.getOnlinePlayers()) {
				if (on.isOp() == false) {
					on.hidePlayer(p);
					p.setPlayerListName(null);
				}
			}
			p.getInventory().clear();
			p.getInventory().clear();
			admins.add(p);
			addAdminItems(p);
			p.sendMessage(Main.getInstance().prefix + "§aVocê entrou no modo §fADMIN§c!");
		}
	}
}
