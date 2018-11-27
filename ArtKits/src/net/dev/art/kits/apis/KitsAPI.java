package net.dev.art.kits.apis;

import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.kits.Main;
import net.dev.art.kits.objects.Kit;

public class KitsAPI {

	public static void darKit(Player p, String kit) {
		for (ItemStack is : getKitByName(kit).getItens()) {
			p.getInventory().addItem(is);
		}
	}

	public static boolean canPickup(Player p, String kit) {
		if (GruposAPI.getGrupo(p.getName()) == GruposAPI.getGrupoByName(getGrupoDoKit(kit))) {
			return true;
		} else {
			return false;
		}
	}

	public static void addDelay(String p, String kit) {
		Main.d.set("Delay." + p + "." + getKitByName(kit).getName(), System.currentTimeMillis());
		Main.saveKits();
	}

	public static void removeDelay(String p, String kit) {
		Main.d.set("Delay." + p + "." + getKitByName(kit).getName(), null);
		Main.saveKits();
	}

	public static boolean existKit(String kit) {
		for (Kit k : getKits()) {
			if (k.getName().equalsIgnoreCase(kit)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isDelayed(Player p, String kit) {
		Kit k = getKitByName(kit);
		if (Main.d.getLong("Delay." + p.getName() + "." + k.getName()) <= 0) {
			return false;
		}
		if (Main.d.contains("Delay." + p.getName() + "." + k.getName())) {
			return true;
		} else {
			return false;
		}
	}

	public static Kit getKitByName(String name) {
		for (Kit k : getKits()) {
			if (k.getName().equalsIgnoreCase(name)) {
				return k;
			}
		}
		return null;
	}

	public static String getGrupoDoKit(String kit) {
		for (Kit k : getKits()) {
			if (k.getName().equalsIgnoreCase(kit)) {
				return k.getGrupo();
			}
		}
		return "membro";
	}

	public static List<Kit> getKits() {
		return Main.kits;
	}

	public static Enchantment traduzirEncantamento(String e) {
		if (e.equalsIgnoreCase("PROTECTION")) {
			return Enchantment.PROTECTION_ENVIRONMENTAL;
		}
		if (e.equalsIgnoreCase("FIREPROTECTION")) {
			return Enchantment.PROTECTION_FIRE;
		}
		if (e.equalsIgnoreCase("FEATHERFALLING")) {
			return Enchantment.PROTECTION_FALL;
		}
		if (e.equalsIgnoreCase("BLASTPROTECTION")) {
			return Enchantment.PROTECTION_EXPLOSIONS;
		}
		if (e.equalsIgnoreCase("PROJECTILEPROTECTION")) {
			return Enchantment.PROTECTION_ENVIRONMENTAL;
		}
		if (e.equalsIgnoreCase("SHARPNESS")) {
			return Enchantment.DAMAGE_ALL;
		}
		if (e.equalsIgnoreCase("KNOCKBACK")) {
			return Enchantment.KNOCKBACK;
		}
		if (e.equalsIgnoreCase("FIREASPECT")) {
			return Enchantment.FIRE_ASPECT;
		}
		if (e.equalsIgnoreCase("LOOTING")) {
			return Enchantment.LOOT_BONUS_MOBS;
		}
		if (e.equalsIgnoreCase("EFFICIENCY")) {
			return Enchantment.DIG_SPEED;
		}
		if (e.equalsIgnoreCase("SILKTOUCH")) {
			return Enchantment.SILK_TOUCH;
		}
		if (e.equalsIgnoreCase("UNBREAKING")) {
			return Enchantment.DURABILITY;
		}
		if (e.equalsIgnoreCase("FORTUNE")) {
			return Enchantment.LOOT_BONUS_BLOCKS;
		}
		return null;
	}

}
