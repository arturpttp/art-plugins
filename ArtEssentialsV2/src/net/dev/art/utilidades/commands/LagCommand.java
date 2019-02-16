package net.dev.art.utilidades.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.Paginas;
import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.objects.ArtItem;
import net.dev.art.utilidades.mangers.LagManager;

public class LagCommand extends ArtCommand {

	public LagCommand() {
		super("clearlagg");
		addAliases("clearlag", "lagg");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (!hasPermission(p, "lagg.clear")) {
			return false;
		}
		if (args.length == 0) {
			mensagem(p, LagManager.prefix + "§cUtilize: §f/lagg <anuncios> -  limpar os mobs e itens dropados!");
		} else {
			if (args.length == 1) {
				int qnt = 5;
				try {
					qnt = Integer.valueOf(args[0]);
				} catch (NumberFormatException e) {
					mensagem(p, LagManager.prefix + "§cUtilize um numero correto");
				}
				if (args[0].equalsIgnoreCase("NaN")) {
					mensagem(p, LagManager.prefix + "§cBurlando o sistema?");
					return true;
				}
				if (qnt > 5) {
					mensagem(p, LagManager.prefix + "§cUtilize um numero menor que 5");
				} else if (qnt <= 0) {
					mensagem(p, LagManager.prefix + "§cUtilize um numero maior que 0");
				}
				LagManager.anuncio(qnt);
			} else {
				mensagem(p, LagManager.prefix + "§cUtilize: 1§f/lagg <anuncios> -  limpar os mobs e itens dropados!");
			}
		}
		return false;
	}

}
