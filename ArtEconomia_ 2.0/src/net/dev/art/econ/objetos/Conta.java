package net.dev.art.econ.objetos;

public class Conta {

	private String name;
	private double coin = 0.0, cash = 0.0, alma = 0.0;

	public String getName() {
		return name;
	}

	public Conta(String name, double coin, double cash, double alma) {
		this.name = name;
		this.coin = coin;
		this.cash = cash;
		this.alma = alma;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCoin() {
		return coin;
	}

	public void setCoin(double coin) {
		this.coin = coin;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getAlma() {
		return alma;
	}

	public void setAlma(double alma) {
		this.alma = alma;
	}

}
