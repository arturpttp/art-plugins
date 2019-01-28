package net.dev.art.facs.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class ArtFacManager {

	public static String stripList(List<String> list) {
		String toSet = "";
		if (list.size() > 0) {
			for (String str : list) {
				toSet = toSet + "," + str;
			}
		}
		if (toSet.length() > 0) {
			toSet = toSet.substring(1);
		} else {
			toSet = "?";
		}
		return toSet;
	}

	public static List<String> unstripString(String str) {
		List<String> lista = new ArrayList<>();
		String[] split = str.split(",");
		for (String sp : split) {
			if (str == "?" || split == null)
				break;
			if (sp == "" || sp == " " || sp == "?")
				break;
			lista.add(sp);
		}
		return lista;
	}

	public static String serializeLoc(Location loc) {
		String serialized = "";

		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		float pitch = loc.getPitch();
		float yaw = loc.getYaw();
		String world = loc.getWorld().getName();

		String serialize = x + ":" + y + ":" + z + ":" + pitch + ":" + yaw + ":" + world;

		return serialized;
	}

	public static Location deserializeLoc(String loc) {
		String[] split = loc.split(":");

		double x = Double.valueOf(split[0]);
		double y = Double.valueOf(split[1]);
		double z = Double.valueOf(split[2]);
		float pitch = Float.valueOf(split[3]);
		float yaw = Float.valueOf(split[4]);
		World world = Bukkit.getWorld(split[5]);

		return new Location(world, x, yaw, z, yaw, pitch);

	}

}
