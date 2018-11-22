package net.dev.art.rank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setRank extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setrank")) {
			if (!(sender.hasPermission("greenz.Rank.set"))) {
				sender.sendMessage(NoPerm);
				return true;
			}

			if (args.length == 0) {
				sender.sendMessage(prefix + "§cUtilize: §7/setrank §r<Rank> (Player)");
			}
			if (args.length == 1) {

				if (sender instanceof Player) {

					Player p = (Player) sender;
					Rank r = null;
					try {
						r = RanksAPI.getRankByName(args[0]);

					} catch (Exception e) {
						mensagem(p, "§cRank §r" + args[0].toUpperCase() + " §cNão Existe");
					}
					if (r == null) {
						sender.sendMessage(prefix + "§cRank §r" + args[0].toUpperCase() + " §cNão Existe");
						return false;
					}
					String rprefix = r.getPrefix().replace("&", "§");

					try {
						RanksAPI.setRank(p.getName(), r);
						mensagem(p, "§aSeu Rank Foi Alterado Para " + rprefix + " §aCom Sucesso");
					} catch (Exception e) {
						mensagem(p, "§cRank §r" + args[0].toUpperCase() + " §cNão Existe");
					}
				} else {
					sender.sendMessage(prefix + "§cUtilize: §7/setRank §r<Rank> (Player)");
				}
			}

			if (args.length > 1) {
				Rank r = null;
				try {
					r = RanksAPI.getRankByName(args[0]);

				} catch (Exception e) {
					sender.sendMessage(prefix + "§cRank §r" + args[0].toUpperCase() + " §cNão Existe");
				}
				if (r == null) {
					sender.sendMessage(prefix + "§cRank §r" + args[0].toUpperCase() + " §cNão Existe");
					return false;
				}
				String rprefix = r.getPrefix().replace("&", "§");
				Player t = Bukkit.getPlayer(args[1]);
				if (t == null) {
					sender.sendMessage(prefix + "§cEsse Jogador Não Está Online");
					return true;
				}
				try {
					RanksAPI.setRank(t.getName(), r);
					sender.sendMessage(prefix + "§aVocê Alterou o Rank De " + t.getName() + " §2Para " + rprefix
							+ " §aCom Sucesso");
				} catch (Exception e) {
					sender.sendMessage(prefix + "§cRank §r" + args[0].toUpperCase() + " §cNão Existe");
				}
				if (t.isOnline()) {
					mensagem(t, "§aSeu Rank Foi Alterado Para " + rprefix);
				}
			}

		}
		return false;
	}

}
