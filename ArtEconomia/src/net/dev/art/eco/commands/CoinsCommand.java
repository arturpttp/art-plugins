package net.dev.art.eco.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.FormatarAPI;
import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.eco.apis.EconomiaAPI;
import net.dev.art.eco.utils.Mensagens;
import net.dev.green.grupos.APIs.GruposAPI;

@SuppressWarnings("all")
public class CoinsCommand extends Mensagens implements CommandExecutor {

	public static boolean hasPermission(Player p) {
		if (GruposAPI.getPremissionLevel(GruposAPI.getGrupo(p)) >= 3)
			return true;
		else
			return false;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return true;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("Coins")) {
			if (args.length == 0) {
				CoinsAPI.sendHelp(p);
				p.sendMessage(" ");
				mensagem(p, "§eSeu Coins: §b" + CoinsAPI.getCoinsFormatado(p));
			}
			if (args.length >= 1) {
				String sb = args[0];

//				if (targetarget.getName().equalsIgnoreCase("set") || targetarget.getName().equalsIgnoreCase("add")
//						|| targetarget.getName().equalsIgnoreCase("remove") || targetarget.getName().equalsIgnoreCase("help")
//						|| targetarget.getName().equalsIgnoreCase("pay")) return true;
				Player target = null;
				if (sb.equalsIgnoreCase("set")) {
					if (args.length < 3) {
						p.sendMessage("§e/Coins set (jogador) (quantia) §8 » §6setar os Coins do jogador");
						return true;
					}
					target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
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
					CoinsAPI.setCoins(target, quantia);
					mensagem(p,
							"§eVocê setou os Coins de `§b" + target.getName() + "§e` para `§b"
									+ FormatarAPI.doubleFormatado(quantia) + "§e` que atualmente está com `§b"
									+ CoinsAPI.getCoinsFormatado(target) + "§e`!");
					mensagem(target,
							"§e`§b" + p.getName() + "§e` setou seus Coins para `§b"
									+ FormatarAPI.doubleFormatado(quantia) + "§e` você atualmente está com `§b"
									+ CoinsAPI.getCoinsFormatado(target) + "§e`!");
					return true;

				} else if (sb.equalsIgnoreCase("add")) {
					if (args.length < 3) {
						p.sendMessage("§e/Coins add (jogador) (quantia) §8 » §6adicionar os Coins do jogador");
						return true;
					}
					target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
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
					CoinsAPI.addCoins(target, quantia);
					mensagem(p,
							"§eVocê adicionou `§b" + FormatarAPI.doubleFormatado(quantia) + "§e` de `§b"
									+ target.getName() + "§e` que atualmente está com `§b"
									+ CoinsAPI.getCoinsFormatado(target) + "§e`!");
					mensagem(target,
							"§eVocê teve `§b" + FormatarAPI.doubleFormatado(quantia)
									+ "§e` adicionado a sua conta por `§b" + target.getName()
									+ "§e` você atualmente está com `§b" + CoinsAPI.getCoinsFormatado(target) + "§e`!");
					return true;

				} else if (sb.equalsIgnoreCase("remove")) {
					if (args.length < 3) {
						p.sendMessage("§e/Coins remove (jogador) (quantia) §8 » §6remover os Coins do jogador");
						return true;
					}
					target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
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
									+ CoinsAPI.getCoinsFormatado(target) + "§e`!");
					mensagem(target, "§eVocê teve `§b" + FormatarAPI.doubleFormatado(quantia)
							+ "§e` removido da sua conta por `§b" + target.getName() + "!");
					CoinsAPI.removeCoins(target, quantia);

					return true;

				} else if (sb.equalsIgnoreCase("pay")) {
					if (args.length < 3) {
						p.sendMessage("§e/Coins pay (jogador) (quantia) §8 » §6pagar para um player");
						return true;
					}
					target = Bukkit.getPlayer(args[1]);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
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
					CoinsAPI.payCoins(p, target, quantia);
					mensagem(p, "§eVocê pagou `§b" + FormatarAPI.doubleFormatado(quantia) + "§e` para `§b"
							+ target.getName() + "§e`!");
					mensagem(target, "§eVocê recebeu `§b" + FormatarAPI.doubleFormatado(quantia) + "§e` de "
							+ target.getName() + "§e`!");
					return true;

				} else if (sb.equalsIgnoreCase("help")) {
					CoinsAPI.sendHelp(p);
				} else {
					target = Bukkit.getPlayer(sb);
					if (target == null) {
						mensagem(p, "§cEsse Player Está Offline!");
						return true;
					}
					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
						return true;
					}

					if (!CoinsAPI.config.contains(target.getName().toLowerCase())) {
						mensagem("§cEste Player Não Existe!", p);
						return true;
					}
					mensagem(p, "§eCoins De `§b" + target.getName() + "§e`: §b" + CoinsAPI.getCoinsFormatado(target));
					return true;

				}

			}

		}
		return false;

	}

}
