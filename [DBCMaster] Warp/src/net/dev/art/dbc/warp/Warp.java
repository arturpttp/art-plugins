package net.dev.art.dbc.warp;

import org.bukkit.Location;

public class Warp {

	private String name;
	private Location location;

	public Warp(String name, Location loc) {
		this.name = name;
		this.location = loc;
	}

	public Location getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}
}
