package net.dev.art.drops.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import net.dev.art.drops.Main;
import net.dev.art.drops.manager.DropsManager.Settings;

public class CreatureSpawn implements Listener {

	public static HashMap<Creature, Integer> entStacks = new HashMap<>();

	@EventHandler
	public void onSpawn(CreatureSpawnEvent e) {
//		if (e.getSpawnReason() == SpawnReason.CUSTOM)
//			return;
		if (e.getEntity() instanceof Creature) {
			Creature creature = (Creature) e.getEntity();
			if (Main.getManager().stackaveis.contains(creature.getType())) {
				Main.getManager().settings();
				double range = Settings.range;
				List<Entity> lista = creature.getNearbyEntities(range, range, range);
				List<Creature> mobs = new ArrayList<>();
				for (Entity ent : lista) {
					if (ent instanceof Creature) {
						Creature mob = (Creature) ent;
						mobs.add(mob);
					}
				}
				int stack = 1;

				for (Creature mob : mobs) {
					if (mob.getType() == creature.getType()) {
						if (!mob.equals(creature)) {
							if (entStacks.containsKey(mob)) {
								stack += entStacks.get(mob);
								entStacks.remove(mob);
								mob.remove();
							} else {
								stack++;
							}
						}
					}
				}
				
				Settings settings = Main.getManager().settings();
				String name = Settings.displayName
						.replace("$ent", "" + creature.getType().toString().replaceAll("_", " "))
						.replace("$qnt", "" + stack).replace("$vida", "" + (int) creature.getHealth());
				creature.setCustomName(name);
				entStacks.put(creature, stack);
			}

		}
	}

}
