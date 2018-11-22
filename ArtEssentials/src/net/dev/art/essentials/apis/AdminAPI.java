package net.dev.art.essentials.apis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AdminAPI {

	public static List<Player> players = new ArrayList<>();
	
	public static void prison(Player player) {
		players.add(player);

		Location loc = player.getLocation();
		loc = loc.add(0, 10, 0);

		player.playSound(player.getLocation(), Sound.WITHER_SPAWN, 2, 1);
		loc.clone().add(0, 0, 0).getBlock().setType(Material.BEDROCK);
		loc.clone().add(0, 3, 0).getBlock().setType(Material.BEDROCK);
		loc.clone().add(0, 1, -1).getBlock().setType(Material.BEDROCK);
		loc.clone().add(-1, 1, 0).getBlock().setType(Material.BEDROCK);
		loc.clone().add(1, 1, 0).getBlock().setType(Material.BEDROCK);
		loc.clone().add(0, 1, 1).getBlock().setType(Material.BEDROCK);
		player.teleport(loc.clone().add(-0.4, 1, -0.4));
		player.sendMessage("§cVoce foi Aprisionado!");
	}
	
	public void removePrison(Player player) {
		players.remove(player);
		Location loc = player.getLocation();
		player.sendMessage("§aVoce foi liberto da Prisão!");
		player.playSound(player.getLocation(), Sound.LEVEL_UP, 2, 1);
		loc.clone().add(0, -1, 0).getBlock().setType(Material.AIR);
		loc.clone().add(0, 2, 0).getBlock().setType(Material.AIR);
		loc.clone().add(0, 0, 1).getBlock().setType(Material.AIR);
		loc.clone().add(1, 0, 0).getBlock().setType(Material.AIR);
		loc.clone().add(0, 0, -1).getBlock().setType(Material.AIR);
		loc.clone().add(-1, 0, 0).getBlock().setType(Material.AIR);
	}
	
}
