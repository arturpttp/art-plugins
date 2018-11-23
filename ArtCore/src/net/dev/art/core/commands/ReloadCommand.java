package net.dev.art.core.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.dev.art.core.ArtCore;
import net.dev.art.core.ArtLib;
import net.dev.art.core.ArtPlugin;
import net.dev.art.core.managers.CommandManager;

public class ReloadCommand implements CommandExecutor, ArtLib {

	public ReloadCommand(ArtPlugin pl) {
		pl.setCommand("artreload", this);
		pl.getCommand("artreload").setAliases(Arrays.asList("artrl", "reloadart", "rlart"));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (sender.hasPermission("reload.art")) {
			mensagem(sender, "§aPlugin sendo recarregado.");
			ArtCore.getInstance().reload();
			mensagem(sender, "§aPlugin recarregado.");
		}
		return false;
	}

}
