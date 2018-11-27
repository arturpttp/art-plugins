package net.dev.art.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.dev.art.api.APIs.CalendarioAPI;
import net.dev.art.api.APIs.ItemsAPI;
import net.dev.art.essentials.Main;
import net.dev.art.essentials.apis.StaffAPI;
import net.dev.art.essentials.utils.Mensagens;
import net.dev.art.grupos.api.GruposAPI;

public class StaffManager extends Mensagens implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("staff")) {
			if (!p.hasPermission("staff.use") || !p.hasPermission(perm)) {
				p.sendMessage(NoPerm);
				return true;
			}

			Inventory inv = Bukkit.createInventory(null, 45, "§eEquipe");

			for (String k : Main.getStaff().getConfigurationSection("Staffs").getKeys(false)) {
				String status = "§cOffline";
				if (Bukkit.getPlayer(k) != null) {
					status = "§aOnline";
				}
				String grupo = GruposAPI.getGrupo(p.getName()).getPrefix();

				String ul = null;

				if (status.equalsIgnoreCase("§cOffline")) {
					ul = CalendarioAPI.getData(StaffAPI.getTempoOff(k)) + " ás "
							+ CalendarioAPI.getHoras(StaffAPI.getTempoOff(k));
				} else {
					ul = "§eAgora";
				}

				ItemStack skull = ItemsAPI.head("§e" + k, k, "§bGrupo: " + grupo, "§bStatus: " + status,
						"§bUltimo Login Há: §e" + ul);

				inv.setItem(StaffAPI.getSlot(inv), skull);

			}
			p.openInventory(inv);

		}
		return false;
	}

	@EventHandler
	public void onClickStaff(InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;

		if (e.getInventory().getTitle().equalsIgnoreCase("§eEquipe"))
			e.setCancelled(true);
	}

	@EventHandler
	void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("staff.use") || p.hasPermission(perm)) {
			Main.getStaff().set("Staffs." + p.getName(), System.currentTimeMillis());
			Main.getInstance().saveStaff();
		}
	}

}
