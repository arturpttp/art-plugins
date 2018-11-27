package net.dev.art.grupos.api;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.dev.art.grupos.objetos.Grupo;

public class NamesAPI {

	public static HashMap<Player, Grupo> GRUPOS = new HashMap<>();

	public static void setName(Player player, String prefix) {
		setName(player, prefix + " ", "");
	}

	public static void setName(Player player, String prefix, String suffix) {

		setName(player, prefix + " ", suffix);
	}

	private static boolean active = true;

	public static void update() {
		if (isActive()) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.getScoreboard() == null) {
					p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
				} else {
					update(p.getScoreboard());
				}
			}
			update(Bukkit.getScoreboardManager().getMainScoreboard());
		}

	}

	public static void update(Scoreboard scoreboard) {
		if (isActive()) {
			for (Entry<Player, Grupo> map : GRUPOS.entrySet()) {
				Player p = map.getKey();
				Grupo g = map.getValue();
				Team team = null;
				if ((team = scoreboard.getTeam(p.getName())) == null) {
					team = scoreboard.registerNewTeam(p.getName());
				}
				if (!team.hasPlayer(p)) {
					team.addPlayer(p);
				}
				team.setPrefix(g.getPrefix()+" ");

			}
		}
	}

	public static boolean isActive() {
		return active;
	}

	static {
		Plugin plugin = Bukkit.getPluginManager().getPlugins()[0];
		new BukkitRunnable() {

			@Override
			public void run() {
				update();
			}
		}.runTaskTimer(plugin, 20, 20);
	}

}
