package net.dev.art.essentials.objetos;

import net.dev.art.essentials.apis.DuvidasAPI;

public class Duvida {

	String player;
	String duvida;
	String ID;
	
	public Duvida(String player, String duvida, String ID) {
		this.player = player;
		this.duvida = duvida;
		this.ID = ID;
		DuvidasAPI.duvidas.add(this);
	}
	
	public String getID() {
		return ID;
	}
	
	public String getDuvida() {
		return duvida;
	}
	
	public String getPlayer() {
		return player;
	}
	
}
