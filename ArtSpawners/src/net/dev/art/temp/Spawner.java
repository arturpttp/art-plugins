package net.dev.art.temp;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dev.art.api.APIs.ArtItem;
import net.dev.art.api.APIs.ItemsAPI;

public class Spawner {

	Location location;
	EntityType type;

	public Spawner(Location location, EntityType type) {
		super();
		this.location = location;
		this.type = type;
	}

	public ItemStack getItem(int qnt) {

		ItemStack item = ItemsAPI.getSpawner("§eGerador de monstros", qnt, type);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList("", "§eTIPO:§a " + type.toString().toUpperCase().replace("_", " "), ""));
		item.setItemMeta(meta);
		return item;
	}

	public Location getLocation() {
		return location;
	}

	public EntityType getType() {
		return type;
	}

}
