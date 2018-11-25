package net.dev.art.core.objects;

import java.awt.MenuItem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ArtMenu {
	private final String name;
	private MenuItem[] items;

	public ArtMenu(String name, int size) {
		this.name = name;
		items = new MenuItem[size];
	}

	public ArtMenu(String name, MenuSize size) {
		this.name = name;
		items = new MenuItem[size.getSlots()];
	}

	public static void register(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(new Listener() {
			@EventHandler
			private void onInvClick(InventoryClickEvent e) {
				if (!e.isCancelled() && e.getInventory().getHolder() instanceof MenuHolder)
					((MenuHolder) e.getInventory().getHolder()).getMenu().inventoryClick(e);
			}
		}, plugin);
	}

	public void setItem(int slot, ItemStack itemStack) {
		setItem(slot, itemStack, null);
	}

	public void setItem(int slot, ItemStack itemStack, MenuItemClick itemClick) {
		setItem(slot, new MenuItem(itemStack, itemClick));
	}

	public void setItem(int slot, MenuItem menuItem) {
		items[slot] = menuItem;
	}

	public void open(HumanEntity humanEntity) {
		MenuHolder holder = new MenuHolder(this);
		Inventory inventory = Bukkit.createInventory(holder, items.length, name);
		holder.setInventory(inventory);

		for (int i = 0; i < this.items.length; ++i) {
			if (this.items[i] != null)
				inventory.setItem(i, this.items[i].getIcon());
			else
				inventory.setItem(i, new ItemStack(Material.AIR));
		}

		humanEntity.openInventory(inventory);
	}

	private void inventoryClick(InventoryClickEvent e) {
		e.setCancelled(true);
		int slot = e.getRawSlot();
		if (slot > 0 && slot < items.length && items[slot] != null)
			items[slot].onClick(e);
	}

	public enum MenuSize {
		ONE_LINE(9), TWO_LINES(18), THREE_LINES(27), FOUR_LINES(36), FIVE_LINES(45), SIX_LINES(54);

		private final int slots;

		MenuSize(int slots) {
			this.slots = slots;
		}

		public int getSlots() {
			return slots;
		}
	}

	public interface MenuItemClick {
		void onClick(InventoryClickEvent e);
	}

	public MenuItem newMenuItem(ItemStack icon, MenuItemClick itemClick) {
		return new MenuItem(icon, itemClick);
	}

	public static class MenuItem {
		private final ItemStack icon;
		private final MenuItemClick itemClick;

		MenuItem(ItemStack icon, MenuItemClick itemClick) {
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
		private final ArtMenu menu;
		private Inventory inventory;

		MenuHolder(ArtMenu menu) {
			this.menu = menu;
		}

		ArtMenu getMenu() {
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
