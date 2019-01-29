package net.dev.art.facs.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dev.art.core.managers.Menu;
import net.dev.art.core.managers.Menu.MenuSize;
import net.dev.art.core.objects.ArtItem;
import net.dev.art.facs.enums.Cargo;
import net.dev.art.facs.objects.FactionPlayer;
import net.dev.art.facs.utils.Heads;

public class HasFactionMenu extends Menu {

	private FactionPlayer fp;

	public FactionPlayer getFp() {
		return fp;
	}

	public void setFp(FactionPlayer fp) {
		this.fp = fp;
	}

	public HasFactionMenu(FactionPlayer fp) {
		super("§7Informações da facção", MenuSize.FIVE_LINES);
		setFp(fp);
		addItems();
	}

	private void addItems() {

		String headName = fp.getCargo() == Cargo.Lider ? "§7Desfazer Facção." : "§7Sair da Facção.";
		ItemStack i = Heads.VERMELHO;
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(headName);
		i.setItemMeta(m);

		if (fp.getCargo() == Cargo.Lider) {
			this.setItem(20, i, e -> {
				Player p = (Player) e.getWhoClicked();
				p.chat("/f desfazer");
			});
		} else {
			this.setItem(20, i, e -> {
				Player p = (Player) e.getWhoClicked();
				p.chat("/f sair");
			});
		}

	}

}
