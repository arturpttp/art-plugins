package net.dev.art.core.objects;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class FloatItem {

	String name;
	Location location;
	boolean visible = true;
	ItemStack item;
	private ArmorStand stand;

	public FloatItem(ItemStack item, Location location, Boolean bolean) {
		String nm = "";
		if (item.getItemMeta().hasDisplayName()) {
			nm = item.getItemMeta().getDisplayName();
		} else {
			nm = item.getType().toString().toLowerCase().replace("_", " ");
		}
		this.name = nm;
		this.item = item;
		this.visible = bolean;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

	public boolean isVisible() {
		return visible;
	}

	public ItemStack getItem() {
		return item;
	}

	public ArmorStand getEntity() {
		return stand;
	}
	
	public void spawn() {

		ArmorStand s = (ArmorStand) getLocation().getWorld().spawn(getLocation(), ArmorStand.class);
		s.setVisible(isVisible());
		s.setCustomNameVisible(true);
		s.setItemInHand(getItem());
		s.setCustomName(getName());
		s.getLocation().setDirection(getLocation().getDirection());
		this.stand=s;

	}

}
