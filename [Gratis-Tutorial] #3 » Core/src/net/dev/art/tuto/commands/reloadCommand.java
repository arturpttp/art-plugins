package net.dev.art.tuto.commands;

import org.bukkit.command.CommandSender;

import net.dev.art.tuto.ArtCore;
import net.dev.art.tuto.managers.ArtCommand;

public class reloadCommand extends ArtCommand {

	public reloadCommand() {
		super("corereload");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		broadcastWithPerm("§7(" + sender.getName() + ") §8» §cEste comando pode causar lag cuidado no uso dele",
				"coreload.ver");
		ArtCore.getInstance().reload(ArtCore.getInstance());
		broadcastWithPerm("§7(" + sender.getName() + ") §8» §aCarregamento finalizado com sucesso!", "coreload.ver");
		return false;
	}

}
