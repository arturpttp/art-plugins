package net.dev.art.enchants.enchants;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.ItemsAPI;

public class SmeltingEnchantment extends Enchantment implements Listener {

	public SmeltingEnchantment(int id) {
		super(id);
	}

	@EventHandler
	void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (p.getItemInHand().containsEnchantment(this)) {
			if (p.getItemInHand().getType().toString().contains("PICKAXE")) {
				if (b.getType() != Material.GOLD_ORE && b.getType() != Material.IRON_ORE) {
					return;
				}
				if (b.getType() == Material.GOLD_ORE) {
					e.setCancelled(true);
					b.setType(Material.AIR);
					b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.GOLD_INGOT));
				} else if (b.getType() == Material.IRON_ORE) {
					e.setCancelled(true);
					b.setType(Material.AIR);
					b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.IRON_INGOT));

				}
			} else if (p.getItemInHand().getType().toString().contains("AXE")) {
				if (b.getType() == Material.WOOD) {
					e.setCancelled(true);
					b.setType(Material.AIR);
					b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.COAL, 1, (short) 1));
				} else if (b.getType() == Material.CACTUS) {
					e.setCancelled(true);
					b.setType(Material.AIR);
					ItemStack verde = ItemsAPI.corante(null, DyeColor.GREEN, null);
					b.getWorld().dropItem(b.getLocation(), verde);

				}
			} else if (p.getItemInHand().getType().toString().contains("SPADE")
					|| p.getItemInHand().getType().toString().contains("SHOVEL")) {
				if (b.getType() == Material.SAND) {
					e.setCancelled(true);
					b.setType(Material.AIR);
					b.getWorld().dropItem(b.getLocation(), new ItemStack(Material.GLASS));
				}
			}
		}
	}

	@Override
	public int getId() {
		return 102;
	}

	@Override
	public boolean canEnchantItem(ItemStack arg0) {
		return true;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {

		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {

		return null;
	}

	@Override
	public int getMaxLevel() {

		return 1;
	}

	@Override
	public String getName() {
		return "Smelting";
	}

	@Override
	public int getStartLevel() {

		return 1;
	}

}
