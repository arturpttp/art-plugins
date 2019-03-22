package net.dev.art.utilidades.events;

import java.util.Collection;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.utilidades.Main;

public class AutoPickUP implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void aoQuebrar(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!Main.getInstance().getConfig().getBoolean("Auto-PickUP-Enable")) {
			return;
		}
		if (p.getItemInHand() == null)
			return;
		if (p.getItemInHand().getType() == Material.AIR)
			return;

		if (p.getInventory().firstEmpty()==-1) {

			p.sendMessage("�cInventario cheio!");

			Collection<ItemStack> lista = e.getBlock().getDrops(p.getItemInHand());

			for (ItemStack item : lista) {
				e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), item);
			}

		} else {

			Collection<ItemStack> lista = e.getBlock().getDrops(p.getItemInHand());
			for (ItemStack item : lista) {
				p.getInventory().addItem(item);
			}

			new BukkitRunnable() {

				@Override
				public void run() {
					Collection<Entity> drops = e.getBlock().getLocation().getWorld()
							.getNearbyEntities(e.getBlock().getLocation(), 1, 1, 1);
					for (Entity entidade : drops) {
						if (entidade instanceof Item) {
							Item drop = (Item) entidade;
							drop.setPickupDelay(300);
							drop.remove();

						}
					}

				}
			}.runTaskLaterAsynchronously(Main.getInstance(), 1);
		}
	}
	
}
