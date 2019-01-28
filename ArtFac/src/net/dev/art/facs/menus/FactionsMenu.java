package net.dev.art.facs.menus;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import net.dev.art.core.managers.Menu;
import net.dev.art.core.objects.ArtItem;

public class FactionsMenu extends Menu {

	public static HashMap<String, Integer> criar_fac = new HashMap<>();
	public static HashMap<String, String> nome = new HashMap<>();
	public static HashMap<String, String> tag = new HashMap<>();

	public FactionsMenu() {
		super("§7Suas informações", MenuSize.FIVE_LINES);
		addItems();
	}

	private void addItems() {
		this.setItem(20, new ArtItem(Material.BANNER).setData((short) 15).nome("§fCriar facção!"), e -> {
			Player p = (Player) e.getWhoClicked();
			p.chat("/f criar");
		});
	}

}
