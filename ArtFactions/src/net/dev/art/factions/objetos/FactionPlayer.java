package net.dev.art.factions.objetos;

import net.dev.art.factions.Main;

public class FactionPlayer {

	private String name, faction;
	private int kills, deaths, poder, podermax;
	private long online;

	public boolean hasFaction() {
		return Main.getInstance().factions.get(this) != null;
	}

}
