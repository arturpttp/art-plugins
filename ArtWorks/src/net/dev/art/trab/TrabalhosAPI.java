package net.dev.art.trab;

import org.bukkit.entity.Player;

import net.dev.art.core.objects.Inventario;

public class TrabalhosAPI {

	public static void Abrir(Player p) {
		Inventario inv = new Inventario(9 * 4, "§c§lTrabalhos");
		TItem i = new TItem(Main.getInstance());
		inv.getInventory().setItem(10, TItem.Minerador);
		inv.getInventory().setItem(12, TItem.Assassino);
		inv.getInventory().setItem(14, TItem.Fazendeiro);
		inv.getInventory().setItem(16, TItem.Policial);
		inv.getInventory().setItem(19, TItem.Lenhador);
		inv.getInventory().setItem(21, TItem.Caçador);
		inv.getInventory().setItem(23, TItem.Escavador);
		inv.getInventory().setItem(25, TItem.Empresario);

		inv.getInventory().setItem(35, TItem.Demição);

		inv.open(p);
	}

	public static boolean hasWork(String p) {
		String emprego = Main.getInstance().config.getString("Trabalhadores." + p.toLowerCase());
		if (emprego == null) {
			return false;
		}
		return true;
	}

	public static String getWork(String p) {
		String emprego = Main.getInstance().config.getString("Trabalhadores." + p.toLowerCase());
		if (hasWork(p))
			return emprego;
		else
			return "Nenhum";
	}

}
