package net.dev.art.facs.utils;

import org.bukkit.inventory.Inventory;

public class FUtils {

	public static boolean isInvFull(Inventory inv) {
		return inv.firstEmpty() == -1;
	}

	public static boolean isInvEmpty(Inventory inv) {
		return inv.firstEmpty() == 0;
	}

}
