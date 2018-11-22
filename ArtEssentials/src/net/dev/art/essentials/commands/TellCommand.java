package net.dev.art.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.essentials.utils.Mensagens;

public class TellCommand extends Mensagens implements CommandExecutor {

	public void Tell(Player p, Player t, String msg) {
		p.sendMessage("§6Para " + t.getName() + "§8 » §b" + msg);
		t.sendMessage("§6De " + p.getName() + "§8 » §b" + msg);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("tell")) {
			if (!p.hasPermission("tell.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}

			if (args.length <= 1) {
				mensagem(p, "§eUse:§b /tell <Player> (MENSAGEM)§3 - Enviar Uma Mensagem Privada!");
			} else {

				Player t = Bukkit.getPlayer(args[0]);

				if (t==null) {
					mensagem(p, "§cPlayer Offline!");
					return true;
				}
				
				String message = "";
				for (int i = 0; i < args.length; i++) {
					if (i == args.length - 1) {
						message = message + args[i];
					} else {
						message = message + args[i] + " ";
					}

				}

				Tell(p, t, message);

			}

		}
		return false;
	}

}
