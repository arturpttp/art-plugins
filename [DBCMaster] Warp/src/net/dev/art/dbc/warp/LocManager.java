package net.dev.art.dbc.warp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocManager {

	public static void setWarp(Warp w) {
		Main.getInstance().warp.set("Warps." + w.getName() + ".X", w.getLocation().getX());
		Main.getInstance().warp.set("Warps." + w.getName() + ".Y", w.getLocation().getY());
		Main.getInstance().warp.set("Warps." + w.getName() + ".Z", w.getLocation().getZ());
		Main.getInstance().warp.set("Warps." + w.getName() + ".Yaw", w.getLocation().getYaw());
		Main.getInstance().warp.set("Warps." + w.getName() + ".Pitch", w.getLocation().getPitch());
		Main.getInstance().warp.set("Warps." + w.getName() + ".World", w.getLocation().getWorld().getName());
		try {
			Main.getInstance().warp.save(Main.getInstance().w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.warps.put(w.getName(), w);
	}

	public static String getWarpList() {
		List<String> list = new ArrayList<>();
		Main.warps.entrySet().forEach(entry -> {
			list.add(entry.getKey());
		});
		String toSet = "";
		if (list.size() > 0) {
			for (String str : list) {
				toSet = toSet + "," + str;
			}
		}
		if (toSet.length() > 0) {
			toSet = toSet.substring(1);
		} else {
			toSet = "Nenhuma";
		}
		return toSet;
	}

	public static Warp getWarp(String name) {
		if (Main.warps.containsKey(name)) {
			return Main.warps.get(name);
		}
		return null;
	}

	public static void delWarp(Warp w) {
		Main.getInstance().warp.set("Warps." + w.getName(), null);
		Main.getInstance().saveConfig();
		Main.warps.remove(w.getName());
	}

}
