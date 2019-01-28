package net.dev.art.clans.commands;

import org.bukkit.command.CommandSender;

import net.dev.art.core.managers.ArtCommand;

public class Clan extends ArtCommand {

	public Clan() {
		super("clan");
		addAliases("clans");
		setDescription("Principal comando do clans");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!isPlayer(sender))
			return true;
		if (args.length == 0) {
			
		} else {

		}
		return false;
	}

}
