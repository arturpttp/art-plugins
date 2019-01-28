package net.dev.art.drops.manager;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.EntityType;

public class DropsManager {

	public static class Settings {
		public static double range = 20.0;
		public static String displayName = "§e$ent: §b$qntx §4♥$vida";
	}

	public Settings settings() {
		return new Settings();
	}

	public List<EntityType> stackaveis = Arrays.asList(EntityType.BLAZE, EntityType.IRON_GOLEM, EntityType.CREEPER,
			EntityType.COW, EntityType.CHICKEN, EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.SKELETON,
			EntityType.ENDERMAN, EntityType.ZOMBIE, EntityType.SQUID, EntityType.SLIME, EntityType.PIG_ZOMBIE,
			EntityType.PIG,EntityType.SHEEP);

}
