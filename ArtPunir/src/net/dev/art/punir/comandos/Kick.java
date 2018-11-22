package net.dev.art.punir.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.punir.Main;
import net.dev.art.punir.PunimentosAPI;

public class Kick implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kickar")) {
			if (sender.hasPermission("avisar")) {
				if (args.length < 1) {
					sender.sendMessage(
							"§b" + Main.getInstance().getDescription().getName() + "§8 » §cUse: §f/kickar {Player}!");
				} else {
					Player p = Bukkit.getPlayer(args[0]);
					if (p == null) {
						sender.sendMessage("§b" + Main.getInstance().getDescription().getName()
								+ "§8 » §cEsse Player Não Está Online!");
						return true;
					}
					PunimentosAPI.Kickar(p, sender);
					sender.sendMessage("§b" + Main.getInstance().getDescription().getName() + "§8 » §7Aviso Dado Há: `§c"
							+ p.getName() + "§7` !");

				}
			} else {
				sender.sendMessage("§b" + Main.getInstance().getDescription().getName() + "§8 » §cSem Permissao!");
			}
		}
		return false;
	}

}
