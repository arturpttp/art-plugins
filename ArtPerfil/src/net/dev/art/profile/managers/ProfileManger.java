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

import net.dev.art.core.utils.ArtLib;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.profile.apis.ProfileAPI;

public class ProfileManger implements CommandExecutor, Listener, ArtLib {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			noPerm(sender);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("perfil")) {
			if (GruposAPI.getGrupo(p.getName()).getPermissions().contains("dono")) {
				noPerm(p);
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
