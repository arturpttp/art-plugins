package net.dev.art.maquinas.apis;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.Hologram;
import net.dev.art.api.APIs.ItemsAPI;
import net.dev.art.maquinas.Main;

public class MaquinasAPI {

	public static FileConfiguration config = Main.getInstance().getConfig();
	public static HashMap<Player, Location> maquinasplayers = new HashMap<>();
	public static HashMap<Block, Boolean> maquinasativas = new HashMap<>();

	public static void loadMaquinas() {
		for (String p : config.getKeys(false)) {
			Location location = getLocation(p);
			if (location == null)
				break;
			else
				maquinasplayers.put(Bukkit.getPlayer(p), location);
		}
	}

	public static void saveMaquinas() {
		if (maquinasplayers.keySet() == null) return;
		for (Player p : maquinasplayers.keySet()) {
			setLocation(p.getName(), maquinasplayers.get(p));
		}
	}

	public static void setLocation(String path, Location l) {
		config.set(path + ".X", l.getX());
		config.set(path + ".Y", l.getY());
		config.set(path + ".Z", l.getZ());
		config.set(path + ".Yaw", l.getYaw());
		config.set(path + ".Pitch", l.getPitch());
		config.set(path + ".World", l.getWorld().getName());
		Main.getInstance().saveConfig();
	}

	public static Location getLocation(String path) {
		double x = config.getDouble(path + ".X");
		double y = config.getDouble(path + ".Y");
		double z = config.getDouble(path + ".Z");
		float pitch = (float) config.getDouble(path + ".Pitch");
		float yaw = (float) config.getDouble(path + ".Yaw");
		World world = Bukkit.getWorld(path + ".World");
		return new Location(world, x, y, z, yaw, pitch);
	}

	public static void startmachine(Location l, Block b, Player p) {
		maquinasativas.put(b, true);
		ItemStack drop = ItemsAPI.add(Material.DIAMOND, 5);
		String status = "§aAbilitada";
		if (maquinasativas.get(b) == false) {
			status = "§cDesabilitada";
		}
		String[] lines = new String[] { "§eMaquina TIPO: §b" + drop.toString(), "§eDono: §b" + p.getName(),
				"§eNivel: §b1", "§eStatus: " + status };
		Hologram h = new Hologram(lines);
		h.show(p, l);
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.getInstance(), new Runnable() {
			int seconds = 60 * 20 * 1;

			public void run() {
				if (seconds >= 0) {
					b.getLocation().getWorld().dropItem(b.getLocation(), drop);
					seconds--;
				} else if (seconds == 0) {
					maquinasativas.put(b, false);
					seconds--;
				}
			}
		}, 20, 20);
	}

}
