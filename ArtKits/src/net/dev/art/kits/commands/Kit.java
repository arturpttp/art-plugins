package net.dev.art.kits.commands;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.API;
import net.dev.art.api.APIs.CalendarioAPI;
import net.dev.art.kits.Main;
import net.dev.art.kits.Mensagens;
import net.dev.art.kits.apis.KitsAPI;
import net.dev.green.grupos.APIs.GruposAPI;
import net.dev.green.grupos.APIs.GruposAPI.GruposTipos;

public class Kit extends Mensagens implements CommandExecutor {

	public static long getDelay(String kit, Player p) {
		long delay1 = Main.d.getLong("Delay." + p.getName() + "." + KitsAPI.getKitByName(kit));
		int dl = KitsAPI.getKitByName(kit).getDelay() * 60 * 1000;
		return delay1 + dl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("kit")) {
			if (args.length == 0) {
				p.sendMessage(prefix + "§6Use: §e/kit <kit>");
			} else {
				String kit = args[0];
				if (KitsAPI.existKit(kit)) {
					if (!Main.d.contains("Delay." + p.getName() + "." + KitsAPI.getKitByName(kit).getName())) {
						if (KitsAPI.getKitByName(kit).getItens().size() <= API.getReamingSlot(p)) {
							KitsAPI.darKit(p, kit);
							KitsAPI.removeDelay(p.getName(), kit);
							KitsAPI.addDelay(p.getName(), kit);
							p.sendMessage(prefix + "§eVocê Pegou o Kit: §b" + KitsAPI.getKitByName(kit).getName());
						} else {
							p.sendMessage(
									prefix + "§cVocê não tem espaço no inventario suficiente para pegar esse kit");
						}
					} else {
						long delay = getDelay(kit, p);
						if (delay < System.currentTimeMillis()) {
							p.sendMessage(prefix + "§cVocê so pode pegar outro kit: §f"
									+ KitsAPI.getKitByName(kit).getName() + " §cem §f" + CalendarioAPI.getData(delay)
									+ " §cás §f" + CalendarioAPI.getHoras(delay));
						} else {
							if (KitsAPI.getKitByName(kit).getItens().size() <= API.getReamingSlot(p)) {
								KitsAPI.darKit(p, kit);
								KitsAPI.removeDelay(p.getName(), kit);
								KitsAPI.addDelay(p.getName(), kit);
								p.sendMessage(prefix + "§eVocê Pegou o Kit: §b" + KitsAPI.getKitByName(kit).getName());
								p.sendMessage(prefix + "§c" + Main.d
										.getLong("Delay." + p.getName() + "." + KitsAPI.getKitByName(kit).getName()));
							} else {
								p.sendMessage(
										prefix + "§cVocê não tem espaço no inventario suficiente para pegar esse kit");
							}
						}
					}
				} else {
					p.sendMessage(prefix + "§cO Kit: §f" + kit + " §cnão existe!");
				}
			}

		}
		return false;
	}

}
