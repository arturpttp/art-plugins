package net.dev.art.factions.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Menu {

	private String title;
	private int size;
	private InventoryItem[] items;
	private Inventory inventory;

	public Menu(int size, String title) {
		MenuHolder holder = new MenuHolder(this);
		this.size = size;
		this.title = title;
		this.inventory = Bukkit.createInventory(holder, size, title);
		holder.setInventory(inventory);
	}

	public void open(HumanEntity humanEntity) {
		for (int i = 0; i < this.items.length; ++i) {
			if (this.items[i] != null)
				inventory.setItem(i, this.items[i].getIcon());
			else
				inventory.setItem(i, new ItemStack(Material.AIR));
		}
		humanEntity.openInventory(getInventory());
	}

	public void register(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(new Listener() {
			@EventHandler
			private void onInvClick(InventoryClickEvent e) {
				if (!e.isCancelled() && e.getInventory().getHolder() instanceof MenuHolder)
					((MenuHolder) e.getInventory().getHolder()).getMenu().inventoryClick(e);
			}
		}, plugin);
	}

	public void setItem(int slot, InventoryItem item) {
		items[slot] = item;
	}

	public void setItem(int slot, ItemStack itemStack, MenuItemClick itemClick) {
		setItem(slot, new InventoryItem(itemStack, itemClick));
	}

	public void setItem(int slot, ItemStack itemStack) {
		setItem(slot, new InventoryItem(itemStack, null));
	}

	private void inventoryClick(InventoryClickEvent e) {
		e.setCancelled(true);
		int slot = e.getRawSlot();
		if (slot > 0 && slot < items.length && items[slot] != null)
			items[slot].onClick(e);
	}

	public interface MenuItemClick {
		void onClick(InventoryClickEvent e);
	}

	public InventoryItem[] getItems() {
		return items;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public String getTitle() {
		return title;
	}

	public int getSize() {
		return size;
	}

	public static class InventoryItem {
		private final ItemStack icon;
		private final MenuItemClick itemClick;

		InventoryItem(ItemStack icon, MenuItemClick itemClick) {
			this.icon = icon;
			this.itemClick = itemClick;
		}

		void onClick(InventoryClickEvent e) {
			if (itemClick != null)
				itemClick.onClick(e);
		}

		ItemStack getIcon() {
			return icon;
		}
	}

	private class MenuHolder implements InventoryHolder {
		private final Menu menu;
		private Inventory inventory;

		MenuHolder(Menu menu) {
			this.menu = menu;
		}

		Menu getMenu() {
			return menu;
		}

		public Inventory getInventory() {
			return this.inventory;
		}

		void setInventory(Inventory inventory) {
			this.inventory = inventory;
		}
	}

}
