package net.dev.art.utils.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PickUP implements Listener{

	@EventHandler
	void event(BlockBreakEvent e) {
		e.getBlock().getDrops().forEach(item -> e.getPlayer().getInventory().addItem(item));
		e.getBlock().setType(Material.AIR);
		e.setCancelled(true);
	}

}
