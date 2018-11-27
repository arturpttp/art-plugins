package net.dev.art.shop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.ArtCore;
import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.utils.ArtLib;

public class ReloadCommand extends ArtCommand {

	public ReloadCommand() {
		super("shoprl");
		addAliases("artshoprl", "artshopreload");
		setDescription("Dar reload no plugin ArtShop");

	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!hasPermission(sender, "")) {
			return false;
		}
		broadcast(
				"§7(" + sender.getName()
						+ ")§8 » §cRecarrendo plugin §bArtCore §cnão feche o servidor. esse comando pode causar lag!",
				sender, "recive.artreload");
		mensagem(sender, "§7(" + sender.getName()
				+ ")§8 » §cRecarrendo plugin §bArtCore §cnão feche o servidor. esse comando pode causar lag!");
		broadcast("§7(" + sender.getName() + ")§8 » §aRecarregamento completo.", sender, "recive.artreload");
		mensagem(sender, "§7(" + sender.getName() + ")§8 » §aRecarregamento completo.");
		Main.getInstance().reload(Main.getInstance());
		return false;
	}

}
