package net.dev.art.sign;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.dev.art.core.managers.ArtCommand;

public class Circle extends ArtCommand {

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

	public Circle() {
		super("circle");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (isPlayer(sender))
			return false;
		Player p = (Player) sender;


		return false;
	}

}
