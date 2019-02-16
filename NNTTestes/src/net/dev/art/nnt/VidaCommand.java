package net.dev.art.nnt;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.managers.ArtCommand;

public class VidaCommand extends ArtCommand {

	public VidaCommand() {
		super("nntlife");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!isPlayer(sender))
			return true;
		Player p = (Player) sender;
		p.sendMessage("§c" + Events.lifes.getOrDefault(p.getName(), 2500D) + "♥");
		return false;
	}

}
