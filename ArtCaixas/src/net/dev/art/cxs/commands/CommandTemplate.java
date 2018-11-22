package net.dev.art.cxs.commands;

import java.io.File;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.cxs.Main;
import net.dev.art.cxs.utils.Mensagens;

public class CommandTemplate extends Mensagens implements CommandExecutor {

	public static File[] getFiles() {
		File file = new File(Main.getInstance().getDataFolder() + File.separator + "caixas");
		return file.listFiles();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("caixa")) {
			for (File file : getFiles()) {
				p.sendMessage(file.getName());
			}
			p.sendMessage("§cAPENAS UM COMANDO DE TESTES");
		}
		return false;
	}

}
