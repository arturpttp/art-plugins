package net.dev.art.hab.utils;

public class Habilidade {

	String name;
	double price;

	public Habilidade(String name, double price) {
		setName(name);
		setPrice(price);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

}
