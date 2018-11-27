package net.dev.art.essentials.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.essentials.Main;
import net.dev.art.essentials.objetos.Warp;
import net.dev.art.essentials.utils.Mensagens;
import net.dev.art.grupos.api.GruposAPI;

public class WarpCommand extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("warp")) {
			if (args.length == 0) {
				mensagem(p, "§e/warp §6(Nome)§7 » §bTeleportar-se até uma WARP existente!");
				if (GruposAPI.getGrupo(p.getName()).getPermissions().contains("dono")) {
					mensagem(p, "§e/warp setar §6(Nome)§7 » §bCriar uma WARP!");
					mensagem(p, "§e/warp deletar §6(Nome)§7 » §bdeletar uma WARP existente!");
				}
			} else {
				if (args.length == 1) {
					String name = args[0];
					if (!Main.warpsHash.containsKey(name.toLowerCase())) {
						mensagem(p, "§cessa warp não existe!");
						return true;
					}
					Warp w = Main.warpsHash.get(name);
					new BukkitRunnable() {
						int tempo = 3;

						public void run() {
							if (tempo >= 1) {
								p.sendTitle("§aWarps",
										"§eVocê sera teleportado para warp `§b" + w.getName() + "§e` em §b" + tempo);
								tempo--;
							} else if (tempo == 0) {
								p.sendTitle("§aWarps", "§eTeleportando...");
								p.teleport(w.getLocation());
								tempo--;
							}

						}
					}.runTaskTimer(Main.getInstance(), 20, 20);
					return true;
				}
				String sc = args[0];
				String name = args[1];

				if (sc.equalsIgnoreCase("setar") || sc.equalsIgnoreCase("set")) {
					if (Main.warpsHash.containsKey(name.toLowerCase())) {
						mensagem(p, "§cessa warp já existe!");
						return true;
					}
					char aspa = '`';
					Warp w = new Warp(name.toLowerCase(), p.getLocation());
					Main.warpsHash.put(name.toLowerCase(), w);
					mensagem(p, "§eWarp " + aspa + "§b" + name + "§e" + aspa + " criada com §2sucesso");
				} else if (sc.equalsIgnoreCase("deletar") || sc.equalsIgnoreCase("del")) {
					if (!Main.warpsHash.containsKey(name.toLowerCase())) {
						mensagem(p, "§cessa warp não existe!");
						return true;
					}
					char aspa = '`';
					Main.warpsHash.remove(name.toLowerCase());

					new File(Main.getInstance().getDataFolder(), "/Warps/" + name.toLowerCase() + ".yml").delete();

					mensagem(p, "§cWarp " + aspa + "§b" + name + "§c" + aspa + " deletada com sucesso");
				}

			}

		}

		return false;
	}

}
