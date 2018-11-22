package net.dev.rpg.APIs;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.dev.rpg.Main;

public class ClassesAPI {

	public static ItemStack newItem(Material m, String name, String... lore) {
		ItemStack i = new ItemStack(m);
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		i.setItemMeta(meta);
		return i;
	}

	public static ItemStack newSkull(String owner, String name, String... lore) {
		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) i.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		meta.setOwner(owner);
		i.setItemMeta(meta);
		return i;
	}

	public static void SeletorDeClasses(Player p) {
		Inventory menu = Bukkit.createInventory(null, 45, "§6§lSeletor De Classe");

		menu.setItem(10, newItem(Material.POTION, "§b§lMAGO",
				"§eEspecialista Em Magias e Poçoes... Pode Preparar Poçoes Especiais. (Speed 3, Haste 2, Saturation) "));
		menu.setItem(12, newItem(Material.DIAMOND_SWORD, "§b§lGUERREIRO",
				"§eEspecialista Em Espadas e Machados... Podem Dar Mais Dano Que o Normal."));
		menu.setItem(14, newItem(Material.BLAZE_ROD, "§b§lPYROMANCER",
				"§eEspecialista Em Fogo Nao Brique Com Ele Ou Se Queimara... Sua Varinha Taca Fogo e Encedeia Seus Inimigos"));
		menu.setItem(16, newItem(Material.BOW, "§b§lARQUEIRO", "§eEspecialista Em Arcos..."));
		menu.setItem(28,
				newItem(Material.BONE, "§b§lNECROMANCER", "§eEspecialista Em Mobs... Cria Seus Mobs 'SERVOS'"));
		menu.setItem(30, newItem(Material.WATER_BUCKET, "§b§lHYDROMANCER",
				"§eEspecialista Em Agua Nao Brique Com Ele Ou Se Afogara... Sua Varinha Taca Agua e Afoga Seus Inimigos"));
		menu.setItem(32, newItem(Material.GRASS, "§b§lGEOMANCER",
				"§eEspecialista Em Terra Nao Brique Com Ele Ou Se Arrependra... Sua Varinha Manipula Terra e Manipula Seus Inimigos"));
		menu.setItem(34, newItem(Material.FEATHER, "§b§lAEROMANCER",
				"§eEspecialista Em Ar Nao Brique Com Ele Ou Se Arrependra... Sua Varinha Manipula Ar e Manipula Seus Inimigos"));

		p.openInventory(menu);

	}

	public static void setClasse(Player p, String c) {
		Main.d.set(p.getName().toLowerCase() + ".Classe", c);
		Main.getInstance().saveData();
	}

	public static boolean hasClasse(Player p) {
		return Main.d.contains(p.getName().toLowerCase() + ".Classe");
	}
	
	public static String getClasse(Player p) {
		return Main.d.getString(p.getName().toLowerCase() + ".Classe");
	}
	
	public static void resetClasse(Player p, String c) {
		Main.d.set(p.getName().toLowerCase() + ".Classe", "NENHUMA");
		Main.d.set(p.getName().toLowerCase() + ".Classe", c);
		Main.getInstance().saveData();
	}
	
	
	
}
