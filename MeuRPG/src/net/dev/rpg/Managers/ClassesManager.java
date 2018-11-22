package net.dev.rpg.Managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.rpg.Main;
import net.dev.rpg.APIs.ClassesAPI;

@SuppressWarnings("all")
public class ClassesManager implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("classe")) {
			if (args.length == 0) {
				if (!(p.hasPermission("classe.admin"))) {
					p.sendMessage("§bA_RPG §8§l>>§e Sua Classe é §b§l" + ClassesAPI.getClasse(p));
					return true;
				}
				p.sendMessage("§bA_RPG §8§l>>§e Sua Classe é §b§l" + ClassesAPI.getClasse(p));
			} else {
				if (args[0].equalsIgnoreCase("menu")) {
					ClassesAPI.SeletorDeClasses(p);
				}
			}

		}
		return false;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (ClassesAPI.hasClasse(p)) {

		} else {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new BukkitRunnable() {

				@Override
				public void run() {
					ClassesAPI.SeletorDeClasses(p);

				}
			}, 0L);

		}

	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if (e.getInventory().getTitle().equalsIgnoreCase("§6§lSeletor De Classe")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
				String classe = ClassesAPI.getClasse(p);
				switch (e.getCurrentItem().getItemMeta().getDisplayName()) {
				case "§b§lMAGO":
					if (ClassesAPI.hasClasse(p)) {
						p.sendMessage("§c§lVocê Ja Pertence a Classe §b§l" + classe);
						p.closeInventory();
					} else {
						ClassesAPI.setClasse(p, "MAGO");
						p.sendMessage("§a§lVocê Escolheu a Classe §b§lMAGO");
						p.closeInventory();
					}
					break;
				case "§b§lARQUEIRO":
					if (ClassesAPI.hasClasse(p)) {
						p.sendMessage("§c§lVocê Ja Pertence a Classe §b§l" + classe);
						p.closeInventory();
					} else {
						ClassesAPI.setClasse(p, "ARQUEIRO");
						p.sendMessage("§a§lVocê Escolheu a Classe §b§lARQUEIRO");
						p.closeInventory();
					}
					break;
				case "§b§lGUERREIRO":
					if (ClassesAPI.hasClasse(p)) {
						p.sendMessage("§c§lVocê Ja Pertence a Classe §b§l" + classe);
						p.closeInventory();
					} else {
						ClassesAPI.setClasse(p, "GUERREIRO");
						p.sendMessage("§a§lVocê Escolheu a Classe §b§lGUERREIRO");
						p.closeInventory();
					}
					break;
				case "§b§lPYROMANCER":
					if (ClassesAPI.hasClasse(p)) {
						p.sendMessage("§c§lVocê Ja Pertence a Classe §b§l" + classe);
						p.closeInventory();
					} else {
						ClassesAPI.setClasse(p, "PYROMANCER");
						p.sendMessage("§a§lVocê Escolheu a Classe §b§lPYROMANCER");
						p.closeInventory();
					}
					break;
				case "§b§lNECROMANCER":
					if (ClassesAPI.hasClasse(p)) {
						p.sendMessage("§c§lVocê Ja Pertence a Classe §b§l" + classe);
						p.closeInventory();
					} else {
						ClassesAPI.setClasse(p, "NECROMANCER");
						p.sendMessage("§a§lVocê Escolheu a Classe §b§lNECROMANCER");
						p.closeInventory();
					}
					break;
				case "§b§lHYDROMANCER":
					if (ClassesAPI.hasClasse(p)) {
						p.sendMessage("§c§lVocê Ja Pertence a Classe §b§l" + classe);
						p.closeInventory();
					} else {
						ClassesAPI.setClasse(p, "HYDROMANCER");
						p.sendMessage("§a§lVocê Escolheu a Classe §b§lHYDROMANCER");
						p.closeInventory();
					}
					break;
				case "§b§lGEOMANCER":
					if (ClassesAPI.hasClasse(p)) {
						p.sendMessage("§c§lVocê Ja Pertence a Classe §b§l" + classe);
						p.closeInventory();
					} else {
						ClassesAPI.setClasse(p, "GEOMANCER");
						p.sendMessage("§a§lVocê Escolheu a Classe §b§lGEOMANCER");
						p.closeInventory();
					}
					break;
				case "§b§lAEROMANCER":
					if (ClassesAPI.hasClasse(p)) {
						p.sendMessage("§c§lVocê Ja Pertence a Classe §b§l" + classe);
						p.closeInventory();
					} else {
						ClassesAPI.setClasse(p, "AEROMANCER");
						p.sendMessage("§a§lVocê Escolheu a Classe §b§lAEROMANCER");
						p.closeInventory();
					}
					break;

				}

			}
		}

	}

}
