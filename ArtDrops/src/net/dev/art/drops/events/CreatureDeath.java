package net.dev.art.drops.events;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import net.dev.art.drops.Main;
import net.dev.art.drops.apis.DropsAPI;

public class CreatureDeath implements Listener {

	@EventHandler
	void onDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Creature) {
			Creature creature = (Creature) e.getEntity();
			if (creature.getKiller() instanceof Player) {
				Player p = (Player) creature.getKiller();
				if (Main.getManager().stackaveis.contains(creature.getType())) {
					Integer intger = CreatureSpawn.entStacks.get(creature);
					int rd = new Random().nextInt(intger);
					int dropQNT = (intger + rd / 2)
							* (p.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) + 1);
					for (ItemStack drops : e.getDrops()) {
						Material m = drops.getType();
						if (Main.getInstance().getConfig().contains(p.getName().toLowerCase() + "." + m.toString())) {
							DropsAPI.addDrops(p, m, (double) dropQNT);
						} else {
							DropsAPI.setDrops(p, m, (double) dropQNT);
						}
					}
//					e.getDrops().clear();
				}
			}
		}
	}

}
