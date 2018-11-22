package net.dev.art.rc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTemplate implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas Players AMIGAO!");
			return false;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("cmd")) {
			p.sendMessage("§cAPENAS UM COMANDO DE TESTES");
		}
		return false;
	}

}
