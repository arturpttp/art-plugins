package net.dev.art.maquinas.objetos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import net.dev.art.core.managers.API;
import net.dev.art.maquinas.Main;
import net.dev.art.maquinas.managers.MaquinaManager;

public class Maquina {

	private String name, owner;
	private Location location;
	private Level level;
	private List<ItemStack> drops = new ArrayList<>();
	private ItemStack icon;

	public Maquina(String name, String owner, Location location, Level level, List<ItemStack> drops, ItemStack icon) {
		this.name = name;
		this.owner = owner;
		this.location = location;
		this.level = level;
		this.drops = drops;
		this.icon = icon;
		MaquinaManager.maquinas.put(name, this);
	}

	public Maquina start() {
		BukkitTask task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
			int seconds = 120;

			if (seconds == 0) {
				if (Bukkit.getPlayer(owner) != null) {
					Player p = Bukkit.getPlayer(owner);
					p.sendMessage("§cSua maquina foi finalizada");
				}
				seconds--;
			} else if (seconds > 0) {
				seconds--;
				drops.forEach(item -> {
					getLocation().getWorld().dropItem(getLocation(), new ItemStack(item.getType(), level.getDrops()));
				});

				if (seconds == 90) {
					if (Bukkit.getPlayer(owner) != null) {
						Player p = Bukkit.getPlayer(owner);
						p.sendMessage("§cSua maquina ira ser finalizada em " + seconds + " segundos");
					}
				} else if (seconds == 60) {
					if (Bukkit.getPlayer(owner) != null) {
						Player p = Bukkit.getPlayer(owner);
						p.sendMessage("§cSua maquina ira ser finalizada em " + seconds + " segundos");
					}

				} else if (seconds == 30) {
					if (Bukkit.getPlayer(owner) != null) {
						Player p = Bukkit.getPlayer(owner);
						p.sendMessage("§cSua maquina ira ser finalizada em " + seconds + " segundos");
					}

				} else if (seconds == 15) {
					if (Bukkit.getPlayer(owner) != null) {
						Player p = Bukkit.getPlayer(owner);
						p.sendMessage("§cSua maquina ira ser finalizada em " + seconds + " segundos");
					}

				} else if (seconds <= 10) {
					if (Bukkit.getPlayer(owner) != null) {
						Player p = Bukkit.getPlayer(owner);
						p.sendMessage("§cSua maquina ira ser finalizada em " + seconds + " segundos");
					}

				}

			}

		}, level.getDelay() * 20, level.getDelay() * 20);
		return this;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public Location getLocation() {
		return location;
	}

	public Level getLevel() {
		return level;
	}

	public List<ItemStack> getDrops() {
		return drops;
	}

	public ItemStack getIcon() {
		return icon;
	}

}
