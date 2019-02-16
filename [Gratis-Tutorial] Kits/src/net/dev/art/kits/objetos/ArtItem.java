package net.dev.art.kits.objetos;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ArtItem extends ItemStack {

	public ArtItem(Material type) {
		super(type);
	}

	public ArtItem setAmout(int amount) {
		this.setAmount(amount);
		return this;
	}

	public ArtItem setData(short data) {
		this.setDurability(data);
		return this;
	}

	public ArtItem lore(String... strings) {
		ItemMeta itemMeta = this.getItemMeta();
		itemMeta.setLore(Arrays.asList(strings));
		this.setItemMeta(itemMeta);
		return this;
	}

	public ArtItem nome(String string) {
		ItemMeta itemMeta = this.getItemMeta();
		itemMeta.setDisplayName(string);
		this.setItemMeta(itemMeta);
		return this;
	}

	public ArtItem encantamento(Enchantment enc, int level) {
		this.addUnsafeEnchantment(enc, level);
		return this;
	}

}
