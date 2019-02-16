package net.dev.art.utilidades.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.Paginas;
import net.dev.art.core.managers.ArtCommand;
import net.dev.art.core.objects.ArtItem;
import net.dev.art.utilidades.Main;
import net.dev.art.utilidades.mangers.LagManager;

public class GMCommand extends ArtCommand {

	public GMCommand() {
		super("gm");
		addAliases("gamemode");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		if (!hasPermission(p, "gm.use")) {
			mensagem(p, Main.getInstance().getPrefix() + "§fComando desconhecido utilize /help");
			return false;
		}
		if (args.length == 0) {
			if (p.getGameMode() == GameMode.SURVIVAL) {
				p.setGameMode(GameMode.CREATIVE);
				mensagem(p, Main.getInstance().getPrefix() + "§eVocê alterou seu modo de jogo para: §fCRIATIVO");
			} else if (p.getGameMode() == GameMode.CREATIVE) {
				p.setGameMode(GameMode.SURVIVAL);
				mensagem(p, Main.getInstance().getPrefix() + "§eVocê alterou seu modo de jogo para: §fSOBREVIVENCIA");
			} else if (p.getGameMode() == GameMode.ADVENTURE) {
				p.setGameMode(GameMode.CREATIVE);
				mensagem(p, Main.getInstance().getPrefix() + "§eVocê alterou seu modo de jogo para: §fCRIATIVO");
			} else if (p.getGameMode() == GameMode.SPECTATOR) {
				p.setGameMode(GameMode.CREATIVE);
				mensagem(p, Main.getInstance().getPrefix() + "§eVocê alterou seu modo de jogo para: §fCRIATIVO");
			}
		} else {

			if (args.length == 1) {
				// alterar com nome ou numero EX: (1, creiativo) // alterar modo de outra pessoa
				if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")
						|| args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("sobrevivencia")) {
					p.setGameMode(GameMode.SURVIVAL);
					mensagem(p,
							Main.getInstance().getPrefix() + "§eVocê alterou seu modo de jogo para: §fSOBREVIVENCIA");
				} else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")
						|| args[0].equalsIgnoreCase("criativo") || args[0].equalsIgnoreCase("creative")) {
					p.setGameMode(GameMode.CREATIVE);
					mensagem(p, Main.getInstance().getPrefix() + "§eVocê alterou seu modo de jogo para: §fCRIATIVO");
				} else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")
						|| args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("aventura")) {
					p.setGameMode(GameMode.ADVENTURE);
					mensagem(p, Main.getInstance().getPrefix() + "§eVocê alterou seu modo de jogo para: §fAVENTURA");
				} else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")
						|| args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("spectador")) {
					p.setGameMode(GameMode.SPECTATOR);
					mensagem(p, Main.getInstance().getPrefix() + "§eVocê alterou seus modo de jogo para: §fSPECTADOR");
				}
			} else if (args.length == 2) {
				Player t = Bukkit.getPlayer(args[1]);
				if (t == null) {
					mensagem(p, Main.getInstance().getPrefix() + "§cPlayer não existente!");
					return true;
				}
				if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")
						|| args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("sobrevivencia")) {
					t.setGameMode(GameMode.SURVIVAL);
					mensagem(t, Main.getInstance().getPrefix() + "§eVocê alterou o modo de jogo de `§f" + args[1]
							+ "§e` para: §fSOBREVIVENCIA");
					mensagem(p, Main.getInstance().getPrefix() + "§eVocê teve seu modo de jogo alterado por `§f"
							+ p.getName() + "§e` para: §fSOBREVIVENCIA");
				} else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")
						|| args[0].equalsIgnoreCase("criativo") || args[0].equalsIgnoreCase("creative")) {
					t.setGameMode(GameMode.CREATIVE);
					mensagem(t, Main.getInstance().getPrefix() + "§eVocê alterou o modo de jogo de `§f" + args[1]
							+ "§e` para: §fCRIATIVO");
					mensagem(p, Main.getInstance().getPrefix() + "§eVocê teve seu modo de jogo alterado por `§f"
							+ p.getName() + "§e` para: §fCRIATIVO");
				} else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")
						|| args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("aventura")) {
					t.setGameMode(GameMode.ADVENTURE);
					mensagem(t, Main.getInstance().getPrefix() + "§eVocê alterou o modo de jogo de `§f" + args[1]
							+ "§e` para: §fAVENTURA");
					mensagem(p, Main.getInstance().getPrefix() + "§eVocê teve seu modo de jogo alterado por `§f"
							+ p.getName() + "§e` para: §fAVENTURA");
				} else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")
						|| args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("spectador")) {
					t.setGameMode(GameMode.SPECTATOR);
					mensagem(t, Main.getInstance().getPrefix() + "§eVocê alterou o modo de jogo de `§f" + args[1]
							+ "§e` para: §fSPECTADOR");
					mensagem(p, Main.getInstance().getPrefix() + "§eVocê teve seu modo de jogo alterado por `§f"
							+ p.getName() + "§e` para: §fSPECTADOR");
				}
			} else {
				mensagem(Main.getInstance().getPrefix() + "§cUtilize:§f /gm [gamemode]");
			}
		}
		return false;
	}

}
