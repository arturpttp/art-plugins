package net.dev.art.essentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.dev.art.essentials.utils.Mensagens;

public class Clear extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("clear")) {
			int qut = 0;
			for (int i = 0; i < p.getInventory().getSize(); i++) {
			    ItemStack is = p.getInventory().getItem(i);
			    if (is == null) continue;
			    qut += is.getAmount();
			    p.getInventory().setItem(i, null);
			    p.getInventory().setArmorContents(null);
			    
			}
			p.sendMessage(prefix + "§eVoce Limpou §b(x"+ qut +") §eItems Do Seu Inventario");

		}
		return false;
	}
}
