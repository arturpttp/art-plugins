package net.dev.art.essentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dev.art.essentials.utils.Mensagens;

public class ReloreHand extends Mensagens implements CommandExecutor {

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

		if (cmd.getName().equalsIgnoreCase("relore")) {
			if (!p.hasPermission("relore.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}

			if (args.length == 0) {
				mensagem(p, "§eUse: §6/relore <lore> - Use §7&§6 para colocar cores!");
			} else {
				List<String> lores = new ArrayList<>();
				String lore = append(args).replace("&", "§");
				lores.add(lore);
				ItemStack is = p.getItemInHand();
				ItemMeta mt = is.getItemMeta();
				mt.setLore(lores);
				is.setItemMeta(mt);

			}

		}
		return false;
	}

}
