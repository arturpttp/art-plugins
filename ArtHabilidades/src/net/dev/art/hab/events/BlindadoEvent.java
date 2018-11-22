package net.dev.art.hab.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlindadoEvent implements Listener {

	public int getLevel(ItemStack item, Enchantment enc) {
		if (item.getEnchantments().containsKey(enc)) {
			return item.getEnchantmentLevel(enc);
		}
		return 0;
	}

	@SuppressWarnings("all")
	@EventHandler
	void onDeath(PlayerDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player k = (Player) e.getEntity().getKiller();
			Player p = e.getEntity();
			ItemStack capacete = k.getInventory().getHelmet();
			ItemStack peitoral = k.getInventory().getChestplate();
			ItemStack calça = k.getInventory().getLeggings();
			ItemStack botas = k.getInventory().getBoots();
			ItemMeta cm = capacete.getItemMeta();
			ItemMeta pm = peitoral.getItemMeta();
			ItemMeta clm = calça.getItemMeta();
			ItemMeta bm = botas.getItemMeta();

			if (capacete != null || capacete.getType() != Material.AIR) {
				cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						getLevel(capacete, Enchantment.PROTECTION_ENVIRONMENTAL) + 1, false);
				capacete.setItemMeta(cm);
				k.getInventory().setHelmet(capacete);
			}

			if (peitoral != null || peitoral.getType() != Material.AIR) {
				pm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						getLevel(peitoral, Enchantment.PROTECTION_ENVIRONMENTAL) + 1, false);
				peitoral.setItemMeta(pm);
				k.getInventory().setChestplate(peitoral);
			}

			if (calça != null || calça.getType() != Material.AIR) {
				clm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						getLevel(calça, Enchantment.PROTECTION_ENVIRONMENTAL) + 1, false);
				calça.setItemMeta(clm);
				k.getInventory().setLeggings(calça);
			}

			if (botas != null || botas.getType() != Material.AIR) {
				bm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,
						getLevel(botas, Enchantment.PROTECTION_ENVIRONMENTAL) + 1, false);
				botas.setItemMeta(bm);
				k.getInventory().setBoots(botas);
			}
			p.updateInventory();
		}
	}

}
