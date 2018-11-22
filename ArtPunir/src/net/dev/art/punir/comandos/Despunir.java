package net.dev.art.punir.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.punir.Main;
import net.dev.art.punir.PunimentosAPI;

public class Despunir implements CommandExecutor {

	String prefix = "§b" + Main.getInstance().getDescription().getName() + "§8 » ";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (cmd.getName().equalsIgnoreCase("desmutar")) {
			if (sender.hasPermission("despunir")) {
				if (args.length < 1) {
					sender.sendMessage(
							"§b" + Main.getInstance().getDescription().getName() + "§8 » §cUse: §f/desmutar {Player}!");
				} else {
					Player p = Bukkit.getPlayer(args[0]);
					if (p == null) {
						sender.sendMessage("§b" + Main.getInstance().getDescription().getName()
								+ "§8 » §cEsse Player Não Está Online!");
						return true;
					}
					try {
						PunimentosAPI.Despunir(p, "mute");
						sender.sendMessage(prefix + "§eVocê Desmutou: `§b"+p.getName()+"§e` §eCom §aSucesso");
					} catch (Exception e) {
						sender.sendMessage(prefix + "§cVocê Não Consegui Desmutar: `§b"+p.getName()+"§e`");
					}
					
					
				}
			} else {
				sender.sendMessage("§b" + Main.getInstance().getDescription().getName() + "§8 » §cSem Permissao!");
			}
		}else if (cmd.getName().equalsIgnoreCase("desbanir")) {
			if (sender.hasPermission("despunir")) {
				if (args.length < 1) {
					sender.sendMessage(
							"§b" + Main.getInstance().getDescription().getName() + "§8 » §cUse: §f/desbanir {Player}!");
				} else {
					try {
						PunimentosAPI.Despunir(args[0], "ban");
						sender.sendMessage(prefix + "§eVocê Desbaniu: `§b"+args[0]+"§e` §eCom §aSucesso");
					} catch (Exception e) {
						sender.sendMessage(prefix + "§cVocê Não Consegui Desbaniu: `§b"+args[0]+"§e`");
					}

				}
			} else {
				sender.sendMessage("§b" + Main.getInstance().getDescription().getName() + "§8 » §cSem Permissao!");
			}
		}
		return false;
	}

}
