package net.dev.art.tags;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("tags.membro")) {
				p.setDisplayName("§8§lMEMBRO " + p.getName());
				p.setPlayerListName("§8§lMEMBRO " + p.getName());
			} else if (p.hasPermission("tags.admin")) {
				p.setDisplayName("§c§lADMIN " + p.getName());
				p.setPlayerListName("§c§lADMIN " + p.getName());
			} else if (p.hasPermission("tags.dono")) {
				p.setDisplayName("§4§lDONO " + p.getName());
				p.setPlayerListName("§4§lDONO " + p.getName());
			}

		}
	}

	@EventHandler
	void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("tags.membro")) {
			p.setDisplayName("§8§lMEMBRO " + p.getName());
			p.setPlayerListName("§8§lMEMBRO " + p.getName());
		} else if (p.hasPermission("tags.admin")) {
			p.setDisplayName("§c§lADMIN " + p.getName());
			p.setPlayerListName("§c§lADMIN " + p.getName());
		} else if (p.hasPermission("tags.dono")) {
			p.setDisplayName("§4§lDONO " + p.getName());
			p.setPlayerListName("§4§lDONO " + p.getName());
		}

	}

}
