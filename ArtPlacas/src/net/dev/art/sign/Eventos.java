package net.dev.art.sign;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.dev.art.core.utils.ArtLib;

public class Eventos implements Listener, ArtLib {

	@EventHandler
	void event(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (e.getLines()[1].equalsIgnoreCase("[COMANDO]")) {
			if (e.getLines()[2] == null || e.getLines()[2] == "" || e.getLines()[2] == " ") {
				e.getBlock().breakNaturally();
				p.sendMessage("§cAdicione um comando valido!");
			} else {
				e.setLine(1, "§9[COMANDO]");
				e.setLine(2, e.getLine(2));
				p.sendMessage("§aComando adicionado a placa corretamente!");
			}
		}
	}

	@EventHandler
	void event(PlayerInteractEvent e) {
		if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;
		if (!(e.getClickedBlock().getState() instanceof Sign)) {
			return;
		}
		Player p = e.getPlayer();
		Sign sign = (Sign) e.getClickedBlock().getState();
		if (sign.getLine(1).equalsIgnoreCase("§9[COMANDO]")) {
			p.chat(sign.getLine(2));
		}

	}

//	@EventHandler
//	void event(PlayerJoinEvent e) {
//		Player p = e.getPlayer();
//		int i = 250;
//		for (Location loc : getCircle(p.getLocation(), 5, i)) {
//			ArmorStand stand = (ArmorStand) p.getLocation().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
//			stand.setVisible(true);
//			stand.setHelmet(new ItemStack(Material.GLASS, 1, (short) 5));
//			stand.setCanPickupItems(false);
//			stand.setCustomName("§cCirculo");
//			stand.setCustomNameVisible(true);
//			i++;
//			mensagem(p, "§aSpawnando" + i);
//		}
//		mensagem(p, "§aSpanados" + i);
//	}

	@EventHandler
	void event(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().startsWith("/circulo") || e.getMessage().contains("circulo")
				|| e.getMessage().contains("/circulo")) {
			Player p = e.getPlayer();
			int i = 50;
			for (Location loc : getCircle(p.getLocation(), 5, i)) {
				ArmorStand stand = (ArmorStand) p.getLocation().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
				stand.setVisible(false);
				stand.setHelmet(new ItemStack(Material.GLASS, 1, (short) 5));
				stand.setCanPickupItems(false);
				stand.setCustomName("§cCirculo");
				stand.setCustomNameVisible(true);
				i++;
				mensagem(p, "§aSpawnando" + i);
			}
			mensagem(p, "§aSpanados" + i);
		}
	}

	public ArrayList<Location> getCircle(Location center, double radius, int amount) {
		World world = center.getWorld();
		double increment = (2 * Math.PI) / amount;
		ArrayList<Location> locations = new ArrayList<Location>();
		for (int i = 0; i < amount; i++) {
			double angle = i * increment;
			double x = center.getX() + (radius * Math.cos(angle));
			double z = center.getZ() + (radius * Math.sin(angle));
			locations.add(new Location(world, x, center.getY(), z));
		}
		return locations;
	}

}
