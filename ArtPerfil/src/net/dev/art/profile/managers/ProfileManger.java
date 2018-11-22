package net.dev.art.profile.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.dev.art.eco.utils.Mensagens;
import net.dev.art.profile.apis.ProfileAPI;
import net.dev.green.grupos.APIs.GruposAPI;

public class ProfileManger extends Mensagens implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("perfil")) {
			if (GruposAPI.getPremissionLevel(GruposAPI.getGrupo(p)) < 3) {
				p.sendMessage(NoPerm);
				return true;
			}
			if (args.length == 0) {
				p.sendMessage("§e/perfil (jogador)");
			} else {
				String target = args[0];
				Player t = Bukkit.getPlayer(target);
				ProfileAPI.AbrirPerfil(p, target);
			}

		}
		return false;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;
		if (e.getInventory().getTitle().equalsIgnoreCase("§ePerfil")) {
			;
			e.setCancelled(true);
		}
	}

}
