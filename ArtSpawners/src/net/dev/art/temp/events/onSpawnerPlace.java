package net.dev.art.temp.events;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import net.dev.art.temp.apis.SpawnersAPI;

public class onSpawnerPlace implements Listener {

	@EventHandler
	void onPlaceSpawner(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() != Material.MOB_SPAWNER) {
			return;
		}

		try {
			List<String> string = p.getItemInHand().getItemMeta().getLore();
			CreatureType tipo = CreatureType.valueOf(string.get(1).replace(" ", "_").replace("§eTIPO:§a ", ""));
			CreatureSpawner b = (CreatureSpawner) e.getBlockPlaced().getState();
			b.setCreatureType(tipo);
		} catch (Exception e2) {
			p.sendMessage("§cERRO AO COLOCAR SPAWNER");
			e.setCancelled(true);
		}

	}

}
