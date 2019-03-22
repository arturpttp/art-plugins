package net.dev.art.utilidades.objetos;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import net.dev.art.core.objects.ArtItem;
import net.dev.art.grupos.api.GruposAPI;

public class Warp {

	private String name;
	private ItemStack icon;
	private Location location;

	public Warp(String name, ItemStack icon, Location location) {
		this.name = name;
		this.icon = icon;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Warp setIcone(Material m, String name, String lore) {
		setIcon(new ArtItem(m).nome(name).lore(lore));
		return this;
	}

	public Warp teleport(LivingEntity p) {
		p.teleport(getLocation());
		p.getWorld().playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 1F);
		p.sendMessage("§aTeleportado para warp " + getName());
		return this;
	}

}
