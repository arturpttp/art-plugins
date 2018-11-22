package net.dev.rpg.Objetos;

import org.bukkit.Location;

public class Warp {

	private Location location;
	private String name;
	
	public Warp(Location location, String name) {
		this.location = location;
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
	
}
