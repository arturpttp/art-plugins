package net.dev.art.temp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import net.dev.art.api.APIs.ArtItem;
import net.dev.art.temp.Spawner;
import net.dev.art.temp.apis.SpawnersAPI;
import net.dev.art.temp.utils.Mensagens;

public class SpawnerCommand extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("spawner")) {
			if (args.length < 3) {
				mensagem(p, "§e/spawner give (jogador) {tipo} [qnatidade]");
			} else {
				int numero = 1;
				Player target = Bukkit.getPlayer(args[1]);
				String sbc = args[0];
				String tipo = args[2];
				if (target == null) {
					mensagem(p, "§cEsse player está offline");
					return true;
				}
				if (sbc.equalsIgnoreCase("give")) {
					if (args[1].equalsIgnoreCase(target.getName())) {
						try {
							numero = Integer.valueOf(args[3]);
						} catch (NumberFormatException e) {
							mensagem(p, "§f" + args[3] + " §cNão é um numero");
						}
						Spawner spwaner = new Spawner(p.getLocation(), SpawnersAPI.getTypeByNames(tipo));
						target.getInventory().addItem(spwaner.getItem(numero));
						mensagem(p, "§aVocê deu §b" + numero + "§a de spawners de §b"
								+ spwaner.getType().toString().replace("_", " ") + " §a para §b" + target.getName());
					}

				}

			}
		}
		return false;
	}

}
