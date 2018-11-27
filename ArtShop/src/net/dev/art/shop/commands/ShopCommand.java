package net.dev.art.shop.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.managers.ArtCommand;
import net.dev.art.shop.menus.ShopInventory;

public class ShopCommand extends ArtCommand {

	public ShopCommand() {
		super("shop");
		addAliases("loja");
	}

	@Override
	public boolean execute(CommandSender sender, String arg1, String[] arg2) {
		if (!isPlayer(sender))
			return true;
		if (!hasPermission(sender, "shop.use"))
			return true;

		Player p = (Player) sender;

		new ShopInventory().open(p);

		return false;
	}

}
