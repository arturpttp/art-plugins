package net.dev.art.essentials.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.essentials.Main;
import net.dev.art.essentials.utils.Mensagens;

public class setSpawn extends Mensagens implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("setspawn")) {
			if (!p.hasPermission("setspawn.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}
			Location l = p.getLocation();
			Main.spawn.setLocation("Spawn", p.getLocation());
			Main.spawn.saveConfig();
			p.sendTitle("§aSpawn", "§eSpawn setado com sucesso!");

		}

		return false;
	}

}
