package net.dev.art.core.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class InventariosAPI {

	public int getItemAmount(Player player, Material material) {
		for (ItemStack is : player.getInventory().getContents()) {
			if (is != null && is.getType() != null && is.getType() == material) {
				for (int i = 0; i < is.getAmount(); i++)
					return i;
			}
		}
		return 0;
	}

	public int getItemAmount(Player player, ItemStack itemstack) {
		for (ItemStack is : player.getInventory().getContents()) {
			if (is != null && is.getType() != null && is.isSimilar(itemstack)) {
				for (int i = 0; i < is.getAmount(); i++)
					return i;
			}
		}
		return 0;
	}

}
