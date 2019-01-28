package net.dev.art.drops.apis;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.dev.art.drops.Main;

public class DropsAPI {

	static FileConfiguration config = Main.getInstance().getConfig();

	public static int getDrops(Player p, Material m) {
		if (config.getInt(p.getName().toLowerCase() + "." + m.toString()) < 0) {
			return 0;
		}
		return config.getInt(p.getName().toLowerCase() + "." + m.toString());
	}

	public static int getDrops(String p, Material m) {
		if (config.getInt(p.toLowerCase() + "." + m.toString()) < 0) {
			return 0;
		}
		return config.getInt(p.toLowerCase() + "." + m.toString());
	}

	public static void setDrops(Player p, Material m, Double quantia) {
		config.set(p.getName().toLowerCase() + "." + m.toString(), quantia);
		Main.getInstance().saveConfig();
	}

	public static void addDrops(Player p, Material m, Double quantia) {
		setDrops(p, m, getDrops(p, m) + quantia);
		Main.getInstance().saveConfig();
	}

	public static void removeDrops(Player p, Material m, Double quantia) {
		setDrops(p, m, getDrops(p, m) - quantia);
		Main.getInstance().saveConfig();
	}

}
