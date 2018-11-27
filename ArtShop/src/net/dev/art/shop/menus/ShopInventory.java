package net.dev.art.shop.menus;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.dev.art.core.objects.ArtInventory;
import net.dev.art.core.objects.ArtItem;
import net.dev.art.core.objects.ArtPlayer;

public class ShopInventory extends ArtInventory {

	public ShopInventory() {
		super("§6§lSHOP", 9 * 3);

		setItem(12, new ArtItem(Material.DIAMOND).nome("§b§lDIAMANTE").lore(" ", "§eclique com esquerdo para comprar",
				"§aValor: 10000", " "), new MenuItemClick() {

					@Override
					public void onClick(InventoryClickEvent e) {
						Player player = (Player) e.getWhoClicked();
						ArtPlayer p = new ArtPlayer(player);

						if (p.getCoins() >= 10000) {
							e.getWhoClicked().getInventory()
									.addItem(new ArtItem(Material.DIAMOND).nome("§b§lDIAMANTE"));
							player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 1f);
							player.sendMessage("§aVocê comprou um §b§lDIAMANTE");
							player.closeInventory();
							p.coin().remove(10000);
						} else {
							player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1f, 1f);
							player.sendMessage("§cVocê nao tem dinheiro suficiente para comprar um §b§lDIAMANTE");
							player.closeInventory();
						}

					}
				});

	}

}
