package net.dev.art.core.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.dev.art.core.ArtCore;
import net.dev.art.core.ArtPlugin;
import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.managers.CommandManager;
import net.dev.art.core.utils.ArtLib;

public class ReloadCommand extends ArtCommand implements ArtLib {

	public ReloadCommand() {
		super("corerl");
		addAliases("corereload", "artcorerl", "artcorereload");
		setDescription("Recarregar o plugin ArtCore!");

	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!hasPermission(sender, "corerl"))
			return true;
		broadcast(
				"§7(" + sender.getName()
						+ ")§8 » §cRecarrendo plugin §bArtCore §cnão feche o servidor. esse comando pode causar lag!",
				sender, "recive.artreload");
		mensagem(sender, "§7(" + sender.getName()
				+ ")§8 » §cRecarrendo plugin §bArtCore §cnão feche o servidor. esse comando pode causar lag!");
		ArtCore.getInstance().reload(ArtCore.getInstance());
		broadcast("§7(" + sender.getName() + ")§8 » §aRecarregamento completo.", sender, "recive.artreload");
		mensagem(sender, "§7(" + sender.getName() + ")§8 » §aRecarregamento completo.");
		return false;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		List<String> list = new ArrayList<>();
		return list;
	}

}
