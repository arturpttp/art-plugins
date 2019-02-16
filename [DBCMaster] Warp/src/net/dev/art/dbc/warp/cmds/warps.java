package net.dev.art.dbc.warp.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.dbc.warp.LocManager;
import net.dev.art.dbc.warp.Main;
import net.dev.art.dbc.warp.MessagesManager;

public class warps implements CommandExecutor, MessagesManager {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			send(Main.getInstance().getConfig().getString("Mesagens.noPlayer").replace("&", "§"));
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("warps")) {
//			if(!p.hasPermission("warps.use")) {
//				send(p, Main.getInstance().getConfig().getString("Mesagens.noPerm").replace("&", "§"));
//				return true;
//			}
			send(p, Main.getInstance().getConfig().getString("Mesagens.warpList")
					.replace("$warps", "" + LocManager.getWarpList()).replace("&", "§"));
		}
		return false;
	}

}
