package net.dev.art.core.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.dev.art.core.utils.ArtLib;

public abstract class ArtCommand extends Command implements ArtLib, Listener {

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

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		return null;
	}

	public abstract boolean execute(CommandSender sender, String lb, String[] args);

	protected boolean hasPermission(CommandSender sender, String permission) {
		boolean hasPerm = sender.hasPermission(permission);
		if (!hasPerm) {
			noPerm(sender);
			return false;
		} else {
			return true;
		}
	}

	protected void addAliases(String... aliases) {
		setAliases(Arrays.asList(aliases));
	}

}
