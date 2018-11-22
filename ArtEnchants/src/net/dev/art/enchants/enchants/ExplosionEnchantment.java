package net.dev.art.enchants.enchants;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ExplosionEnchantment extends Enchantment implements Listener {

	public ExplosionEnchantment(int id) {
		super(id);
	}

	@EventHandler
	void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().containsEnchantment(this)) {
			if (p.isSneaking())
				return;
			if(p.getItemInHand().getEnchantments().get(this)==1) {
				e.getBlock().getWorld().createExplosion(e.getBlock().getLocation(), 3, false);
			}else if(p.getItemInHand().getEnchantments().get(this)==2) {
				e.getBlock().getWorld().createExplosion(e.getBlock().getLocation(), 5, false);
			}
		}
	}

	@Override
	public int getId() {
		return 101;
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

		return 2;
	}

	@Override
	public String getName() {
		return "Explosive";
	}

	@Override
	public int getStartLevel() {

		return 1;
	}

}
