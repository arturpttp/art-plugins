package net.dev.art.drops.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AllEvents implements Listener {

	@EventHandler
	void event(InventoryClickEvent e) {
		if (e.getClickedInventory() == null || e.getCurrentItem().getType() == Material.AIR)
			return;
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if(e.getInventory().getTitle().equalsIgnoreCase("§eDrops de » §b" + p.getName())) {
				e.setCancelled(true);
			}
		}
	}

}
