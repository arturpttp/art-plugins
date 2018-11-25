package net.dev.art.essentials.apis;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.bukkit.enchantments.Enchantment;

public class Enchantments {

	private static final Map<String, Enchantment> ENCHANTMENTS;
	private static final Map<String, Enchantment> ALIASENCHANTMENTS;

	public static Enchantment getByName(final String name) {
		Enchantment enchantment;
		if (NumbersAPI.isInt(name)) {
			enchantment = Enchantment.getById(Integer.parseInt(name));
		} else {
			enchantment = Enchantment.getByName(name.toUpperCase(Locale.ENGLISH));
		}
		if (enchantment == null) {
			enchantment = Enchantments.ENCHANTMENTS.get(name.toLowerCase(Locale.ENGLISH));
		}
		if (enchantment == null) {
			enchantment = Enchantments.ALIASENCHANTMENTS.get(name.toLowerCase(Locale.ENGLISH));
		}
		return enchantment;
	}

	public static Set<Map.Entry<String, Enchantment>> entrySet() {
		return Enchantments.ENCHANTMENTS.entrySet();
	}

	static {
		ENCHANTMENTS = new HashMap<String, Enchantment>();
		ALIASENCHANTMENTS = new HashMap<String, Enchantment>();
		Enchantments.ENCHANTMENTS.put("alldamage", Enchantment.DAMAGE_ALL);
		Enchantments.ALIASENCHANTMENTS.put("alldmg", Enchantment.DAMAGE_ALL);
		Enchantments.ENCHANTMENTS.put("sharpness", Enchantment.DAMAGE_ALL);
		Enchantments.ALIASENCHANTMENTS.put("sharp", Enchantment.DAMAGE_ALL);
		Enchantments.ALIASENCHANTMENTS.put("dal", Enchantment.DAMAGE_ALL);
		Enchantments.ENCHANTMENTS.put("ardmg", Enchantment.DAMAGE_ARTHROPODS);
		Enchantments.ENCHANTMENTS.put("baneofarthropods", Enchantment.DAMAGE_ARTHROPODS);
		Enchantments.ALIASENCHANTMENTS.put("baneofarthropod", Enchantment.DAMAGE_ARTHROPODS);
		Enchantments.ALIASENCHANTMENTS.put("arthropod", Enchantment.DAMAGE_ARTHROPODS);
		Enchantments.ALIASENCHANTMENTS.put("dar", Enchantment.DAMAGE_ARTHROPODS);
		Enchantments.ENCHANTMENTS.put("undeaddamage", Enchantment.DAMAGE_UNDEAD);
		Enchantments.ENCHANTMENTS.put("smite", Enchantment.DAMAGE_UNDEAD);
		Enchantments.ALIASENCHANTMENTS.put("du", Enchantment.DAMAGE_UNDEAD);
		Enchantments.ENCHANTMENTS.put("digspeed", Enchantment.DIG_SPEED);
		Enchantments.ENCHANTMENTS.put("efficiency", Enchantment.DIG_SPEED);
		Enchantments.ALIASENCHANTMENTS.put("minespeed", Enchantment.DIG_SPEED);
		Enchantments.ALIASENCHANTMENTS.put("cutspeed", Enchantment.DIG_SPEED);
		Enchantments.ALIASENCHANTMENTS.put("ds", Enchantment.DIG_SPEED);
		Enchantments.ALIASENCHANTMENTS.put("eff", Enchantment.DIG_SPEED);
		Enchantments.ENCHANTMENTS.put("durability", Enchantment.DURABILITY);
		Enchantments.ALIASENCHANTMENTS.put("dura", Enchantment.DURABILITY);
		Enchantments.ENCHANTMENTS.put("unbreaking", Enchantment.DURABILITY);
		Enchantments.ALIASENCHANTMENTS.put("d", Enchantment.DURABILITY);
		Enchantments.ENCHANTMENTS.put("thorns", Enchantment.THORNS);
		Enchantments.ENCHANTMENTS.put("highcrit", Enchantment.THORNS);
		Enchantments.ALIASENCHANTMENTS.put("thorn", Enchantment.THORNS);
		Enchantments.ALIASENCHANTMENTS.put("highercrit", Enchantment.THORNS);
		Enchantments.ALIASENCHANTMENTS.put("t", Enchantment.THORNS);
		Enchantments.ENCHANTMENTS.put("fireaspect", Enchantment.FIRE_ASPECT);
		Enchantments.ENCHANTMENTS.put("fire", Enchantment.FIRE_ASPECT);
		Enchantments.ALIASENCHANTMENTS.put("meleefire", Enchantment.FIRE_ASPECT);
		Enchantments.ALIASENCHANTMENTS.put("meleeflame", Enchantment.FIRE_ASPECT);
		Enchantments.ALIASENCHANTMENTS.put("fa", Enchantment.FIRE_ASPECT);
		Enchantments.ENCHANTMENTS.put("knockback", Enchantment.KNOCKBACK);
		Enchantments.ALIASENCHANTMENTS.put("kback", Enchantment.KNOCKBACK);
		Enchantments.ALIASENCHANTMENTS.put("kb", Enchantment.KNOCKBACK);
		Enchantments.ALIASENCHANTMENTS.put("k", Enchantment.KNOCKBACK);
		Enchantments.ALIASENCHANTMENTS.put("blockslootbonus", Enchantment.LOOT_BONUS_BLOCKS);
		Enchantments.ENCHANTMENTS.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
		Enchantments.ALIASENCHANTMENTS.put("fort", Enchantment.LOOT_BONUS_BLOCKS);
		Enchantments.ALIASENCHANTMENTS.put("lbb", Enchantment.LOOT_BONUS_BLOCKS);
		Enchantments.ALIASENCHANTMENTS.put("mobslootbonus", Enchantment.LOOT_BONUS_MOBS);
		Enchantments.ENCHANTMENTS.put("mobloot", Enchantment.LOOT_BONUS_MOBS);
		Enchantments.ENCHANTMENTS.put("looting", Enchantment.LOOT_BONUS_MOBS);
		Enchantments.ALIASENCHANTMENTS.put("lbm", Enchantment.LOOT_BONUS_MOBS);
		Enchantments.ALIASENCHANTMENTS.put("oxygen", Enchantment.OXYGEN);
		Enchantments.ENCHANTMENTS.put("respiration", Enchantment.OXYGEN);
		Enchantments.ALIASENCHANTMENTS.put("breathing", Enchantment.OXYGEN);
		Enchantments.ENCHANTMENTS.put("breath", Enchantment.OXYGEN);
		Enchantments.ALIASENCHANTMENTS.put("o", Enchantment.OXYGEN);
		Enchantments.ENCHANTMENTS.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
		Enchantments.ALIASENCHANTMENTS.put("prot", Enchantment.PROTECTION_ENVIRONMENTAL);
		Enchantments.ENCHANTMENTS.put("protect", Enchantment.PROTECTION_ENVIRONMENTAL);
		Enchantments.ALIASENCHANTMENTS.put("p", Enchantment.PROTECTION_ENVIRONMENTAL);
		Enchantments.ALIASENCHANTMENTS.put("explosionsprotection", Enchantment.PROTECTION_EXPLOSIONS);
		Enchantments.ALIASENCHANTMENTS.put("explosionprotection", Enchantment.PROTECTION_EXPLOSIONS);
		Enchantments.ALIASENCHANTMENTS.put("expprot", Enchantment.PROTECTION_EXPLOSIONS);
		Enchantments.ALIASENCHANTMENTS.put("blastprotection", Enchantment.PROTECTION_EXPLOSIONS);
		Enchantments.ALIASENCHANTMENTS.put("bprotection", Enchantment.PROTECTION_EXPLOSIONS);
		Enchantments.ALIASENCHANTMENTS.put("bprotect", Enchantment.PROTECTION_EXPLOSIONS);
		Enchantments.ENCHANTMENTS.put("blastprotect", Enchantment.PROTECTION_EXPLOSIONS);
		Enchantments.ALIASENCHANTMENTS.put("pe", Enchantment.PROTECTION_EXPLOSIONS);
		Enchantments.ALIASENCHANTMENTS.put("fallprotection", Enchantment.PROTECTION_FALL);
		Enchantments.ENCHANTMENTS.put("fallprot", Enchantment.PROTECTION_FALL);
		Enchantments.ENCHANTMENTS.put("featherfall", Enchantment.PROTECTION_FALL);
		Enchantments.ALIASENCHANTMENTS.put("featherfalling", Enchantment.PROTECTION_FALL);
		Enchantments.ALIASENCHANTMENTS.put("pfa", Enchantment.PROTECTION_FALL);
		Enchantments.ALIASENCHANTMENTS.put("fireprotection", Enchantment.PROTECTION_FIRE);
		Enchantments.ALIASENCHANTMENTS.put("flameprotection", Enchantment.PROTECTION_FIRE);
		Enchantments.ENCHANTMENTS.put("fireprotect", Enchantment.PROTECTION_FIRE);
		Enchantments.ALIASENCHANTMENTS.put("flameprotect", Enchantment.PROTECTION_FIRE);
		Enchantments.ENCHANTMENTS.put("fireprot", Enchantment.PROTECTION_FIRE);
		Enchantments.ALIASENCHANTMENTS.put("flameprot", Enchantment.PROTECTION_FIRE);
		Enchantments.ALIASENCHANTMENTS.put("pf", Enchantment.PROTECTION_FIRE);
		Enchantments.ENCHANTMENTS.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
		Enchantments.ENCHANTMENTS.put("projprot", Enchantment.PROTECTION_PROJECTILE);
		Enchantments.ALIASENCHANTMENTS.put("pp", Enchantment.PROTECTION_PROJECTILE);
		Enchantments.ENCHANTMENTS.put("silktouch", Enchantment.SILK_TOUCH);
		Enchantments.ALIASENCHANTMENTS.put("softtouch", Enchantment.SILK_TOUCH);
		Enchantments.ALIASENCHANTMENTS.put("st", Enchantment.SILK_TOUCH);
		Enchantments.ENCHANTMENTS.put("waterworker", Enchantment.WATER_WORKER);
		Enchantments.ENCHANTMENTS.put("aquaaffinity", Enchantment.WATER_WORKER);
		Enchantments.ALIASENCHANTMENTS.put("watermine", Enchantment.WATER_WORKER);
		Enchantments.ALIASENCHANTMENTS.put("ww", Enchantment.WATER_WORKER);
		Enchantments.ALIASENCHANTMENTS.put("firearrow", Enchantment.ARROW_FIRE);
		Enchantments.ENCHANTMENTS.put("flame", Enchantment.ARROW_FIRE);
		Enchantments.ENCHANTMENTS.put("flamearrow", Enchantment.ARROW_FIRE);
		Enchantments.ALIASENCHANTMENTS.put("af", Enchantment.ARROW_FIRE);
		Enchantments.ENCHANTMENTS.put("arrowdamage", Enchantment.ARROW_DAMAGE);
		Enchantments.ENCHANTMENTS.put("power", Enchantment.ARROW_DAMAGE);
		Enchantments.ALIASENCHANTMENTS.put("arrowpower", Enchantment.ARROW_DAMAGE);
		Enchantments.ALIASENCHANTMENTS.put("ad", Enchantment.ARROW_DAMAGE);
		Enchantments.ENCHANTMENTS.put("arrowknockback", Enchantment.ARROW_KNOCKBACK);
		Enchantments.ALIASENCHANTMENTS.put("arrowkb", Enchantment.ARROW_KNOCKBACK);
		Enchantments.ENCHANTMENTS.put("punch", Enchantment.ARROW_KNOCKBACK);
		Enchantments.ALIASENCHANTMENTS.put("arrowpunch", Enchantment.ARROW_KNOCKBACK);
		Enchantments.ALIASENCHANTMENTS.put("ak", Enchantment.ARROW_KNOCKBACK);
		Enchantments.ALIASENCHANTMENTS.put("infinitearrows", Enchantment.ARROW_INFINITE);
		Enchantments.ENCHANTMENTS.put("infarrows", Enchantment.ARROW_INFINITE);
		Enchantments.ENCHANTMENTS.put("infinity", Enchantment.ARROW_INFINITE);
		Enchantments.ALIASENCHANTMENTS.put("infinite", Enchantment.ARROW_INFINITE);
		Enchantments.ALIASENCHANTMENTS.put("unlimited", Enchantment.ARROW_INFINITE);
		Enchantments.ALIASENCHANTMENTS.put("unlimitedarrows", Enchantment.ARROW_INFINITE);
		Enchantments.ALIASENCHANTMENTS.put("ai", Enchantment.ARROW_INFINITE);
		Enchantments.ENCHANTMENTS.put("luck", Enchantment.LUCK);
		Enchantments.ALIASENCHANTMENTS.put("luckofsea", Enchantment.LUCK);
		Enchantments.ALIASENCHANTMENTS.put("luckofseas", Enchantment.LUCK);
		Enchantments.ALIASENCHANTMENTS.put("rodluck", Enchantment.LUCK);
		Enchantments.ENCHANTMENTS.put("lure", Enchantment.LURE);
		Enchantments.ALIASENCHANTMENTS.put("rodlure", Enchantment.LURE);
	}

}
