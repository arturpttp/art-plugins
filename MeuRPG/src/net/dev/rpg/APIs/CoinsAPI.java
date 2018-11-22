package net.dev.rpg.APIs;

import net.dev.rpg.Main;

public class CoinsAPI {

	public static void setCoins(String p, double qnt) {
		Main.c.set(p.toLowerCase(), qnt);
		Main.getInstance().saveCoinsConfig();
	}

	public static double getCoins(String p) {
		return Main.c.getDouble(p.toLowerCase());
	}

	public static void addCoins(String p, double qnt) {
		setCoins(p, getCoins(p) + qnt);
	}

	public static void removeCoins(String p, double qnt) {
		setCoins(p, getCoins(p) - qnt);
	}

}
