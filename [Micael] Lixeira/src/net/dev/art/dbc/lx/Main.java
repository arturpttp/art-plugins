package net.dev.art.dbc.lx;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements MessagesManager {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public String prefix = getConfig().getString("prefix").replace("&", "§");

	@Override
	public void onEnable() {

		instance = this;
		saveDefaultConfig();
		Commands();

		Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
			Bukkit.getWorlds().forEach(world -> {
				for (Item entity : world.getEntitiesByClass(Item.class)) {
					entity.remove();
				}
				for (Entity e : world.getEntities()) {
					if (e instanceof Creature) {
						e.remove();
					}

				}
			});
		}, 60 * 5 * 60, 60 * 5 * 60);

	}

	void Commands() {
		getCommand("lixeira").setExecutor(new LixeiraCommand());
	}

	@Override
	public void onDisable() {
	}

}
