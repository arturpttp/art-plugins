package net.dev.art.clans.commands;

import org.bukkit.command.CommandSender;

import net.dev.art.core.managers.ArtCommand;

public class C extends ArtCommand{

	public C() {
		super("c");
		setDescription("Falar com o clan");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		
		return false;
	}

}
