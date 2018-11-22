package net.dev.art.cxs.apis;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class CaixasAPI {

	public static FileConfiguration CONFIG;

	public static void loadItems() {
		for (String cxi : CONFIG.getConfigurationSection("Itens").getKeys(false)) {
			int material = CONFIG.getInt("Itens." + cxi + ".ID");
			String name = CONFIG.getString("Itens." + cxi + ".Nome");
			List<String> lore = CONFIG.getStringList("Itens." + cxi + ".Lore");

		}
	}

}
