package net.dev.art.core.objects;

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

public class ArtInventory {

	private String title;
	private int size;
	private InventoryItem[] items;

	public ArtInventory(String title, int size) {
		this.title = title;
		this.items = new InventoryItem[size];
	}

	private void inventoryClick(InventoryClickEvent e) {
		e.setCancelled(true);
		int slot = e.getRawSlot();
		if (slot > 0 && slot < items.length && items[slot] != null)
			items[slot].onClick(e);
	}

	public void register(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(new Listener() {
			@EventHandler
			private void onInvClick(InventoryClickEvent e) {
				if (!e.isCancelled() && e.getInventory().getHolder() instanceof ArtInventoryHolder) {
					ArtInventoryHolder ArtInventoryHolder = (ArtInventoryHolder) e.getInventory().getHolder();
					ArtInventoryHolder.getArtInventory().inventoryClick(e);
				}
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

	public InventoryItem[] getItems() {
		return items;
	}

	public void open(HumanEntity p) {
		ArtInventoryHolder holder = new ArtInventoryHolder(this);
		Inventory inventory = Bukkit.createInventory(holder, items.length, title);
		holder.setInventory(inventory);

		for (int i = 0; i < this.items.length; ++i) {
			if (this.items[i] != null)
				inventory.setItem(i, this.items[i].getIcon());
			else
				inventory.setItem(i, new ItemStack(Material.AIR));
		}

		p.openInventory(inventory);
	}

	private class ArtInventoryHolder implements InventoryHolder {
		private final ArtInventory menu;
		private Inventory inventory;

		ArtInventoryHolder(ArtInventory menu) {
			this.menu = menu;
		}

		ArtInventory getArtInventory() {
			return menu;
		}

		public Inventory getInventory() {
			return this.inventory;
		}

		void setInventory(Inventory inventory) {
			this.inventory = inventory;
		}
	}

	public String getTitle() {
		return title;
	}

	public int getSize() {
		return size;
	}

	public interface MenuItemClick {
		void onClick(InventoryClickEvent e);
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

}
