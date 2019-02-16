package net.dev.art.dbc.warp.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.dbc.warp.LocManager;
import net.dev.art.dbc.warp.Main;
import net.dev.art.dbc.warp.MessagesManager;
import net.dev.art.dbc.warp.Warp;

public class setWarp implements CommandExecutor, MessagesManager {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			send(Main.getInstance().getConfig().getString("Mesagens.noPlayer").replace("&", "§"));
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("setwarp")) {
			if (!p.hasPermission("setwarp.use")) {
				send(p, Main.getInstance().getConfig().getString("Mesagens.noPerm").replace("&", "§"));
				return true;
			}
			if (args.length == 0) {
				send(p, Main.getInstance().getConfig().getString("Mesagens.insuficientArgs")
						.replace("$command", "setWarp <nome>").replace("&", "§"));
			} else {
				if (args.length > 1) {
					send(p, Main.getInstance().getConfig().getString("Mesagens.insuficientArgs")
							.replace("$command", "setWarp <nome>").replace("&", "§"));
					return true;
				}
				String name = args[0].toLowerCase();
				if (Main.warps.containsKey(name.toLowerCase())) {
					send(p, Main.getInstance().getConfig().getString("Mesagens.existeWarp").replace("&", "§"));
					return true;
				}
				Location loc = p.getLocation();
				Warp w = new Warp(name.toLowerCase(), loc);
//				Main.warps.put(name, w);
				LocManager.setWarp(w);
				send(p, Main.getInstance().getConfig().getString("Mesagens.setWarp").replace("$warp", name).replace("&",
						"§"));

			}
		}
		return false;
	}

}
