package net.dev.art.dbc.warp.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.dbc.warp.LocManager;
import net.dev.art.dbc.warp.Main;
import net.dev.art.dbc.warp.MessagesManager;

public class delWarp implements CommandExecutor, MessagesManager {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			send(Main.getInstance().getConfig().getString("Mesagens.noPlayer").replace("&", "§"));
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("delWarp")) {
			if (args.length == 0) {
				send(p, Main.getInstance().getConfig().getString("Mesagens.insuficientArgs")
						.replace("$command", "delWarp <nome>").replace("&", "§"));
			} else {
				if (args.length > 1) {
					send(p, Main.getInstance().getConfig().getString("Mesagens.insuficientArgs")
							.replace("$command", "delWarp <nome>").replace("&", "§"));
					return true;
				}
				String name = args[0].toLowerCase();
				if (!Main.warps.containsKey(name.toLowerCase())) {
					send(p, Main.getInstance().getConfig().getString("Mesagens.noExisteWarp").replace("&", "§"));
					return true;
				}
				LocManager.delWarp(Main.warps.get(name.toLowerCase()));
				send(p, Main.getInstance().getConfig().getString("Mesagens.delWarp").replace("$warp", name).replace("&",
						"§"));
			}
		}
		return false;
	}

}
