package net.dev.art.rank;

public class Rank {

	String name;
	int position;
	double price;
	String prefix;

	public Rank(String name, int position, double price, String prefix) {
		this.name = name;
		this.position = position;
		this.price = price;
		this.prefix = prefix;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getPosition() {
		return position;
	}

	public String getPrefix() {
		return prefix;
	}

}
