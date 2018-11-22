package net.dev.art.essentials.apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.entity.Player;

import net.dev.art.api.APIs.CalendarioAPI;
import net.dev.art.essentials.Main;
import net.dev.art.essentials.objetos.Casa;

public class CasasAPI {

	public static Casa getCasaPeloDono(String player, String nome) {
		for (Casa c : getCasas(player)) {
			if (c.getNome().equalsIgnoreCase(nome)) {
				return c;
			}
		}
		return null;
	}

	public static Casa getCasaPeloNome(String player, String nome) {
		for (Casa c : getCasas(player)) {
			if (c.getNome().equalsIgnoreCase(nome)) {
				return c;
			}
		}
		return null;
	}

	public static String format(Casa c) {
		return "§bCordenadas: §bX: §e" + c.getLocation().getX() + " §bY:§e " + c.getLocation().getBlockY() + " §Z§: §e"
				+ c.getLocation().getZ() + " §bWorld§e: " + c.getLocation().getWorld().getName() + "\n"
				+ "§eCriação: §b" + CalendarioAPI.getData(c.getCriação()) + "§e ás §b"
				+ CalendarioAPI.getHoras(c.getCriação()) + "\n" + "\n" + "§eClique para ir";
	}

	public static List<Casa> getCasas(String player) {
		List<Casa> casas = new ArrayList<>();
		if (Main.casas.containsKey(player)) {
			for (Casa casa : Main.casas.get(player)) {
				casas.add(casa);
			}
		}
		return (casas);
	}

	public static void addCasa(String p, Casa c) {
		if (Main.casas.containsKey(p)) {
			Main.casas.get(p).add(c);
		} else {
			List<Casa> lista = new LinkedList<Casa>(Arrays.asList(c));
			Main.casas.put(p, lista);
		}
	}

	public static Casa criarCasa(Player p, String nome) {
		Casa casa = new Casa(p.getName(), nome, p.getLocation());
		addCasa(p.getName(), casa);
		return casa;
	}

}
