package net.dev.art.facs.manager;

import java.util.ArrayList;
import java.util.List;

import net.dev.art.facs.Main;
import net.dev.art.facs.objects.FactionPlayer;

public class PlayersManager {

	public static List<String> onlinePlayers = new ArrayList<>();

	public static FactionPlayer getPlayer(String name) {
		return Main.players.get(name);
	}

	public static void changeFaction(String player, String name) {
		getPlayer(player).setFaction(name);
		Main.getInstance().playersCF.set("Players." + player + ".Faction", name);
		Main.getInstance().playersCF.saveConfig();
	}

	public static boolean hasFaction(String name) {
		return Main.players.get(name).hasFaction();
	}

}
