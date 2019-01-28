package net.dev.art.utils.drops;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.dev.art.core.objects.ArtItem;
import net.dev.art.utils.Main;

public class DropsManager implements Listener {

	/*
	 * ao quebrar algo vai adicionalo direto a um menu virtual que com um comando
	 * "/drops" podera acessalo e com um botão vendera tudo com seu preço e vai
	 * adicionar na conta direto
	 * 
	 */

	public static Material getByString(String name) {
		for (Material m : Material.values()) {
			if (m.toString().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}

	private static int getSlotToCenter(Inventory inventory) {
		if (inventory.getItem(10) == null || inventory.getItem(10).getType() == Material.AIR)
			return 10;
		if (inventory.getItem(11) == null || inventory.getItem(11).getType() == Material.AIR)
			return 11;
		if (inventory.getItem(12) == null || inventory.getItem(12).getType() == Material.AIR)
			return 12;
		if (inventory.getItem(13) == null || inventory.getItem(13).getType() == Material.AIR)
			return 13;
		if (inventory.getItem(14) == null || inventory.getItem(14).getType() == Material.AIR)
			return 14;
		if (inventory.getItem(15) == null || inventory.getItem(15).getType() == Material.AIR)
			return 15;
		if (inventory.getItem(16) == null || inventory.getItem(16).getType() == Material.AIR)
			return 16;
		if (inventory.getItem(19) == null || inventory.getItem(19).getType() == Material.AIR)
			return 19;
		if (inventory.getItem(20) == null || inventory.getItem(20).getType() == Material.AIR)
			return 20;
		if (inventory.getItem(21) == null || inventory.getItem(21).getType() == Material.AIR)
			return 21;
		if (inventory.getItem(22) == null || inventory.getItem(22).getType() == Material.AIR)
			return 22;
		if (inventory.getItem(23) == null || inventory.getItem(23).getType() == Material.AIR)
			return 23;

		if (inventory.getItem(24) == null || inventory.getItem(24).getType() == Material.AIR)
			return 24;
		if (inventory.getItem(25) == null || inventory.getItem(25).getType() == Material.AIR)
			return 25;
		if (inventory.getItem(26) == null || inventory.getItem(26).getType() == Material.AIR)
			return 26;
		if (inventory.getItem(27) == null || inventory.getItem(27).getType() == Material.AIR)
			return 27;
		if (inventory.getItem(28) == null || inventory.getItem(28).getType() == Material.AIR)
			return 28;
		if (inventory.getItem(29) == null || inventory.getItem(29).getType() == Material.AIR)
			return 29;
		if (inventory.getItem(30) == null || inventory.getItem(30).getType() == Material.AIR)
			return 30;
		if (inventory.getItem(31) == null || inventory.getItem(31).getType() == Material.AIR)
			return 31;
		if (inventory.getItem(32) == null || inventory.getItem(32).getType() == Material.AIR)
			return 32;
		if (inventory.getItem(33) == null || inventory.getItem(33).getType() == Material.AIR)
			return 33;
		if (inventory.getItem(34) == null || inventory.getItem(34).getType() == Material.AIR)
			return 34;
		if (inventory.getItem(35) == null || inventory.getItem(35).getType() == Material.AIR)
			return 35;
		if (inventory.getItem(36) == null || inventory.getItem(36).getType() == Material.AIR)
			return 36;
		if (inventory.getItem(37) == null || inventory.getItem(37).getType() == Material.AIR)
			return 37;
		if (inventory.getItem(38) == null || inventory.getItem(38).getType() == Material.AIR)
			return 38;
		if (inventory.getItem(39) == null || inventory.getItem(39).getType() == Material.AIR)
			return 39;
		if (inventory.getItem(40) == null || inventory.getItem(40).getType() == Material.AIR)
			return 40;
		if (inventory.getItem(41) == null || inventory.getItem(41).getType() == Material.AIR)
			return 41;
		if (inventory.getItem(42) == null || inventory.getItem(42).getType() == Material.AIR)
			return 42;
		if (inventory.getItem(43) == null || inventory.getItem(43).getType() == Material.AIR)
			return 43;
		return -1;
	}

//	@EventHandler
//	void onCommand(PlayerCommandPreprocessEvent e) {
//		if (e.getMessage().startsWith("/drops")) {
//			e.setCancelled(true);
//			open(e.getPlayer());
//		}
//	}

	public static void open(Player p) {
		Inventory inv = Bukkit.createInventory(p, 9 * 6, "§EDrops de §b" + p.getName());

		for (String sec : Main.getInstance().drops.getSection("Drops." + p.getName()).getKeys(false)) {
			Material m = getByString(sec);
			int qnt = Main.getInstance().drops.getInt(sec);
			ArtItem item = new ArtItem(m).nome("§e" + sec).lore("§eQuantidade: §b" + qnt).Glowing(true);
			inv.setItem(getSlotToCenter(inv), item);
		}

		p.openInventory(inv);

	}

	private List<ItemStack> getDrops(Block b) {
		List<ItemStack> drop = new ArrayList<>();

		for (ItemStack item : b.getDrops()) {
			drop.add(item);
		}
		return drop;
	}

	@EventHandler
	void event(BlockBreakEvent e) {
		if (e.isCancelled())
			return;
		Player p = e.getPlayer();
		Block b = e.getBlock();
		for (ItemStack drop : getDrops(b)) {
			Main.getInstance().drops.set("Drops." + p.getName() + "." + drop.getType().toString(),
					Main.getInstance().drops.getInt("Drops." + p.getName() + "." + drop.getType().toString())
							+ drop.getAmount());
			Main.getInstance().drops.saveConfig();
		}
	}

}
