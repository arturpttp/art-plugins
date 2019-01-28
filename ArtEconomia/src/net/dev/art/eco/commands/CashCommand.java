package net.dev.art.eco.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.FormatarAPI;
import net.dev.art.core.utils.ArtLib;
import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.grupos.api.GruposAPI;

@SuppressWarnings("all")
public class CashCommand implements CommandExecutor, ArtLib {

	public static boolean hasPermission(Player p) {
		if (GruposAPI.hasPermission(p, "dono"))
			return true;
		else
			return false;
	}

	public String prefix = "§bArtEconomia §8» ";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			noPlayer(sender);
			return true;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("cash")) {
			if (args.length == 0) {
				CashAPI.sendHelp(p);
				p.sendMessage(" ");
				mensagem(p, "§eSeu Cash: §b" + CashAPI.getCashFormatado(p));
			}
			if (args.length >= 1) {
				String sb = args[0];

//				if (targetarget.getName().equalsIgnoreCase("set") || targetarget.getName().equalsIgnoreCase("add")
//						|| targetarget.getName().equalsIgnoreCase("remove") || targetarget.getName().equalsIgnoreCase("help")
//						|| targetarget.getName().equalsIgnoreCase("pay")) return true;
				Player target = null;
				if (sb.equalsIgnoreCase("set")) {
					if (args.length < 3) {
						p.sendMessage("§e/cash set (jogador) (quantia) §8 » §6setar os Cash do jogador");
						return true;
					}
					target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CashAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}
					Double quantia;
					try {
						quantia = Double.parseDouble(args[2]);
					} catch (NumberFormatException e) {
						p.sendMessage(prefix + "§cA quantidade que você informou não é válida!");
						p.playSound(p.getLocation(), Sound.CAT_MEOW, 1.0f, 0.5f);
						return true;
					}
					CashAPI.setCash(target, quantia);
					mensagem(p,
							"§eVocê setou os cash de `§b" + target.getName() + "§e` para `§b"
									+ FormatarAPI.doubleFormatado(quantia) + "§e` que atualmente está com `§b"
									+ CashAPI.getCashFormatado(target) + "§e`!");
					mensagem(target,
							"§e`§b" + p.getName() + "§e` setou seus cash para `§b"
									+ FormatarAPI.doubleFormatado(quantia) + "§e` você atualmente está com `§b"
									+ CashAPI.getCashFormatado(target) + "§e`!");
					return true;

				} else if (sb.equalsIgnoreCase("add")) {
					if (args.length < 3) {
						p.sendMessage("§e/cash add (jogador) (quantia) §8 » §6adicionar os Cash do jogador");
						return true;
					}
					target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CashAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}
					Double quantia;
					try {
						quantia = Double.parseDouble(args[2]);
					} catch (NumberFormatException e) {
						p.sendMessage(prefix + "§cA quantidade que você informou não é válida!");
						p.playSound(p.getLocation(), Sound.CAT_MEOW, 1.0f, 0.5f);
						return true;
					}
					CashAPI.addCash(target, quantia);
					mensagem(p,
							"§eVocê adicionou `§b" + FormatarAPI.doubleFormatado(quantia) + "§e` de `§b"
									+ target.getName() + "§e` que atualmente está com `§b"
									+ CashAPI.getCashFormatado(target) + "§e`!");
					mensagem(target,
							"§eVocê teve `§b" + FormatarAPI.doubleFormatado(quantia)
									+ "§e` adicionado a sua conta por `§b" + target.getName()
									+ "§e` você atualmente está com `§b" + CashAPI.getCashFormatado(target) + "§e`!");
					return true;

				} else if (sb.equalsIgnoreCase("remove")) {
					if (args.length < 3) {
						p.sendMessage("§e/cash remove (jogador) (quantia) §8 » §6remover os Cash do jogador");
						return true;
					}
					target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CashAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}
					Double quantia;
					try {
						quantia = Double.parseDouble(args[2]);
					} catch (NumberFormatException e) {
						p.sendMessage(prefix + "§cA quantidade que você informou não é válida!");
						p.playSound(p.getLocation(), Sound.CAT_MEOW, 1.0f, 0.5f);
						return true;
					}
					mensagem(p,
							"§eVocê removeu `§b" + FormatarAPI.doubleFormatado(quantia) + "§e` de `§b"
									+ target.getName() + "§e` que atualmente está com `§b"
									+ CashAPI.getCashFormatado(target) + "§e`!");
					mensagem(target,
							"§eVocê teve `§b" + FormatarAPI.doubleFormatado(quantia)
									+ "§e` removido da sua conta por `§b" + target.getName()
									+ "§e` você atualmente está com `§b" + CashAPI.getCashFormatado(target) + "§e`!");
					CashAPI.removeCash(target, quantia);

					return true;

				} else if (sb.equalsIgnoreCase("pay")) {
					if (args.length < 3) {
						p.sendMessage("§e/cash pay (jogador) (quantia) §8 » §6pagar para um player");
						return true;
					}
					target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CashAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}
					Double quantia;
					try {
						quantia = Double.parseDouble(args[2]);
					} catch (NumberFormatException e) {
						p.sendMessage(prefix + "§cA quantidade que você informou não é válida!");
						p.playSound(p.getLocation(), Sound.CAT_MEOW, 1.0f, 0.5f);
						return true;
					}
					CashAPI.payCash(p, target, quantia);
					mensagem(p,
							"§eVocê pagou `§b" + FormatarAPI.doubleFormatado(quantia) + "§e` para `§b"
									+ target.getName() + "§e`! que atualmente está com `§b"
									+ CashAPI.getCashFormatado(target) + "§e`");
					mensagem(target,
							"§eVocê recebeu `§b" + FormatarAPI.doubleFormatado(quantia) + "§e` de `§b"
									+ target.getName() + "§e` você atualmente está com `§b"
									+ CashAPI.getCashFormatado(target) + "§e`!");
					return true;

				} else if (sb.equalsIgnoreCase("help")) {
					CashAPI.sendHelp(p);
				} else {
					target = Bukkit.getPlayer(sb);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CashAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem(p, "§cEste Player Não Existe!");
						return true;
					}
					mensagem(p, "§eCash De `§b" + target.getName() + "§e`: §b" + CashAPI.getCashFormatado(target));
					return true;

				}

			}

		}
		return false;

	}

}
