package net.dev.art.facs.objects;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.dev.art.facs.Main;
import net.dev.art.facs.enums.Cargo;

public class FactionPlayer {

	private String name, faction, ID;
	private int kills, deaths, power, maxPower;
	private long online = 0L;

	public FactionPlayer(String ID, String name, String faction, int kills, int deaths, int power, int maxPower) {
		this.faction = faction;
		this.kills = kills;
		this.deaths = deaths;
		this.power = power;
		this.maxPower = maxPower;
		this.name = name;
		this.ID = ID;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public static List<FactionPlayer> getAll() {
		return Main.players.values().stream().collect(Collectors.toList());
	}

	public long getOnline() {
		return online;
	}

	public void setOnline(long online) {
		this.online = online;
	}

	public FactionPlayer setLast() {
		setOnline(System.currentTimeMillis());
		return this;
	}

	public Cargo getCargo() {
		if (!hasFaction())
			return Cargo.Nenhum;
		if (getFac().getLider().toLowerCase().equalsIgnoreCase(getName().toLowerCase()))
			return Cargo.Lider;
		if (getFac().getRecrutas().contains(getName()))
			return Cargo.Recruta;
		if (getFac().getMembros().contains(getName()))
			return Cargo.Membro;
		if (getFac().getCapitoes().contains(getName()))
			return Cargo.Capitão;
		return Cargo.Nenhum;
	}

	public int getDeaths() {
		return deaths;
	}

	public String getFaction() {
		return faction;
	}

	public int getKills() {
		return kills;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	public int getMaxPower() {
		return maxPower;
	}

	public String getName() {
		return name;
	}

	public int getPower() {
		return power;
	}

	public boolean hasFaction() {
		if (getFac() != null) {
			return true;
		}
		return false;
	}

	public Faction getFac() {
		return Main.factions.get(faction);
	}

	public Player getPlayer() {
		if (Bukkit.getPlayer(name).isOnline())
			return Bukkit.getPlayer(name);
		else
			return null;
	}

}
