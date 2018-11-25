package net.dev.art.core.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.utils.ArtLib;

public abstract class ArtCommand extends Command implements ArtLib {

	public ArtCommand(String name) {
		super(name);
	}

	protected boolean isPlayer(CommandSender sender) {
		boolean isPlayer = sender instanceof Player;
		if (!isPlayer) {
			noPlayer(sender);
			return false;
		} else {
			return true;
		}
	}

	protected boolean isPlayer(CommandSender sender, String permission) {
		boolean hasPerm = sender.hasPermission(permission);
		if (!hasPerm) {
			noPerm(sender);
			return false;
		} else {
			return true;
		}
	}

}
