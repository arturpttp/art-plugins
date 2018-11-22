package net.dev.art.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.ItemsAPI;
import net.dev.art.essentials.utils.Mensagens;

public class KitsManager extends Mensagens implements Listener, CommandExecutor {

	void sendKitEntregue(Player p, String kit) {
		mensagem(p, "§eKit `§b" + kit + "§e` Entregue");
	}

	void AbrirKits(Player p) {
		Inventory inv = Bukkit.createInventory(p, 45, "§bKits");

		inv.setItem(10, ItemsAPI.add(Material.IRON_INGOT, "§bKit §7Ferro §bNoob", "§eKit De §7Ferro §eLiso",
				"§eClique (Direito) Coletar"));
		inv.setItem(19, ItemsAPI.add(Material.IRON_ORE, "§bKit §7Ferro", "§eKit De §7Ferro",
				"§eClique (Direito) Coletar"));

		p.openInventory(inv);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("kits")) {
			if (!p.hasPermission("kits.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}
			AbrirKits(p);

		}
		return false;
	}

	@EventHandler
	void onKitsClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		if (!inv.getTitle().equalsIgnoreCase("§bKits"))
			return;
		if (inv.getTitle().equalsIgnoreCase("§bKits"))
			e.setCancelled(true);
		if (e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem() == null)
			return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bKit §7Ferro §bNoob")) {
			p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
			p.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
			p.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
			p.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
			p.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
			p.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
			p.getInventory().addItem(new ItemStack(Material.IRON_AXE));
			p.getInventory().addItem(new ItemStack(Material.IRON_SPADE));
			p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 5));
			p.closeInventory();
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			sendKitEntregue(p, "Ferro Noob");
		} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bKit §7Ferro")) {

			p.getInventory().addItem(ItemsAPI.add(Material.IRON_SWORD, Enchantment.DAMAGE_ALL, 1));
			p.getInventory().addItem(ItemsAPI.add(Material.IRON_HELMET, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
			p.getInventory().addItem(ItemsAPI.add(Material.IRON_CHESTPLATE, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
			p.getInventory().addItem(ItemsAPI.add(Material.IRON_LEGGINGS, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
			p.getInventory().addItem(ItemsAPI.add(Material.IRON_BOOTS, Enchantment.PROTECTION_ENVIRONMENTAL, 1));
			p.getInventory().addItem(ItemsAPI.add(Material.IRON_PICKAXE, Enchantment.DIG_SPEED, 1));
			p.getInventory().addItem(ItemsAPI.add(Material.IRON_AXE, Enchantment.DIG_SPEED, 1));
			p.getInventory().addItem(ItemsAPI.add(Material.IRON_SPADE, Enchantment.DIG_SPEED, 1));
			p.getInventory().addItem(ItemsAPI.cap(Material.GOLDEN_APPLE, 1));
			p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 5));
			p.closeInventory();
			sendKitEntregue(p, "Ferro");
		}

	}

}
