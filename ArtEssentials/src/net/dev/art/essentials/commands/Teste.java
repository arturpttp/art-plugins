package net.dev.art.essentials.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.ItemsAPI;
import net.dev.art.api.APIs.Paginas;
import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.essentials.utils.Mensagens;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.rank.RanksAPI;

public class Teste extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("online")) {
			if (GruposAPI.getGrupo(p.getName()).getPermissions().contains("dono")) {

				ArrayList<ItemStack> items = new ArrayList<ItemStack>();

				for (Player on : Bukkit.getOnlinePlayers()) {
					String rank = RanksAPI.getCurrentRank(on).getPrefix().replace("&", "§");
					String grupo = GruposAPI.getGrupo(p.getName()).getPrefix();;
					String cash = CashAPI.getCashFormatado(on);
					String coin = CoinsAPI.getCoinsFormatado(on);
					ItemStack item = ItemsAPI.head("§a" + on.getName(), on.getName(), "§eRank: " + rank,
							"§eGrupo: " + grupo, "§eCoins: §b" + coin, "§eCash: §b" + cash);
					items.add(item);
				}
				Paginas pa = new Paginas(items, "§bPlayers online", p);
			} else {
				p.sendMessage(NoPerm);
			}
		}
		return false;
	}

}
