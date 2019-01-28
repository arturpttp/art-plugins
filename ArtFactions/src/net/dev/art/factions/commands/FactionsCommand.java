package net.dev.art.factions.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.objects.ArtItem;
import net.dev.art.factions.api.Menu;
import net.dev.art.factions.api.Menu.MenuItemClick;

public class FactionsCommand extends ArtCommand {

	public FactionsCommand() {
		super("factions");
		addAliases("f", "fac");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!isPlayer(sender))
			return true;
		Player p = (Player) sender;
		if (args.length == 0) {
			Menu menu = new Menu(45, "§8Factions");
			menu.setItem(15, new ArtItem(Material.DIAMOND).nome("§bDiamante"), new MenuItemClick() {

				@EventHandler
				public void onClick(InventoryClickEvent e) {
					e.getWhoClicked().sendMessage("§aALO");
				}
			});
			menu.open(p);
		} else {
			String sc = args[0];

		}
		return false;
	}

}
