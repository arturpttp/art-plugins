package net.dev.art.essentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.TitleAPI;
import net.dev.art.essentials.Main;
import net.dev.art.essentials.utils.Mensagens;
import net.dev.green.grupos.APIs.GruposAPI;

public class Build extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("build")) {
			if (GruposAPI.getPremissionLevel(GruposAPI.getGrupo(p)) > 2) {
				if (Main.builds.contains(p)) {
					Main.builds.remove(p);
					mensagem(p, "§cVocê desativou seu modo §bBUILD§c, agora você não pode mais construir");
				} else {
					Main.builds.add(p);
					TitleAPI.sendTitle(p, "§a" + p.getName().toUpperCase(), "§aVocê ativou seu modo §bBUILD");
					mensagem(p, "§aVocê ativou seu modo §bBUILD§a, agora você pode construir");
				}
			} else {
				p.sendMessage(NoPerm);
			}
		}
		return false;
	}

}
