package net.dev.art.core.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.dev.art.core.ArtCore;
import net.dev.art.core.ArtPlugin;
import net.dev.art.core.managers.CommandManager;
import net.dev.art.core.utils.ArtLib;

public class ReloadCommand implements CommandExecutor, ArtLib {

	ArtPlugin pl;

	public ReloadCommand(ArtPlugin pl) {
		this.pl = pl;
		pl.setCommand("artreload", this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (sender.hasPermission("reload.art")) {
			broadcast("§7(" + sender.getName()
					+ ")§8 » §cRecarrendo plugin §bArtCore §cnão feche o servidor. esse comando pode causar lag!",
					sender, "recive.artreload");
			mensagem(sender, "§7(" + sender.getName()
					+ ")§8 » §cRecarrendo plugin §bArtCore §cnão feche o servidor. esse comando pode causar lag!");
			ArtCore.getInstance().reload(ArtCore.getInstance());
			broadcast("§7(" + sender.getName() + ")§8 » §aRecarregamento completo.", sender, "recive.artreload");
			mensagem(sender, "§7(" + sender.getName() + ")§8 » §aRecarregamento completo.");
		}
		return false;
	}

}
