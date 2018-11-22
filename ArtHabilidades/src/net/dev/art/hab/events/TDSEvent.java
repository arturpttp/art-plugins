package net.dev.art.hab.events;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.TitleAPI;
import net.dev.art.hab.Main;

public class TDSEvent implements Listener {

	public static int gerarID() {
		return ThreadLocalRandom.current().nextInt(0, 100);
	}

	@EventHandler
	void onDeath(BlockBreakEvent e) {
		int id = gerarID();
		if (id > 85) {
			e.getPlayer().sendMessage(
					Main.getInstance().prefix.replace("ArtHabilidades", "§aTrevo Da Sorte")+ "§eParabens você deu a sorte de achar uma mação entre os blocos!");
			TitleAPI.sendActionBar("§aVocê encontrou uma maçã §6dourada§a entre os blocos", e.getPlayer());
			e.getPlayer().getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
		}
	}

}
