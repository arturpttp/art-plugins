package net.dev.art.nnt;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.dev.art.core.objects.ActionBar;

public class Events implements Listener {

	public static Map<String, Double> lifes = new HashMap<>();

	@EventHandler
	void onHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof LivingEntity) {
			if (e.getEntity() instanceof LivingEntity) {
				LivingEntity p = (LivingEntity) e.getDamager();
				LivingEntity en = (LivingEntity) e.getEntity();
				en.setHealth(20D);
				int forca = 500;
				double life = lifes.getOrDefault(en.getName(), 2500D) - forca * 2;
				lifes.put(en.getName(), life);
				if (lifes.get(en.getName()) <= 0) {
					en.setHealth(0);
					lifes.remove(en.getName());
				}
			}
		}

	}

}
