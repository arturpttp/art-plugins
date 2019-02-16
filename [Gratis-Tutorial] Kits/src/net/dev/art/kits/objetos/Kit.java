package net.dev.art.kits.objetos;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.dev.art.kits.Main;

public class Kit {

	private String name, permission;
	private long delay;
	private ItemStack icon;
	private ItemStack[] itens;

	public Kit(String name, long delay, ItemStack[] itens, String permission, ItemStack icon) {
		this.name = name;
		this.delay = delay;
		this.itens = itens;
		Main.getInstance().kits.add(this);
	}

	public void darKit(Player p) {
		for (ItemStack i : itens) {
			if (p.getInventory().firstEmpty() == -1) {
				p.getWorld().dropItem(p.getLocation(), i);
			} else {
				p.getInventory().addItem(i);
			}
		}
		p.sendMessage("§aVocê pegou o kit §8» §f" + getName());
	}

	public ItemStack getIcon() {
		return icon;
	}

	public String getPermission() {
		return permission;
	}

	public ItemStack[] getItens() {
		return itens;
	}

	public long getDelay() {
		return delay;
	}

	public String getName() {
		return name;
	}

}
