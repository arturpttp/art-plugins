package net.dev.art.utils.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.utils.ArtLib;
import net.dev.art.utils.Main;
import net.dev.art.utils.apis.AdminAPI;

public class AdminEvents implements Listener, ArtLib {

	@EventHandler
	public void onDrop(EntitySpawnEvent e) {
		if (e.getEntity() instanceof Item) {
			Item item = (Item) e.getEntity();

			Main.getInstance().getServer().getScheduler().scheduleAsyncRepeatingTask(Main.getInstance(),
					new BukkitRunnable() {

						@Override
						public void run() {
							String name = "§b" + item.getItemStack().getType().toString().replace("_", " ") + " §e"
									+ item.getItemStack().getAmount();
							item.setCustomName(name);

						}
					}, 5, 5);
			item.setCustomNameVisible(true);

		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("verpls"))
			return;
		String[] msg = e.getMessage().split(" ");
		List<String> plugins = new ArrayList<>();
		plugins.add("ver" + msg);
		plugins.add("help" + msg);
		plugins.add("ver");
		plugins.add("me");
		plugins.add("pl" + msg);
		plugins.add("pl");
		plugins.add("plugins" + msg);
		plugins.add("plugins");

		plugins.add("g" + msg);
		plugins.add("sc" + msg);
		plugins.add("spawn");
		plugins.add("warp");
		plugins.add("warp" + msg);
		plugins.add("casa");
		plugins.add("casa" + msg);
		plugins.add("g");

		plugins.add("deop" + msg);
		plugins.add("op" + msg);

		for (String Loop : plugins) {
			if (msg[0].equalsIgnoreCase("/" + Loop) && AdminAPI.presos.contains(p)) {
				mensagem(p, "§cVocê não pode usar comandos enquanto está sendo avaliado por um staff.");
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	void abririnv(PlayerInteractAtEntityEvent e) {
		if (!(e.getRightClicked() instanceof Player)) {
			return;
		}
		Player t = (Player) e.getRightClicked();
		Player p = e.getPlayer();
		if (!AdminAPI.admins.contains(p)) {
			return;
		}
		String dname = "";
		if (p.getItemInHand().getType() != Material.AIR || p.getItemInHand() != null) {
			dname = p.getItemInHand().getItemMeta().getDisplayName();
		}

		if (dname.equalsIgnoreCase("§ePrender Jogador")) {
			if (AdminAPI.presos.contains(t)) {
				AdminAPI.removePrison(t);
				p.sendMessage("§aVocê liberou §8» §f" + t.getName());
				t.sendMessage("§f" + p.getName() + " §aliberou você da avaliação");
			} else {
				AdminAPI.prison(t);
				p.sendMessage("§cVocê prendeu §8» §f" + t.getName());
				t.sendMessage("§f" + p.getName() + " §cPrendeu você para avaliação");
			}

		} else if (dname.equalsIgnoreCase("§eInformações do Jogador")) {
			AdminAPI.sendInfos(p, t);
		} else if (dname.equalsIgnoreCase("§eTeste De: §bAuto-Soup")) {
			AdminAPI.AutoSoup(p, t);
		} else if (dname.equalsIgnoreCase("§eTeste De: §bKill Aura")) {

		} else if (dname.equalsIgnoreCase("§eTeste De: §bKnockback")) {

		} else if (dname.equalsIgnoreCase("§eTroca Rapida")) {

		} else if (dname.equalsIgnoreCase("§eCrashar jogador")) {

		} else if (dname.equalsIgnoreCase("§eTeste De: §bNoFall")) {

		} else {
			p.openInventory(t.getInventory());
		}

	}

}
