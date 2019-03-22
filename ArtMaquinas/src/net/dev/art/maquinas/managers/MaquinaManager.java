package net.dev.art.maquinas.managers;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.dev.art.core.objects.ArtItem;
import net.dev.art.maquinas.objetos.Level;
import net.dev.art.maquinas.objetos.Maquina;

public class MaquinaManager implements Listener {

	public static HashMap<String, Maquina> maquinas = new HashMap<>();

	@EventHandler
	void onPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		Player p = e.getPlayer();
		Maquina m1 = new Maquina("§7Maquina de ferro", p.getName(), b.getLocation(), Level.N1,
				Arrays.asList(new ArtItem(Material.DIAMOND)),
				new ArtItem(Material.DIAMOND_BLOCK).nome("§bMaquina de diamante"));
		
		if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(m1.getIcon().getItemMeta().getDisplayName())) {
			m1.start();
			p.sendMessage("§aMaquina colocada");
		}
	}


}
