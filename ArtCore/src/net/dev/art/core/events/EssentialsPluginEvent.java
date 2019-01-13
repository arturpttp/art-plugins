package net.dev.art.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import net.dev.art.core.ArtPlugin;
import net.dev.art.core.objects.ActionBar;
import net.dev.art.core.utils.ArtLib;
import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.rank.RanksAPI;

public class EssentialsPluginEvent implements Listener, ArtLib {

	public String msgPlugin(boolean b, ArtPlugin pl) {

		String prefix = "§b" + pl.getName() + " §8» ";

		if (b) {
			return prefix + "§eVersao: §b" + pl.getDescription().getVersion() + "§e Autores: §b"
					+ pl.getDescription().getAuthors() + " " + "§eHabilitado com §esucesso";
		} else {
			return prefix + "§cVersao: §f" + pl.getDescription().getVersion() + "§c Autores: §f"
					+ pl.getDescription().getAuthors() + " " + "§cDesabilitado com §csucesso";
		}
	}

	@EventHandler
	void onEnable(PluginEnableEvent e) {
		if (e.getPlugin() instanceof ArtPlugin) {
			ArtPlugin pl = (ArtPlugin) e.getPlugin();
			console(msgPlugin(true, pl));
		}
	}

	@EventHandler
	void onDissable(PluginDisableEvent e) {
		if (e.getPlugin() instanceof ArtPlugin) {
			ArtPlugin pl = (ArtPlugin) e.getPlugin();
			console(msgPlugin(false, pl));
		}
	}

	public <T extends Player> T getTarget(Player entity, Iterable<T> entities) {
		if (entity == null) {
			return null;
		}
		T target = null;
		for (T other : entities) {
			Vector n = other.getLocation().toVector().subtract(entity.getLocation().toVector());
			if ((entity.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < 1.0D)
					&& (n.normalize().dot(entity.getLocation().getDirection().normalize()) >= 0.0D)) {
				if ((target == null) || (target.getLocation().distanceSquared(entity.getLocation()) > other
						.getLocation().distanceSquared(entity.getLocation()))) {
					target = other;
				}

				String prefix = GruposAPI.getGrupo(target.getName()).getPrefix();

				String rank = RanksAPI.getCurrentRank(target).getPrefix().replace("&", "§");
				String coins = CoinsAPI.getCoinsFormatado(target);
				String cash = CashAPI.getCashFormatado(target);
				new ActionBar("§eRank §8» " + rank + " §eGrupo§8 » " + prefix + " §eCoins §8» " + coins + " §eCash §8» "
						+ cash).sendToPlayer(entity);
			}
		}
		return target;
	}

	public Player getTargetPlayer(Player player) {
		return getTarget(player, player.getWorld().getPlayers());
	}

	@EventHandler
	void event(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		getTargetPlayer(p);
	}

}
