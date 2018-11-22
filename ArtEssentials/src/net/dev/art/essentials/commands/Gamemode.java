package net.dev.art.essentials.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.essentials.utils.Mensagens;

public class Gamemode extends Mensagens implements CommandExecutor {

	String sendGameMode(String gm) {
		return "§eSeu Modo De Jogo Foi Alterado Para `§b" + gm.toLowerCase() + "§e`";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("gm")) {
			if (!p.hasPermission("gm.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}
			if (args.length == 0) {
				if (p.getGameMode() == GameMode.CREATIVE) {
					p.setGameMode(GameMode.SURVIVAL);
					mensagem(p, sendGameMode("SURVIVAL"));
					return true;
				}
				if (p.getGameMode() == GameMode.SURVIVAL) {
					p.setGameMode(GameMode.CREATIVE);
					mensagem(p, sendGameMode("CREATIVE"));
					return true;
				}
				if (p.getGameMode() == GameMode.SPECTATOR) {
					p.setGameMode(GameMode.ADVENTURE);
					mensagem(p, sendGameMode("ADVENTURE"));
					return true;
				}
				if (p.getGameMode() == GameMode.ADVENTURE) {
					p.setGameMode(GameMode.SPECTATOR);
					mensagem(p, sendGameMode("SPECTATOR"));
					return true;
				}
			} else {

				if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")
						|| args[0].equalsIgnoreCase("survival")) {
					p.setGameMode(GameMode.SURVIVAL);
					mensagem(p, sendGameMode("SURVIVAL"));
				} else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")
						|| args[0].equalsIgnoreCase("creative")) {
					p.setGameMode(GameMode.CREATIVE);
					mensagem(p, sendGameMode("CREATIVE"));
				} else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")
						|| args[0].equalsIgnoreCase("adventure")) {
					p.setGameMode(GameMode.ADVENTURE);
					mensagem(p, sendGameMode("ADVENTURE"));
				} else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")
						|| args[0].equalsIgnoreCase("apectator")) {
					p.setGameMode(GameMode.SPECTATOR);
					mensagem(p, sendGameMode("SPECTATOR"));
				}
			}
		}

		return false;
	}

}
