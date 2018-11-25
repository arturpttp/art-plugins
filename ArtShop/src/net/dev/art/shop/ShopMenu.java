package net.dev.art.shop;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.dev.art.core.objects.ArtItem;
import net.dev.art.core.objects.ArtMenu;
import net.dev.art.core.objects.ArtMenu.MenuSize;
import net.dev.art.shop.menus.BlocosMenu;
import net.dev.art.core.objects.ArtMenu.MenuItemClick;

public class ShopMenu extends ArtMenu {

	public ShopMenu() {
		super("§6§lSHOP", MenuSize.SIX_LINES);
		BlocosMenu bm = new BlocosMenu();
		MenuItem item = newMenuItem(new ArtItem(Material.GRASS).nome("§aBlocos").lore("§aAbra o menu de blocos"), e -> {
			e.getWhoClicked().closeInventory();
			bm.open(e.getWhoClicked());
		});

		setItem(0, item);

	}

}
