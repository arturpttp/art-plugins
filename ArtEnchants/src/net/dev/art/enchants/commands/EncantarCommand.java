package net.dev.art.enchants.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dev.art.enchants.Main;

public class EncantarCommand implements CommandExecutor {

	public static String prefix = Main.getInstance().prefix;
	public static String perm = "artchat.*";
	public static String NoPerm = prefix + "§cVoce Nao Tem Permissao Para Isso!";
	public static String NoPlayer = prefix + "§cComando Apenas Para Players!";

	public void mensagem(Player p, String mensagem) {
		p.sendMessage(prefix + mensagem);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("artencantar")) {
			if (!p.hasPermission("artencantar")) {
				p.sendMessage(NoPerm);
				return true;
			}

			if (args.length == 0) {
				mensagem(p, "§e/artencantar <encantamento>");
			} else {
				String enc = args[0];
				if (!enc.equalsIgnoreCase("explosive") && !enc.equalsIgnoreCase("smelting")) {
					mensagem(p, "§cEncantamento §f" + enc.toUpperCase() + " §cinexistente!");
					return true;
				}
				if (p.getItemInHand() == null) {
					mensagem(p, "§citem não encantavel!");
					return true;
				}
				List<String> lores = new ArrayList<>();
				ItemStack i = p.getItemInHand();
				ItemMeta m = i.getItemMeta();
				if (enc.equalsIgnoreCase("explosive")) {

					try {
						lores.add("§7Explosive I");
						m.setLore(lores);
						i.setItemMeta(m);

						i.addUnsafeEnchantment(Main.getInstance().enc, 1);

						mensagem(p, "§eItem encantado com §bsucesso§e!");
					} catch (Exception e) {
						mensagem(p, "§citem não encantavel!");
					}

				} else if (enc.equalsIgnoreCase("smelting")) {
					try {
						lores.add("§7Smelting I");
						m.setLore(lores);
						i.setItemMeta(m);

						i.addUnsafeEnchantment(Main.getInstance().smelting, 1);

						mensagem(p, "§eItem encantado com §bsucesso§e!");
					} catch (Exception e) {
						mensagem(p, "§citem não encantavel!");
					}
				}

			}

		}
		return false;
	}

}
