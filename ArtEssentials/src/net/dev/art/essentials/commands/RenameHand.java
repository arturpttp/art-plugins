package net.dev.art.essentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dev.art.essentials.utils.Mensagens;

public class RenameHand extends Mensagens implements CommandExecutor {

	public static String append(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < args.length; x++) {
			stringBuilder.append(args[x] + " ");
		}
		return stringBuilder.toString();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("rename")) {
			if (!p.hasPermission("rename.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}

			if (args.length == 0) {
				mensagem(p, "§eUse: §6/rename <NOME> - Use §7&§6 para colocar cores!");
			} else {
				
				String name = append(args).replace("&", "§");
				ItemStack is = p.getItemInHand();
				ItemMeta mt = is.getItemMeta();
				mt.setDisplayName(name);
				is.setItemMeta(mt);
			}

		}
		return false;
	}

}
