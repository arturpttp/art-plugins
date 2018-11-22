package net.dev.art.mina;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class minaCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;

		if (command.getName().equalsIgnoreCase("mina")) {
			if (p.getWorld().getName().equalsIgnoreCase("mina")) {
				p.sendMessage(Main.getInstance().prefix + "§cVocê já está na mina!");
				return true;
			}
			if (Bukkit.getWorlds().contains(Bukkit.getWorld("mina"))) {
				int x = Main.gerarID();
				int y = Main.gerarID();
				int z = Main.gerarID();
				p.teleport(new Location(Bukkit.getWorld("mina"), x, 80, z));
				p.sendMessage(Main.getInstance().prefix + "§ateleportando para mina");
			}else {
				p.sendMessage(Main.getInstance().prefix + "§cA mina não existe!");
			}
		}

		return false;
	}

}
