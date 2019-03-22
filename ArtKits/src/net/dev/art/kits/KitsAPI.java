package net.dev.art.kits;

import org.bukkit.enchantments.Enchantment;

public class KitsAPI {

	public static Enchantment traduzirEncantamento(final String e) {
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
