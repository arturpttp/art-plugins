package net.dev.art.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.dev.art.core.ArtCore;
import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.objects.ArtItem;
import net.dev.art.core.objects.ArtItem.ArtItemClick;
import net.dev.art.core.objects.FloatItem;

public class SetFloatItem extends ArtCommand {

	public SetFloatItem() {
		super("coreteste");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!isPlayer(sender)) {
			return true;
		} else {
			Player p = (Player) sender;
			Inventory inv = Bukkit.createInventory(null, 9, "§cinventory");
			ArtItem item = new ArtItem(Material.ITEM_FRAME);
			item.nome("§aNome");
			item.lore("§cALO");
			Listener listener = new Listener() {
				@EventHandler
				public void onClick(InventoryClickEvent e) {
					e.setCancelled(true);
					e.getWhoClicked().sendMessage("aaa");
				}
			};
			item.onClick(listener);
			Bukkit.getPluginManager().registerEvents(listener, ArtCore.getInstance());
			inv.setItem(4, item);
			p.openInventory(inv);

		}
		return false;
	}

}
