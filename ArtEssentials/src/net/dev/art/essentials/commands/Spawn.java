package net.dev.art.essentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.essentials.Main;
import net.dev.art.essentials.utils.Mensagens;

public class Spawn extends Mensagens implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("spawn")) {

			new BukkitRunnable() {
				int tempo = 3;

				public void run() {
					if (tempo >= 1) {
						p.sendTitle("§aSpawn", "§eVocê sera teleportado em §b" + tempo);
						tempo--;
					} else if (tempo == 0) {
						p.sendTitle("§aSpawn", "§eTeleportando...");
						try {
							p.teleport(Main.spawn.getLocation("Spawn"));
						} catch (Exception e) {
							mensagem(p, "§cO spawn não foi setado!");

						}
						tempo--;
					}

				}
			}.runTaskTimer(Main.getInstance(), 20, 20);

		}

		return false;
	}

}
