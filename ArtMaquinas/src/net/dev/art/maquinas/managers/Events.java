package net.dev.art.maquinas.managers;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.api.APIs.ItemsAPI;
import net.dev.art.maquinas.Main;
import net.dev.art.maquinas.apis.MaquinasAPI;

public class Events implements Listener {

	@EventHandler
	void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
	}

	@EventHandler
	void onMachinePut(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlockPlaced();
		if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§eMaquina TIPO: §bDIAMANTE")) {
			MaquinasAPI.maquinasplayers.put(p, b.getLocation());
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	void onMachineInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block b = p.getTargetBlock((Set<Material>) null, 5);
		if (MaquinasAPI.maquinasplayers.containsKey(b.getLocation())) {
			if (MaquinasAPI.maquinasplayers.get(p) == b.getLocation()) {
				if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§4Petroleo")) {
					if (!MaquinasAPI.maquinasativas.get(b.getLocation()) == true) {
						MaquinasAPI.startmachine(b.getLocation(), b, p);
					} else {
						p.sendMessage("§aEssa maquina Já Está Ativa!");
					}
				} else {
					Inventory inv = Bukkit.createInventory(null, 9 * 4, "§eConfigurações Da Maquina");

					// 21-22-23

					ItemStack nivel1 = ItemsAPI.add(Material.GOLD_INGOT, "§aUpar para o nivl 2", "");
					ItemStack nivel2 = ItemsAPI.add(Material.DIAMOND, "§aUpar para o nivl 3", "");
					ItemStack nivel3 = ItemsAPI.add(Material.EMERALD, "§aUpar para o nivl 4", "");

					inv.setItem(12, nivel1);
					inv.setItem(13, nivel2);
					inv.setItem(14, nivel3);

					inv.setItem(21, ItemsAPI.corante("", DyeColor.LIME, ""));
					inv.setItem(22, ItemsAPI.corante("", DyeColor.GRAY, ""));
					inv.setItem(23, ItemsAPI.corante("", DyeColor.GRAY, ""));

					p.openInventory(inv);
				}

			} else {
				p.sendMessage("§cEssa Maquina Não é Sua!");
			}
		}else {
			MaquinasAPI.maquinasplayers.put(p, b.getLocation());
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	void quebrar(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (MaquinasAPI.maquinasplayers.containsKey(b.getLocation())) {
			if (MaquinasAPI.maquinasplayers.get(p) == b.getLocation()) {
				if (MaquinasAPI.maquinasativas.get(b.getLocation()) == true) {
					p.sendMessage("§cEssa Maquina está ativa!");
				} else {
					if (p.getItemInHand().getItemMeta().getDisplayName().startsWith("PICKAXE")
							&& p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
						b.setType(Material.AIR);
						p.getInventory().addItem(ItemsAPI.add(Material.DIAMOND_BLOCK, "§eMaquina TIPO: §bDIAMANTE"));
					} else {
						p.sendMessage("§cVocê Prescisa Usar Uma Ppicareta Com Silk Touch!");
					}
				}
			} else {
				p.sendMessage("§cEssa Maquina Não é Sua!");
			}
		}
	}

}
