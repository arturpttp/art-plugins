package net.dev.art.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.essentials.utils.Mensagens;

public class Heal extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("heal")) {
			if (!p.hasPermission("heal.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}

			if (args.length == 0) {

				mensagem(p, "§eVocê Curou Sua Fome e Vida!");
				p.setHealth(p.getMaxHealth());
				p.setFoodLevel(20);
				p.setFireTicks(0);
				
			} else {

				Player t = Bukkit.getPlayer(args[0]);

				if (t == null) {
					mensagem(p, "§cPlayer Offline!");
					return true;
				}
				
				mensagem(p, "§eVocê Curou a Fome e Vida De §b" + t.getName());
				mensagem(t, "§eSua Fome e Vida Foi Curada Por §b" + p.getName());
				t.setHealth(t.getMaxHealth());
				t.setFoodLevel(20);
				t.setFireTicks(0);
				
			}

		}
		return false;
	}

}
