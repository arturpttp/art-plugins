package net.dev.art.clans.commands;

import org.bukkit.command.CommandSender;

import net.dev.art.clans.Main;
import net.dev.art.core.managers.ArtCommand;

public class ReloadCommand extends ArtCommand {

	public ReloadCommand() {
		super("clansReload");
		addAliases("artclansReload", "artclansRl", "clansRl");
		setDescription("Dar reload no plugin ArtClans");

	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!hasPermission(sender, "perm.ver.reload")) {
			return false;
		}
		broadcast(
				"§7(" + sender.getName()
						+ ")§8 » §cRecarrendo plugin §bArtClans §cnão feche o servidor. esse comando pode causar lag!",
				"recive.artreload");
		broadcast("§7(" + sender.getName() + ")§8 » §aRecarregamento completo.", "recive.artreload");
		Main.getInstance().reload();
		return false;
	}

}
