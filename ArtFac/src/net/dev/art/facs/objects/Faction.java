package net.dev.art.facs.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.dev.art.facs.Main;

public class Faction {

	private List<String> aliados, inimigos;
	private double banco;
	private Location base = null;
	private List<String> capitoes, membros, recrutas;
	private String nome, lider, tag, ID;
	private List<String> onlineMembers = new ArrayList<>();

	public Faction(String ID, List<String> aliados, List<String> inimigos, double banco, List<String> capitoes,
			List<String> membros, List<String> recrutas, String nome, String lider, String tag) {
		this.ID = ID;
		this.aliados = aliados;
		this.inimigos = inimigos;
		this.banco = banco;
		this.capitoes = capitoes;
		this.membros = membros;
		this.recrutas = recrutas;
		this.nome = nome;
		this.lider = lider;
		this.tag = tag;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void depositar(double qnt) {
		setBanco(getBanco() + qnt);
	}

	public void retirar(double qnt) {
		setBanco(getBanco() - qnt);
	}

	public List<String> getAllMembers() {
		List<String> members = new ArrayList<>();
		members.add(lider);
		if (capitoes.size() > 0)
			for (String c : capitoes) {
				members.add(c);
			}
		if (membros.size() > 0)
			for (String m : membros) {
				members.add(m);

			}
		if (recrutas.size() > 0)
			for (String r : recrutas) {
				members.add(r);

			}
		return members;
	}

	public void broadcast(String msg) {
		for (String onm : getOnlineMembers()) {
			if (Bukkit.getPlayer(onm).isOnline()) {
				Bukkit.getPlayer(onm).sendMessage(msg);
			}
		}
	}

	public List<String> getOnlineMembers() {
		return onlineMembers;
	}

	public void addOnline(String player) {
		getOnlineMembers().add(player);
	}

	public void removeOnline(String player) {
		getOnlineMembers().remove(player);
	}

	public void setAliados(List<String> aliados) {
		this.aliados = aliados;
	}

	public void setInimigos(List<String> inimigos) {
		this.inimigos = inimigos;
	}

	public void setBanco(double banco) {
		this.banco = banco;
	}

	public void setCapitoes(List<String> capitoes) {
		this.capitoes = capitoes;
	}

	public void setMembros(List<String> membros) {
		this.membros = membros;
	}

	public void setRecrutas(List<String> recrutas) {
		this.recrutas = recrutas;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLider(String lider) {
		this.lider = lider;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setBase(Location base) {
		this.base = base;
	}

	public List<String> getAliados() {
		return aliados;
	}

	public double getBanco() {
		return banco;
	}

	public Location getBase() {
		return base;
	}

	public List<String> getCapitoes() {
		return capitoes;
	}

	public List<String> getInimigos() {
		return inimigos;
	}

	public String getLider() {
		return lider;
	}

	public List<String> getMembros() {
		return membros;
	}

	public String getNome() {
		return nome;
	}

	public List<String> getRecrutas() {
		return recrutas;
	}

	public String getTag() {
		return tag;
	}

	public static List<Faction> getAll() {
		return Main.factions.values().stream().collect(Collectors.toList());
	}

}
