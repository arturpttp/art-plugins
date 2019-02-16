package net.dev.art.kits.manager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import net.dev.art.kits.Main;
import net.dev.art.kits.objetos.Kit;

public class KitsManager implements Listener {

	@EventHandler
	void event(AsyncPlayerChatEvent e) {
		if (e.getMessage().startsWith("openInv")) {
			Inventory inv = Bukkit.createInventory(null, 9 * 6, "§a§lKITS");

			for (Kit k : Main.getInstance().kits) {
				inv.addItem(k.getIcon());
			}

			e.getPlayer().openInventory(inv);

		}
	}

	@EventHandler
	void event(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§a§lKITS")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
				return;
			e.setCancelled(true);

			for (Kit k : Main.getInstance().kits) {
				if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase(k.getIcon().getItemMeta().getDisplayName())) {
					k.darKit(p);
				}
			}

		}
	}

}
