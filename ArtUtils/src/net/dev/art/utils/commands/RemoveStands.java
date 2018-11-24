package net.dev.art.utils.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.utils.apis.LaggAPI;
import net.dev.art.utils.apis.RestartAPI;
import net.dev.art.utils.utils.Mensagens;
import net.dev.green.grupos.APIs.GruposAPI;

public class RemoveStands extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("clearlagg")) {
			if (!(GruposAPI.getPremissionLevel(GruposAPI.getGrupo(p)) > 2)) {
				return true;
			}
			mensagem(p, "§eIniciando ClearLagg!");
			LaggAPI.anuncio(5);
		} else if (cmd.getName().equalsIgnoreCase("reiniciar")) {
			if (!(GruposAPI.getPremissionLevel(GruposAPI.getGrupo(p)) > 2)) {
				return true;
			}
			mensagem(p, "§eReiniciamento Começãndo!");
			RestartAPI.anuncio(3);
		}
		return false;
	}

}
