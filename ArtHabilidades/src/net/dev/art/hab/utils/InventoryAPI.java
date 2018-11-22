package net.dev.art.hab.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class InventoryAPI {

	public static int getSlot(Inventory inv) {
		if (inv.getItem(10) == null || inv.getItem(10).getType() == Material.AIR) return 10;
		if (inv.getItem(11) == null || inv.getItem(11).getType() == Material.AIR) return 11;
		if (inv.getItem(12) == null || inv.getItem(12).getType() == Material.AIR) return 12;
		if (inv.getItem(13) == null || inv.getItem(13).getType() == Material.AIR) return 13;
		if (inv.getItem(14) == null || inv.getItem(14).getType() == Material.AIR) return 14;
		if (inv.getItem(15) == null || inv.getItem(15).getType() == Material.AIR) return 15;
		if (inv.getItem(16) == null || inv.getItem(16).getType() == Material.AIR) return 16;
		if (inv.getItem(19) == null || inv.getItem(19).getType() == Material.AIR) return 19;
		if (inv.getItem(20) == null || inv.getItem(20).getType() == Material.AIR) return 20;
		if (inv.getItem(21) == null || inv.getItem(21).getType() == Material.AIR) return 21;
		if (inv.getItem(22) == null || inv.getItem(22).getType() == Material.AIR) return 22;
		if (inv.getItem(23) == null || inv.getItem(23).getType() == Material.AIR) return 23;
		
		
		if (inv.getItem(24) == null || inv.getItem(24).getType() == Material.AIR) return 24;
		if (inv.getItem(25) == null || inv.getItem(25).getType() == Material.AIR) return 25;
		if (inv.getItem(26) == null || inv.getItem(26).getType() == Material.AIR) return 26;
		if (inv.getItem(27) == null || inv.getItem(27).getType() == Material.AIR) return 27;
		if (inv.getItem(28) == null || inv.getItem(28).getType() == Material.AIR) return 28;
		if (inv.getItem(29) == null || inv.getItem(29).getType() == Material.AIR) return 29;
		if (inv.getItem(30) == null || inv.getItem(30).getType() == Material.AIR) return 30;
		if (inv.getItem(31) == null || inv.getItem(31).getType() == Material.AIR) return 31;
		if (inv.getItem(32) == null || inv.getItem(32).getType() == Material.AIR) return 32;
		if (inv.getItem(33) == null || inv.getItem(33).getType() == Material.AIR) return 33;
		if (inv.getItem(34) == null || inv.getItem(34).getType() == Material.AIR) return 34;
		if (inv.getItem(35) == null || inv.getItem(35).getType() == Material.AIR) return 35;
		if (inv.getItem(36) == null || inv.getItem(36).getType() == Material.AIR) return 36;
		if (inv.getItem(37) == null || inv.getItem(37).getType() == Material.AIR) return 37;
		if (inv.getItem(38) == null || inv.getItem(38).getType() == Material.AIR) return 38;
		if (inv.getItem(39) == null || inv.getItem(39).getType() == Material.AIR) return 39;
		if (inv.getItem(40) == null || inv.getItem(40).getType() == Material.AIR) return 40;
		if (inv.getItem(41) == null || inv.getItem(41).getType() == Material.AIR) return 41;
		if (inv.getItem(42) == null || inv.getItem(42).getType() == Material.AIR) return 42;
		if (inv.getItem(43) == null || inv.getItem(43).getType() == Material.AIR) return 43;
		return -1;
	}
	
}
