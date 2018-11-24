package net.dev.art.core.objects;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dev.art.core.utils.EnchantGlow;

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

	public ArtItem Glowing(boolean b) {
		if (b) {
			EnchantGlow.addGlow(this);
		}else {
			EnchantGlow.removeGlow(this);
		}
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
