package net.dev.art.api.APIs;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArtItem extends ItemStack {

	public ArtItem(Material type,int amount,short data) {
		super(type,amount,data);
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
