package net.dev.art.core.commands;

import org.bukkit.command.CommandSender;

import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.managers.PluginsManager;

public class ArtPluginManage extends ArtCommand {

	public ArtPluginManage() {
		super("artPluginManager");
		addAliases("artPlugin", "PluginManager");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (args.length <= 1) {
			mensagem("§cUse: /" + getName() + " (enable/disble/reload) [plugin]");
		} else {
			String sc = args[0];
			String plugin = args[1];
			if (!sc.equalsIgnoreCase("disble") && !sc.equalsIgnoreCase("enable") && !sc.equalsIgnoreCase("reload")) {
				mensagem("§cUse: /" + getName() + " (enable/disble/reload) [plugin]");
				return true;
			}
			if (sc.equalsIgnoreCase("disble")) {
				PluginsManager.disable(plugin);
			} else if (sc.equalsIgnoreCase("enbale")) {
				PluginsManager.enable(plugin);
			} else if (sc.equalsIgnoreCase("reload")) {
				PluginsManager.reload(plugin);
			}
		}
		return false;
	}

}
