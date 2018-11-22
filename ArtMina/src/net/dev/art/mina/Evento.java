package net.dev.art.mina;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import net.dev.art.api.APIs.ItemsAPI;
import net.dev.art.api.APIs.TitleAPI;
import net.dev.art.core.ArtItem;

public class Evento implements Listener {

	public static int gerarID() {
		return ThreadLocalRandom.current().nextInt(0, 53);
	}

	public static int lapisr() {
		return ThreadLocalRandom.current().nextInt(1, 8);
	}

	boolean isThisItem(Player p) {
		Material type = p.getItemInHand().getType();
		ItemStack is = p.getItemInHand();
		ItemStack fakePick = ItemsAPI.add(Material.DIAMOND_PICKAXE, "§ePicareta Bolada 3x3");
		if (is.isSimilar(fakePick)) {
			return false;
		}
		if (is == null || type == Material.AIR) {
			return false;
		}
		if (type == Material.DIAMOND_PICKAXE || type == Material.WOOD_PICKAXE || type == Material.GOLD_PICKAXE
				|| type == Material.STONE_PICKAXE) {
			return true;
		}

		return false;
	}

	void addInv(Player p, Material m) {
		try {
			p.getInventory().addItem(new ItemStack(m));
		} catch (Exception e) {
			p.sendMessage("§cERRO");
		}

	}

	Block bl = null;

	@EventHandler
	void Hole3x3(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();

		ItemStack type = p.getItemInHand();

		ItemStack fakePick = ItemsAPI.add(Material.DIAMOND_PICKAXE, "§ePicareta Bolada 3x3");

		if (!p.getItemInHand().isSimilar(fakePick)) {
			return;
		}

		e.setCancelled(true);

		Block b1 = b.getRelative(BlockFace.NORTH_WEST);
		Block b2 = b.getRelative(BlockFace.NORTH);
		Block b3 = b.getRelative(BlockFace.NORTH_EAST);
		Block b4 = b.getRelative(BlockFace.WEST);
		Block b5 = b.getRelative(BlockFace.EAST);
		Block b6 = b.getRelative(BlockFace.SOUTH_WEST);
		Block b7 = b.getRelative(BlockFace.SOUTH_EAST);
		Block b8 = b.getRelative(BlockFace.SOUTH);

		p.getWorld().dropItem(b1.getLocation(), new ItemStack(b1.getType()));
		p.getWorld().dropItem(b2.getLocation(), new ItemStack(b2.getType()));
		p.getWorld().dropItem(b3.getLocation(), new ItemStack(b3.getType()));
		p.getWorld().dropItem(b4.getLocation(), new ItemStack(b4.getType()));
		p.getWorld().dropItem(b5.getLocation(), new ItemStack(b5.getType()));
		p.getWorld().dropItem(b6.getLocation(), new ItemStack(b6.getType()));
		p.getWorld().dropItem(b7.getLocation(), new ItemStack(b7.getType()));
		p.getWorld().dropItem(b8.getLocation(), new ItemStack(b8.getType()));
		p.getWorld().dropItem(b.getLocation(), new ItemStack(b.getType()));

		b.setType(Material.AIR);
		b1.setType(Material.AIR);
		b2.setType(Material.AIR);
		b3.setType(Material.AIR);
		b4.setType(Material.AIR);
		b5.setType(Material.AIR);
		b6.setType(Material.AIR);
		b7.setType(Material.AIR);
		b8.setType(Material.AIR);

	}

	public void send(Player p, String cor, Material m) {
		TitleAPI.sendActionBar("§eVocê encontrou um(a) §" + cor + m.toString().replace("_", " "), p);
	}

	@EventHandler
	void onClick(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (e.isCancelled()) {
			e.setCancelled(true);
			return;
		}
		if (!p.getWorld().getName().equalsIgnoreCase("mina")) {
			return;
		}

		ItemStack type = p.getItemInHand();
		ItemStack fakePick = ItemsAPI.add(Material.DIAMOND_PICKAXE, "§ePicareta Bolada 3x3");
		if (isThisItem(p) == false || type == fakePick) {
			return;
		}
		if (b.getType() != Material.STONE) {
			return;
		}
		int id = gerarID();
		e.setCancelled(true);
		if (id < 12) {
			b.setType(Material.AIR);
			b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.COBBLESTONE));
		} else if (id >= 12 && id <= 20) {
			b.setType(Material.AIR);
			addInv(p, Material.COAL);
			send(p, "0", Material.COAL);
			b.breakNaturally(new ItemStack(Material.AIR));
		} else if (id >= 21 && id <= 29) {
			b.setType(Material.AIR);
			addInv(p, Material.IRON_ORE);
			send(p, "7", Material.IRON_ORE);
			b.breakNaturally(new ItemStack(Material.AIR));
		} else if (id >= 30 && id <= 34) {
			b.setType(Material.AIR);
			ArtItem redstone = new ArtItem(Material.REDSTONE);
			p.getInventory().addItem(redstone);
			send(p, "4", Material.REDSTONE);
			b.breakNaturally(new ItemStack(Material.AIR));
		} else if (id >= 35 && id <= 39) {
			b.setType(Material.AIR);
			Dye l = new Dye();
			l.setColor(DyeColor.BLUE);
			ItemStack lapis = l.toItemStack();
			ItemMeta lm = lapis.getItemMeta();
			lapis.setAmount(lapisr());
			lapis.setItemMeta(lm);
			p.getInventory().addItem(lapis);
			b.breakNaturally(new ItemStack(Material.AIR));
			send(p, "1", Material.LAPIS_ORE);
		} else if (id >= 40 && id <= 45) {
			b.setType(Material.AIR);
			addInv(p, Material.GOLD_ORE);
			b.breakNaturally(new ItemStack(Material.AIR));
			send(p, "6", Material.GOLD_ORE);
		} else if (id >= 46 && id <= 50) {
			b.setType(Material.AIR);
			addInv(p, Material.DIAMOND);
			b.breakNaturally(new ItemStack(Material.AIR));
			send(p, "b", Material.DIAMOND);
		} else if (id >= 51 && id <= 53) {
			b.setType(Material.AIR);
			addInv(p, Material.EMERALD);
			b.breakNaturally(new ItemStack(Material.AIR));
			send(p, "a", Material.EMERALD);
		}

	}

}
