package net.dev.art.shop.objects;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class ShopItem {

	private double CPrice, VPrice = 0.0;
	private boolean buyable = true;
	private ItemStack item = new ItemStack(Material.BARRIER);

	public ShopItem(double C, double V, boolean buy, ItemStack item) {
		this.item = item;
		this.CPrice = C;
		this.VPrice = V;
		this.buyable = buy;
	}
	
	
	
	public double getCPrice() {
		return CPrice;
	}
	
	public double getVPrice() {
		return VPrice;
	}
	public ItemStack getItem() {
		return item;
	}
	
	public boolean isBuyable() {
		return buyable;
	}

}
