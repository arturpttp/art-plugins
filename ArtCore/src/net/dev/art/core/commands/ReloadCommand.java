package net.dev.art.core.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.dev.art.core.ArtCore;
import net.dev.art.core.managers.CommandManager;

public class ReloadCommand extends CommandManager {

	public ReloadCommand(Plugin pl) {
		setPlugin(pl);
		register();
		setPermission("admin.reload");
		setPermissionMessage(getPermissionMessage());
		setName("artreload");
		setAliases(Arrays.asList("artrl", "reloadart"));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		mensagem(sender, "§aPlugin sendo recarregado.");
		ArtCore.getInstance().reload();
		mensagem(sender, "§aPlugin recarregado.");
		return false;
	}

}
