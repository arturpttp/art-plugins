package net.dev.art.dbc.lx;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LixeiraCommand implements CommandExecutor, MessagesManager {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("lixeira")) {
			if (!p.hasPermission("lixeira.use")) {
				p.sendMessage("§cSem permissao!");
				return true;
			}

			if (args.length == 0) {
				p.sendMessage(LagManager.prefix + "§cUtilize: §f/lixeira <anuncios> -  limpar os mobs e itens dropados!");
			} else if (args.length == 1) {
				int qnt = 5;
				try {
					qnt = Integer.valueOf(args[0]);
				} catch (NumberFormatException e) {
					p.sendMessage(LagManager.prefix + "§cUtilize um numero correto");
				}
				if (args[0].equalsIgnoreCase("NaN")) {
					p.sendMessage(LagManager.prefix + "§cBurlando o sistema?");
					return true;
				}
				if (qnt > 5) {
					p.sendMessage(LagManager.prefix + "§cUtilize um numero menor que 5");
				} else if (qnt <= 0) {
					p.sendMessage(LagManager.prefix + "§cUtilize um numero maior que 0");
				}
				LagManager.anuncio(qnt);
			} else {
				p.sendMessage(LagManager.prefix + "§cUtilize: 1§f/lagg <anuncios> -  limpar os mobs e itens dropados!");
			}

		}
		return false;
	}

}
