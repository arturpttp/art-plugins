package net.dev.art.essentials.objetos;

import org.bukkit.Location;

import net.dev.art.essentials.Main;

public class Casa {

	String proprietario, nome;
	Location location;
	boolean publica;
	long criação;

	public Casa(String proprietario, String nome, Location location) {
		this.location = location;
		this.publica = false;
		this.proprietario = proprietario;
		this.nome = nome;
		this.criação = System.currentTimeMillis();
	}

	public long getCriação() {
		return criação;
	}

	public String getNome() {
		return nome;
	}

	public String getProprietario() {
		return proprietario;
	}

	public Location getLocation() {
		return location;
	}

	public boolean isPublica() {
		return publica;
	}

	public void setPublica(boolean publica) {
		this.publica = publica;
	}

	public void delete() {
		Main.casas.get(proprietario).remove(this);
	}

}
