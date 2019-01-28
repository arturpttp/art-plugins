package net.dev.art.facs.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.dev.art.facs.Main;
import net.dev.art.facs.menus.FactionsMenu;
import net.dev.art.facs.objects.Faction;
import net.dev.art.facs.objects.FactionPlayer;

public class FactionsManager {

	private static String nome;

	public static boolean existFaction(String fac) {
		return Main.factions.containsKey(fac);
	}

	public static boolean existFactionByTag(String tag) {
		for (Faction fac : Main.factions.values()) {
			return fac.getTag().equalsIgnoreCase(tag);
		}
		return false;
	}

	public static Faction getFactionByName(String fac) {
		return Main.factions.get(fac);
	}

	public static void newFac(String nome, String tag, String p) {
		String ID = new Random().nextInt(500000000) + 1000 + "";
		List<String> recrutas = new ArrayList<>();
		List<String> membros = new ArrayList<>();
		List<String> capitoes = new ArrayList<>();
		List<String> aliados = new ArrayList<>();
		List<String> inimigos = new ArrayList<>();
		double banco = 0.0;
		String lider = p;
		Faction fac = new Faction(ID, aliados, inimigos, banco, capitoes, membros, recrutas, nome, lider, tag);
		fac.setLider(lider);
		fac.addOnline(lider);
		Main.factions.put(nome, fac);
		FactionPlayer fp = PlayersManager.getPlayer(p);
		fp.setFaction(nome);
		Main.players.remove(p);
		Main.players.put(lider, fp);
	}

}
