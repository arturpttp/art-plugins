package net.dev.art.trab;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class TrabalhosManager implements Listener {

	@EventHandler
	void onClickEntity1(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		Entity en = e.getEntity();
		if (en.getName().equalsIgnoreCase("§c§lTrabalhos")) {
			if (p.hasPermission("matarnpc")) {
				if (p.getItemInHand().getType() == Material.STICK && p.getGameMode() == GameMode.CREATIVE
						&& p.isSneaking()) {
					en.remove();
				} else {
					TrabalhosAPI.Abrir(p);
				}
			} else {
				TrabalhosAPI.Abrir(p);
			}
		}
	}

	@EventHandler
	void onClickEntity(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		if (e.getRightClicked().getName().equalsIgnoreCase("§c§lTrabalhos")) {
			TrabalhosAPI.Abrir(p);
		}
	}

	@EventHandler
	void onClickTrabalhosInv(InventoryClickEvent e) {
		Player p = ((Player) e.getWhoClicked());
		TItem i = new TItem(Main.getInstance());
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;

		if (e.getInventory().getTitle().equalsIgnoreCase("§c§lTrabalhos")) {
			e.setCancelled(true);
			String emprego = Main.getInstance().config.getString("Trabalhadores." + p.getName().toLowerCase());
			if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(TItem.Demição.getItemMeta().getDisplayName())) {
				Main.getInstance().config.set("Trabalhadores." + p.getName().toLowerCase(), null);
				Main.getInstance().config.saveConfig();
				p.closeInventory();
				if (emprego == null) {
					p.sendMessage(" ");
					p.sendMessage("§b" + p.getName() + "§8 » §cComo vou pedir as contas sem trabalho...!?");
					p.sendMessage("§b" + p.getName() + "§8 » §cVou procurar um trabalho");
					p.sendMessage(" ");
					return;
				}
				p.sendMessage(" ");
				p.sendMessage("§bChefe§8 » §eAgora você está livre do trabalho!");
				p.sendMessage("§bChefe§8 » §eAgora você pode arranjar outro emprego!");
				p.sendMessage(" ");

			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(TItem.Minerador.getItemMeta().getDisplayName())) {

				if (emprego != null) {
					p.sendMessage(" ");
					p.sendMessage("§cVocê já trabalha de: §f" + emprego + "§c!");
					p.sendMessage("§cPara arranjar outro empreago primeiro pessa as contas.");
					p.closeInventory();
					p.sendMessage(" ");
				} else {
					p.sendMessage(" ");
					p.sendMessage("§fAgora você trabalha como minerador!");
					p.sendMessage("§fSe quiser pedir as contas digite §c/spawn§f e procure o NPC §c§lTrabalhos");
					p.sendMessage(" ");
					Main.getInstance().config.set("Trabalhadores." + p.getName().toLowerCase(), "Minerador");
					Main.getInstance().config.saveConfig();
					p.closeInventory();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(TItem.Assassino.getItemMeta().getDisplayName())) {

				if (emprego != null) {
					p.sendMessage(" ");
					p.sendMessage("§cVocê já trabalha de: §f" + emprego + "§c!");
					p.sendMessage("§cPara arranjar outro empreago primeiro pessa as contas.");
					p.closeInventory();
					p.sendMessage(" ");
				} else {
					p.sendMessage(" ");
					p.sendMessage("§fAgora você trabalha como assassino!");
					p.sendMessage("§fSe quiser pedir as contas digite §c/spawn§f e procure o NPC §c§lTrabalhos");
					p.sendMessage(" ");
					Main.getInstance().config.set("Trabalhadores." + p.getName().toLowerCase(), "Assassino");
					Main.getInstance().config.saveConfig();
					p.closeInventory();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(TItem.Policial.getItemMeta().getDisplayName())) {

				if (emprego != null) {
					p.sendMessage(" ");
					p.sendMessage("§cVocê já trabalha de: §f" + emprego + "§c!");
					p.sendMessage("§cPara arranjar outro empreago primeiro pessa as contas.");
					p.closeInventory();
					p.sendMessage(" ");
				} else {
					p.sendMessage(" ");
					p.sendMessage("§fAgora você trabalha como policial!");
					p.sendMessage("§fSe quiser pedir as contas digite §c/spawn§f e procure o NPC §c§lTrabalhos");
					p.sendMessage(" ");
					Main.getInstance().config.set("Trabalhadores." + p.getName().toLowerCase(), "Policial");
					Main.getInstance().config.saveConfig();
					p.closeInventory();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(TItem.Empresario.getItemMeta().getDisplayName())) {

				if (emprego != null) {
					p.sendMessage(" ");
					p.sendMessage("§cVocê já trabalha de: §f" + emprego + "§c!");
					p.sendMessage("§cPara arranjar outro empreago primeiro pessa as contas.");
					p.closeInventory();
					p.sendMessage(" ");
				} else {
					p.sendMessage(" ");
					p.sendMessage("§fAgora você trabalha como empresario!");
					p.sendMessage("§fSe quiser pedir as contas digite §c/spawn§f e procure o NPC §c§lTrabalhos");
					p.sendMessage(" ");
					Main.getInstance().config.set("Trabalhadores." + p.getName().toLowerCase(), "Empresario");
					Main.getInstance().config.saveConfig();
					p.closeInventory();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(TItem.Caçador.getItemMeta().getDisplayName())) {

				if (emprego != null) {
					p.sendMessage(" ");
					p.sendMessage("§cVocê já trabalha de: §f" + emprego + "§c!");
					p.sendMessage("§cPara arranjar outro empreago primeiro pessa as contas.");
					p.closeInventory();
					p.sendMessage(" ");
				} else {
					p.sendMessage(" ");
					p.sendMessage("§fAgora você trabalha como caçador!");
					p.sendMessage("§fSe quiser pedir as contas digite §c/spawn§f e procure o NPC §c§lTrabalhos");
					p.sendMessage(" ");
					Main.getInstance().config.set("Trabalhadores." + p.getName().toLowerCase(), "Cacador");
					Main.getInstance().config.saveConfig();
					p.closeInventory();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(TItem.Escavador.getItemMeta().getDisplayName())) {

				if (emprego != null) {
					p.sendMessage(" ");
					p.sendMessage("§cVocê já trabalha de: §f" + emprego + "§c!");
					p.sendMessage("§cPara arranjar outro empreago primeiro pessa as contas.");
					p.closeInventory();
					p.sendMessage(" ");
				} else {
					p.sendMessage(" ");
					p.sendMessage("§fAgora você trabalha como escavador!");
					p.sendMessage("§fSe quiser pedir as contas digite §c/spawn§f e procure o NPC §c§lTrabalhos");
					p.sendMessage(" ");
					Main.getInstance().config.set("Trabalhadores." + p.getName().toLowerCase(), "Escavador");
					Main.getInstance().config.saveConfig();
					p.closeInventory();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(TItem.Fazendeiro.getItemMeta().getDisplayName())) {

				if (emprego != null) {
					p.sendMessage(" ");
					p.sendMessage("§cVocê já trabalha de: §f" + emprego + "§c!");
					p.sendMessage("§cPara arranjar outro empreago primeiro pessa as contas.");
					p.closeInventory();
					p.sendMessage(" ");
				} else {
					p.sendMessage(" ");
					p.sendMessage("§fAgora você trabalha como fazendeiro!");
					p.sendMessage("§fSe quiser pedir as contas digite §c/spawn§f e procure o NPC §c§lTrabalhos");
					p.sendMessage(" ");
					Main.getInstance().config.set("Trabalhadores." + p.getName().toLowerCase(), "Fazendeiro");
					Main.getInstance().config.saveConfig();
					p.closeInventory();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equalsIgnoreCase(TItem.Lenhador.getItemMeta().getDisplayName())) {

				if (emprego != null) {
					p.sendMessage(" ");
					p.sendMessage("§cVocê já trabalha de: §f" + emprego + "§c!");
					p.sendMessage("§cPara arranjar outro empreago primeiro pessa as contas.");
					p.closeInventory();
					p.sendMessage(" ");
				} else {
					p.sendMessage(" ");
					p.sendMessage("§fAgora você trabalha como lenhador!");
					p.sendMessage("§fSe quiser pedir as contas digite §c/spawn§f e procure o NPC §c§lTrabalhos");
					p.sendMessage(" ");
					Main.getInstance().config.set("Trabalhadores." + p.getName().toLowerCase(), "Lenhador");
					Main.getInstance().config.saveConfig();
					p.closeInventory();
				}
			}
		}

	}

}
