package net.dev.art.utils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.utils.ArtLib;
import net.dev.art.utils.apis.AdminAPI;

public class AdminCommand implements CommandExecutor, ArtLib {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			noPlayer(sender);
			return true;
		}
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("admin")) {
			if(p.hasPermission("admin.user")) {
				AdminAPI.admin(p);
			}else {
				noPerm(p);
			}
		}
		
		return false;
	}

}
