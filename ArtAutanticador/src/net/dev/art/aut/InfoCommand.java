package net.dev.art.aut;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.ItemsAPI;
import net.dev.art.api.APIs.MensagensAPI;
import net.dev.green.grupos.APIs.GruposAPI;

public class InfoCommand extends MensagensAPI implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPerm);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("autinfo")) {
			if (GruposAPI.getPremissionLevel(GruposAPI.getGrupo(p)) < 3) {
				p.sendMessage(NoPerm);
				return true;
			}
			if (args.length == 0) {
				Main.getInstance().mensagem(p,"§eUse:§6 /autinfo <player>");
			} else {
				String target = args[0];
				if (AutAPI.isRegistrado(target)) {
					String status = "§aOnline";
					if (!AutAPI.isLogado(target)) {
						status = "§cOffline";
					}
					String senha = AutAPI.getSenha(target);
					String ip = AutAPI.getIP(status);

					ItemStack head = ItemsAPI.head("§eInformações De: §b" + target, target, "§eSenha:§b " + senha,
							"§eIP: §b" + ip, "§eGrupo: " + GruposAPI.getPrefix(GruposAPI.getGrupo(target)));

					Inventory inv = Bukkit.createInventory(null, 9 * 3, "§eAutenticador Info");
					inv.setItem(13, head);
					p.openInventory(inv);
				} else {
					Main.getInstance().mensagem(p,"§cEsse Player Não Está Registrado!");
				}
			}

		}
		return false;
	}

}
