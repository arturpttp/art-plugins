package net.dev.art.core.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Inventario {
	Inventory inventory;
	int size;

	public Inventario(int size, String title) {
		this.size = size;
		this.inventory = Bukkit.createInventory(null, getSize(), title);
	}

	public int getSize() {
		return size;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void open(Player p) {
		p.openInventory(inventory);
	}

	public void removeItem(int slot) {
		ItemStack item = new ItemBuilder(Material.AIR).toItemStack();
		inventory.setItem(slot, item);
	}

	public void encher(ItemStack item) {
		for (int i = 0; i < getSize(); i++) {
			inventory.setItem(i, item);
		}
	}

	public int getNumberOfItem(ItemStack item) {
		final ItemStack[] items = getInventory().getContents();
		int has = 0;
		for (final ItemStack itm : items) {
			if (itm != null && itm.getType() == item.getType() && itm.getAmount() > 0
					&& itm.getDurability() == item.getDurability()) {
				has += itm.getAmount();
			}
		}
		return has;
	}

}
