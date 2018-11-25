package net.dev.art.shop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.ArtCore;
import net.dev.art.core.utils.ArtLib;

public class ReloadCommand implements CommandExecutor, ArtLib {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (cmd.getName().equalsIgnoreCase("artshopreload")) {
			broadcast("§7(" + sender.getName()
					+ ")§8 » §cRecarrendo plugin §bArtCore §cnão feche o servidor. esse comando pode causar lag!",
					sender, "recive.artreload");
			mensagem(sender, "§7(" + sender.getName()
					+ ")§8 » §cRecarrendo plugin §bArtCore §cnão feche o servidor. esse comando pode causar lag!");
			broadcast("§7(" + sender.getName() + ")§8 » §aRecarregamento completo.", sender, "recive.artreload");
			mensagem(sender, "§7(" + sender.getName() + ")§8 » §aRecarregamento completo.");
			Main.getInstance().reload(Main.getInstance());
		}
		return false;
	}

}
