package net.dev.art.facs.events;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.facs.manager.PlayersManager;
import net.dev.art.facs.objects.FactionPlayer;
import net.dev.art.facs.utils.FUtils;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.rank.Main;

public class onDeath implements Listener {

	public boolean isEmptyInv(Player p) {
		return p.getInventory().firstEmpty() == 0;
	}

	@EventHandler
	void death(PlayerDeathEvent e) {
		if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity();
			Player k = (Player) p.getKiller();
			FactionPlayer kfp = PlayersManager.getPlayer(k.getName());
			kfp.setKills(kfp.getKills() + 1);
			FactionPlayer pfp = PlayersManager.getPlayer(p.getName());
			pfp.setDeaths(pfp.getDeaths() + 1);
			HashMap<String, Material> oldBlocks = new HashMap<>();
			if (!isEmptyInv(p)) {
				e.getDrops().clear();
				oldBlocks.put(k.getName(), p.getLocation().getBlock().getType());
				p.getLocation().getBlock().setType(Material.CHEST);
				Chest chest = (Chest) p.getLocation().getBlock().getState();
				Inventory cInv = chest.getBlockInventory();
				String holoName = "" + GruposAPI.getGrupo(p.getName()).getPrefix() + " " + p.getName()
						+ "§fMorreu aqui ,";
				String holo2Name = "§fo bau vai desaparecer em §a$tempos";
				for (ItemStack i : p.getInventory().getContents()) {
					if (i == null || i.getType() == Material.AIR)
						break;
					if (!FUtils.isInvFull(cInv))
						cInv.addItem(i);
					else
						k.getWorld().dropItem(k.getLocation(), i);
				}

				for (ItemStack i : p.getInventory().getArmorContents()) {
					if (i == null || i.getType() == Material.AIR)
						break;
					if (!FUtils.isInvFull(cInv))
						cInv.addItem(i);
					else
						k.getWorld().dropItem(k.getLocation(), i);
				}
				ArmorStand holo = p.getWorld().spawn(p.getLocation().add(0, 0.4, 0), ArmorStand.class);
				holo.setSmall(true);
//				holo.setMarker(true);
				holo.setVisible(false);
				holo.setCanPickupItems(false);
				holo.setCustomNameVisible(true);
				holo.setGravity(false);
				holo.setCustomName(holoName);
				ArmorStand holo2 = p.getWorld().spawn(p.getLocation().add(0, 0.1, 0), ArmorStand.class);
				holo2.setSmall(true);
//				holo2.setMarker(true);
				holo2.setVisible(false);
				holo2.setCanPickupItems(false);
				holo2.setCustomNameVisible(true);
				holo2.setGravity(false);
				holo2.setCustomName(holoName);
				new BukkitRunnable() {
					int sumir = 30;

					@Override
					public void run() {
						holo2.setCustomName(holo2Name.replace("$tempo", sumir + ""));
						sumir--;
						if (sumir == 0) {
							holo.remove();
							holo2.remove();
							chest.getLocation().getBlock().setType(oldBlocks.get(k.getName()));
							oldBlocks.remove(k.getName());
							sumir--;
						}
					}
				}.runTaskTimer(Main.getInstance(), 20, 20);
			}

		}
	}

}
