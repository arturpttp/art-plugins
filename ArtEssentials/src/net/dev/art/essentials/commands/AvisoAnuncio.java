package net.dev.art.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.TitleAPI;
import net.dev.art.essentials.utils.Mensagens;

public class AvisoAnuncio extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("aviso")) {
			if (!p.hasPermission("aviso.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}

			if (args.length == 0) {
				mensagem(p, "§eUse:§b /aviso (MENSAGEM)§3 - Enviar Um Aviso Na Tela é No Chat ");
			} else {
				String message = "";
				for (int i = 0; i < args.length; i++) {
					if (i == args.length - 1) {
						message = message + args[i];
					} else {
						message = message + args[i] + " ";
					}

				}

				for (Player on : Bukkit.getOnlinePlayers()) {
					on.sendMessage("§4§l[!] §cAVISO §4§l[!]§c - " + message.replace("&", "§").replace("<3", "♥"));
					TitleAPI.sendTitle(on, "§4§l[!] §cAVISO §4§l[!]", "§c" + message.replace("&", "§").replace("<3", "♥"));
				}

			}

		} else if (cmd.getName().equalsIgnoreCase("anuncio")) {
			if (!p.hasPermission("anuncio.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}
			if (args.length == 0) {
				mensagem(p, "§eUse:§b /anuncio (MENSAGEM)§3 - Enviar Um Anuncio Na Tela é No Chat ");
			} else {
				String message = "";
				for (int i = 0; i < args.length; i++) {
					if (i == args.length - 1) {
						message = message + args[i];
					} else {
						message = message + args[i] + " ";
					}

				}

				for (Player on : Bukkit.getOnlinePlayers()) {
					on.sendMessage("§6(ANUNCIO)§e - " + message.replace("&", "§").replace("<3", "♥"));
					TitleAPI.sendTitle(on, "§6(ANUNCIO)", "§e" + message.replace("&", "§").replace("<3", "♥"));
				}

			}
		}
		return false;
	}

}
