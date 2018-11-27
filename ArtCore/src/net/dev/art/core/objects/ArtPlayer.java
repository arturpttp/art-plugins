package net.dev.art.core.objects;

import org.bukkit.entity.Player;

import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.grupos.objetos.Grupo;
import net.dev.art.rank.Rank;
import net.dev.art.rank.RanksAPI;

public class ArtPlayer {

	private Player player;
	private double coins;
	private double cash;
	private Rank rank;
	private Grupo grupo;

	public ArtPlayer(Player player) {
		this.player = player;
		this.coins = CoinsAPI.getCoins(getPlayer());
		this.cash = CashAPI.getCash(getPlayer());
		this.rank = RanksAPI.getCurrentRank(getPlayer());
		this.grupo = GruposAPI.getGrupo(player.getName());
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

	public Grupo getGrupo() {
		return grupo;
	}

	public class Coin {
		private Player p;

		public Coin(Player p) {
			p = getPlayer();
		}

		public void set(double qnt) {
			CoinsAPI.setCoins(p, qnt);
		}

		public void remove(double qnt) {
			qnt = (getCoins() - qnt);
			CoinsAPI.setCoins(p, qnt);
		}

		public void add(double qnt) {
			qnt = (getCoins() + qnt);
			CoinsAPI.setCoins(p, qnt);
		}

		public void pay(ArtPlayer target, double qnt) {
			CoinsAPI.payCoins(p, target.getPlayer(), qnt);
		}

	}

	public Coin coin() {
		return new Coin(player);
	}

	public class Cash {
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
