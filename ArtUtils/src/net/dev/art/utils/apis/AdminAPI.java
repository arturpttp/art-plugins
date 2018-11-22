package net.dev.art.utils.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.ArtItem;
import net.dev.art.core.ArtPlayer;
import net.dev.art.utils.Main;
import net.dev.green.grupos.APIs.GruposAPI;

public class AdminAPI {

	public static List<Player> presos = new ArrayList<>();
	public static List<Player> admins = new ArrayList<>();

	public static void AutoSoup(Player p, Player t) {
		t.setHealth(0.5);
		t.getInventory().clear();
		p.openInventory((Inventory) t.getInventory());
		final ItemStack sopa = new ArtItem(Material.MUSHROOM_SOUP);
		t.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 2));
		new BukkitRunnable() {
			int i = 10;

			public void run() {
				if (this.i == 9) {
					t.getInventory().setItem(9, sopa);
				}
				if (this.i == 8) {
					t.getInventory().setItem(10, sopa);
				}
				if (this.i == 7) {
					t.getInventory().setItem(11, sopa);
				}
				if (this.i == 6) {
					t.getInventory().setItem(12, sopa);
				}
				if (this.i == 5) {
					t.getInventory().setItem(13, sopa);
				}
				if (this.i == 4) {
					t.getInventory().setItem(14, sopa);
				}
				if (this.i == 3) {
					t.getInventory().setItem(15, sopa);
				}
				if (this.i == 2) {
					t.getInventory().setItem(16, sopa);
				}
				if (this.i == 1) {
					t.getInventory().setItem(17, sopa);
				}
				if (this.i == 0) {
					t.getInventory().clear();
					final ItemStack testItem = t.getInventory().getItem(31);
					if (testItem.getType() == Material.BOWL) {
						final int amount = t.getInventory().getItem(31).getAmount();
						final String tn = t.getName();
						p.sendMessage("§c" + tn + " puxou §6" + amount + " §csopas !");
					}
					this.cancel();
				}
				--this.i;
			}
		}.runTaskTimerAsynchronously(Main.getInstance(), 0L, 20L);
	}

	public static void sendInfos(Player p, Player t) {
		ArtPlayer artplayer = new ArtPlayer(t);
		double coins = artplayer.getCoins();
		double cash = artplayer.getCash();
		String grupo = GruposAPI.getPrefix(artplayer.getGrupo());
		String rank = artplayer.getRank().getPrefix().replace("&", "§");
		double healt = artplayer.getPlayer().getHealth();

		p.sendMessage(" ");
		p.sendMessage("       §b§eInformações de: §b" + artplayer.getPlayer().getName());
		p.sendMessage("§8» §eCoins:§b " + coins);
		p.sendMessage("§8» §eCash:§b " + cash);
		p.sendMessage("§8» §eGrupo: " + grupo);
		p.sendMessage("§8» §eRank: " + rank);
		p.sendMessage("§8» §cVida: " + (int) healt + "♥");
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
		ArtItem crashar = new ArtItem(Material.FIREBALL).nome("§eCrashar jogador").Glowing(true);
		ArtItem info = new ArtItem(Material.PAPER).nome("§eInformações do Jogador").Glowing(true);

		PlayerInventory inv = p.getInventory();

		inv.setItem(0, sopa);
		inv.setItem(1, killaura);
		inv.setItem(2, antikb);
		inv.setItem(3, nofall);

		inv.setItem(5, troca);
		inv.setItem(6, jail);
		inv.setItem(7, crashar);
		inv.setItem(8, info);

	}

	public static void admin(Player p) {
		if (admins.contains(p)) {
			admins.remove(p);
			p.getInventory().clear();
			p.setGameMode(GameMode.SURVIVAL);
			p.setAllowFlight(false);
			p.setFlying(false);
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
			p.setGameMode(GameMode.CREATIVE);
			p.setAllowFlight(true);
			p.setFlying(true);
			admins.add(p);
			addAdminItems(p);
			p.sendMessage(Main.getInstance().prefix + "§aVocê entrou no modo §fADMIN§c!");
		}
	}
}
