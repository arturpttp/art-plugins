package net.dev.art.core.objects;

import java.io.Serializable;

import org.bukkit.entity.Player;

import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.rank.Rank;
import net.dev.art.rank.RanksAPI;
import net.dev.green.grupos.APIs.GruposAPI;
import net.dev.green.grupos.APIs.GruposAPI.GruposTipos;

public class ArtPlayer implements Serializable {

	/*
	 * ArtPlayer serializavel
	 */

	private static final long serialVersionUID = 7796137796224029816L;

	private Player player;
	private double coins;
	private double cash;
	private Rank rank;
	private GruposTipos grupo;

	public ArtPlayer(Player player) {
		this.player = player;
		this.coins = CoinsAPI.getCoins(getPlayer());
		this.cash = CashAPI.getCash(getPlayer());
		this.rank = RanksAPI.getCurrentRank(getPlayer());
		this.grupo = GruposAPI.getGrupo(player);
	}

	public Player getPlayer() {
		return player;
	}

	public double getCoins() {
		return this.coins;
	}

	public double getCash() {
		return cash;
	}

	public Rank getRank() {
		return rank;
	}

	public GruposTipos getGrupo() {
		return grupo;
	}

	protected class Coin {
		private Player p;

		public Coin(Player p) {
			p = getPlayer();
		}

		public void set(double qnt) {
			CoinsAPI.setCoins(p, qnt);
		}

		public void remove(double qnt) {
			CoinsAPI.removeCoins(p, qnt);
		}

		public void add(double qnt) {
			CoinsAPI.addCoins(p, qnt);
		}

		public void pay(ArtPlayer target, double qnt) {
			CoinsAPI.payCoins(p, target.getPlayer(), qnt);
		}

	}

	public Coin coin() {
		return new Coin(player);
	}

	protected class Cash {
		private Player p;

		public Cash(Player p) {
			p = getPlayer();
		}

		public void set(double qnt) {
			CashAPI.setCash(p, qnt);
		}

		public void remove(double qnt) {
			CashAPI.removeCash(p, qnt);
		}

		public void add(double qnt) {
			CashAPI.addCash(p, qnt);
		}

		public void pay(ArtPlayer target, double qnt) {
			CashAPI.payCash(p, target.getPlayer(), qnt);
		}

	}

	public Cash cash() {
		return new Cash(player);
	}

}
