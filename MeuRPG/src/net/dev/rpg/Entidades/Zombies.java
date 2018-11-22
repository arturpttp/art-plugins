package net.dev.rpg.Entidades;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Zombies {

	public static void lvl1(Player p) {
		CriarEntidade z1 = new CriarEntidade(EntityType.ZOMBIE, "§cZumbi§6§l LVL:§6 1", p.getLocation());
	}
	
}
