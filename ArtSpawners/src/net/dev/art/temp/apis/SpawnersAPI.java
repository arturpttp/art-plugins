package net.dev.art.temp.apis;

import org.bukkit.entity.EntityType;

public class SpawnersAPI {

	public static EntityType getTypeByNames(String name) {
		return EntityType.valueOf(name.replace(" ", "_"));
	}

}
