package net.dev.art.tuto.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.tuto.utils.ArtLIB;

public abstract class ArtCommand extends Command implements ArtLIB {

	public ArtCommand(String name) {
		super(name);
	}

	protected boolean isPlayer(CommandSender sender) {
		boolean isPlayer = sender instanceof Player;
		if (!isPlayer) {
			console("§cComando Apenas para players");
			return false;
		}
		return true;
	}

	protected boolean hasPermission(CommandSender sender, String permission) {
		boolean hasPermission = sender.hasPermission(permission);
		if (!hasPermission) {
			sender.sendMessage("§cVocê não tem permissao parar isso!");
			return false;
		}
		return true;
	}

}
