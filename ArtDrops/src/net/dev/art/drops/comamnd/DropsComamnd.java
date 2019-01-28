package net.dev.art.drops.comamnd;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.Paginas;
import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.objects.ArtItem;
import net.dev.art.drops.Main;
import net.dev.art.drops.apis.DropsAPI;

public class DropsComamnd extends ArtCommand {

	public DropsComamnd() {
		super("drops");

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

	public static Material getByString(String name) {
		for (Material m : Material.values()) {
			if (m.toString().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}

//	public static void open(Player p) {
//		Inventory inv = Bukkit.createInventory(p, 9 * 6, "§EDrops de §b" + p.getName());
//
//		for (String sec : Main.getInstance().drops.getSection("Drops." + p.getName()).getKeys(false)) {
//			Material m = getByString(sec);
//			int qnt = Main.getInstance().drops.getInt(sec);
//			ArtItem item = new ArtItem(m).nome("§e" + sec).lore("§eQuantidade: §b" + qnt).Glowing(true);
//			inv.setItem(getSlotToCenter(inv), item);
//		}
//
//		p.openInventory(inv);
//
//	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!(isPlayer(sender)))
			return false;
		Player p = (Player) sender;
		ArrayList<ItemStack> items = new ArrayList<>();
		for (String sec : Main.getInstance().getConfig().getConfigurationSection(p.getName().toLowerCase())
				.getKeys(false)) {
			Material m = getByString(sec);
			ArtItem item = new ArtItem(m).nome("§e" + sec.replace("_", " ")).lore("§aQuantidade", "",
					"§f" + DropsAPI.getDrops(p, m));
			items.add(item);
		}
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§eDrops de » §b" + p.getName());

		for (ItemStack i : items) {
			inv.setItem(getSlotToCenter(inv), i);
		}

		// 46 49 52

		ArtItem coletar = new ArtItem(Material.CHEST).nome("§aColetar todos os drops");
		ArtItem sellall = new ArtItem(Material.EMERALD).nome("§aVender tudo");
		ArtItem onOFF = new ArtItem(Material.WOOL).nome("§cDisailitar coletor de drops").setData((short) 14);

		inv.setItem(46, coletar);

		inv.setItem(49, sellall);

		inv.setItem(52, onOFF);

		p.openInventory(inv);
		return false;
	}

}
