package net.dev.art.essentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.essentials.utils.Mensagens;

public class Fly extends Mensagens implements CommandExecutor {

	public static List<Player> fly = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (!p.hasPermission("fly.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}
			if (args.length == 0) {
				if (fly.contains(p)) {
					fly.remove(p);
					mensagem(p, "§eVocê Disabilitou Seu `§bfly§e`");
					p.setAllowFlight(false);
				} else {
					fly.add(p);
					mensagem(p, "§eVocê Abilitou Seu `§bfly§e`");
					p.setAllowFlight(true);
				}
			} else {
				Player t = Bukkit.getPlayer(args[0]);

				if (t == null) {
					mensagem(p, "§cEsse Player Está Offline!");
					return true;
				}

				if (args[0].equalsIgnoreCase(t.getName())) {
					if (fly.contains(t)) {
						fly.remove(t);
						mensagem(p, "§eVocê Disabilitou O `§bfly§e` de `§b" + t.getName() + "§e`");
						mensagem(t, "§b" + p.getName() + " §eDisabilitou Seu `§bfly§e`");
						t.setAllowFlight(false);
					} else {
						fly.add(t);
						mensagem(p, "§eVocê Abilitou O `§bfly§e` de `§b" + t.getName() + "§e`");
						mensagem(t, "§b" + p.getName() + " §eAbilitou Seu `§bfly§e`");
						t.setAllowFlight(true);
					}
				}

			}

		}
		return false;
	}

}
