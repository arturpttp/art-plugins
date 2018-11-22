package net.dev.rpg.APIs;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.rpg.Main;

public class ManaAPI {

	public static HashMap<Player, Integer> mana = new HashMap<>();

	public static void sendMana(Player p) {

		new BukkitRunnable() {

			@Override
			public void run() {
				TitleAPI.sendActionBar("§a§lMana:§e" + getMana(p), p);
			}
		}.runTaskTimer(Main.getInstance(), 10, 10);

	}

	public static int getMana(Player p) {
		if (mana.get(p) < 0) {
			return 0;
		} else {
			return mana.getOrDefault(p, 100);
		}
	}

	public static void removeMana(Player p, int qnt) {
		mana.replace(p, getMana(p) - qnt);
	}

	public static void regenMana(Player p) {
		int qnt = Main.getInstance().getConfig().getInt("ManaRegen.Rate");
		int sec = Main.getInstance().getConfig().getInt("ManaRegen.Seconds") * 20;
		new BukkitRunnable() {
			@Override
			public void run() {
				if (getMana(p) < 100) {
					mana.replace(p, getMana(p) + qnt);
				}
			}
		}.runTaskTimer(Main.getInstance(), sec, sec);

	}

}
