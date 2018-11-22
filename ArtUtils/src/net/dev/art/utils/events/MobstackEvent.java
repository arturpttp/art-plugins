package net.dev.art.utils.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import net.dev.art.utils.apis.MobstackAPI;

public class MobstackEvent implements Listener {

	@EventHandler
	public void nascer(EntitySpawnEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) e.getEntity();
			if (MobstackAPI.getStack(livingEntity) == 10) {
				return;
			}
			double range = 15.5D;
			for (Entity entidadePerto : livingEntity.getNearbyEntities(range, range, range)) {
				if (entidadePerto instanceof LivingEntity) {
					LivingEntity livingEntity2 = (LivingEntity) entidadePerto;

					if (livingEntity2.getType() == livingEntity.getType()) {
						e.setCancelled(true);
						MobstackAPI.addStack(livingEntity2, 1);
						update(livingEntity2);

						return;
					}

				}
			}
			MobstackAPI.addStack(livingEntity, 1);
			update(livingEntity);

		}
	}

	@EventHandler
	void damage(EntityDamageEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity en = (LivingEntity) e.getEntity();
			update(en);
		}
	}

	@EventHandler
	void matarEntidade(EntityDeathEvent e) {
		int qnt = MobstackAPI.getStack(e.getEntity());
		if (qnt == 0) {
			return;
		}
		int drops = (e.getDrops().size() * qnt);
		Player killer = e.getEntity().getKiller();
		for (ItemStack drop : e.getDrops()) {
			drop.setAmount(drops);
			try {
				killer.getInventory().addItem(drop);
			} catch (Exception e2) {
				killer.getWorld().dropItem(killer.getLocation(), drop);
			}

		}
		e.getDrops().clear();
		e.setDroppedExp(e.getDroppedExp() * qnt);

	}

	public static void update(LivingEntity entidade) {
		int stack = MobstackAPI.getStack(entidade);
		entidade.setCustomNameVisible(true);
		entidade.setCustomName(
				"§a" + entidade.getType().getName() + " §2x§f" + stack + " §c♥ §f" + (int) entidade.getHealth() / 2);

	}

}
