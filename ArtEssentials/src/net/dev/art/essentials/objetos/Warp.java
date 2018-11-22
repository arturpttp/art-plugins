package net.dev.art.essentials.objetos;

import org.bukkit.Location;

public class Warp {

	Location location;
	String name;

	public Warp(String name, Location loc) {
		this.name = name;
		this.location = loc;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

}
