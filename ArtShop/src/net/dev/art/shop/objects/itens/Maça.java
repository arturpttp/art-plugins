package net.dev.art.shop.objects.itens;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import net.dev.art.shop.objects.ShopItem;

public class Maça extends ShopItem {

	public Maça() {
		super(1000D, 1000D, true, new ItemStack(Material.APPLE));
	}

}
