package net.dev.art.hab.commands;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

import net.dev.art.api.APIs.ItemsAPI;
import net.dev.art.hab.Main;
import net.dev.art.hab.utils.InventoryAPI;
import net.dev.art.hab.utils.Mensagens;

public class Habilidades extends Mensagens implements CommandExecutor, Listener {

	public int getDataByInvHash(Player p, String hab, HashMap<Player, String> hash) {
		return Main.getInstance().inv.get(hash);
	}

	public void AbrirhabilidadesInv(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, "§aHabilidades");

		inv.setItem(InventoryAPI.getSlot(inv), ItemsAPI.vidro(Material.STAINED_GLASS_PANE, "§cBlindado", 14, 1,
				"§cHabilidade faz com que ao matar um jogador sua armadura upe um nivel de proteção!"));
		inv.setItem(InventoryAPI.getSlot(inv), ItemsAPI.vidro(Material.STAINED_GLASS_PANE, "§cTrevo da sorte", 14, 1,
				"§cHabilidade faz com que ao quebrar um bloco você tenha a chance de ganhar uma maçã dourada!"));
		inv.setItem(InventoryAPI.getSlot(inv), ItemsAPI.vidro(Material.STAINED_GLASS_PANE, "§cFerreiro", 14, 1,
				"§cHabilidade faz com que ao clicar na vigorna repare o item na sua mao!"));

		p.openInventory(inv);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("habilidades")) {
			AbrirhabilidadesInv(p);
		}
		return false;
	}


	public int getValue(String habilidade, Player p) {
		if(!Main.getInstance().getConfig().getBoolean(p.getName()+"."+habilidade)) {
			return 14;
		}else if(Main.getInstance().getConfig().getBoolean(p.getName()+"."+habilidade)) {
			return 5;
		}
		return 1;
		
	}
	
	@EventHandler
	void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
			return;
		}
		short datablindado = 14;
		if (Main.getInstance().getConfig().getBoolean(p.getName() + ".Blindado") == true) {
			datablindado = 5;
		}

		short datatds = 14;
		if (Main.getInstance().getConfig().getBoolean(p.getName() + ".TrevoDaSorte") == true) {
			datatds = 5;
		}

		short dataferreiro = 14;
		if (Main.getInstance().getConfig().getBoolean(p.getName() + ".Ferreiro") == true) {
			dataferreiro = 5;
		}

		if (e.getInventory().getTitle().equalsIgnoreCase("§aHabilidades")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBlindado")) {
				if (datablindado == 14) {
					Main.getInstance().getConfig().set(p.getName() + ".Blindado", true);
					Main.getInstance().saveConfig();
				} else if (datablindado == 5) {
					Main.getInstance().getConfig().set(p.getName() + ".Blindado", false);
					Main.getInstance().saveConfig();
				}
				e.getCurrentItem().setDurability((short) datablindado);
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				meta.setDisplayName("§aBlindado");
				meta.setLore(
						Arrays.asList(meta.getLore().toString().replace("§c", "§a").replace("[", "").replace("]", "")));
				e.getCurrentItem().setItemMeta(meta);
			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTrevo da sorte")) {
				if (datatds == 14) {
					Main.getInstance().getConfig().set(p.getName() + ".TrevoDaSorte", true);
					Main.getInstance().saveConfig();
				} else if (datatds == 5) {
					Main.getInstance().getConfig().set(p.getName() + ".TrevoDaSorte", false);
					Main.getInstance().saveConfig();
				}
				e.getCurrentItem().setDurability((short) datatds);
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				meta.setDisplayName("§aTrevo da sorte");
				meta.setLore(
						Arrays.asList(meta.getLore().toString().replace("§c", "§a").replace("[", "").replace("]", "")));
				e.getCurrentItem().setItemMeta(meta);
			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cFerreiro")) {
				if (dataferreiro == 14) {
					Main.getInstance().getConfig().set(p.getName() + ".Ferreiro", true);
					Main.getInstance().saveConfig();
				} else if (dataferreiro == 5) {
					Main.getInstance().getConfig().set(p.getName() + ".Ferreiro", false);
					Main.getInstance().saveConfig();
				}
				e.getCurrentItem().setDurability((short) dataferreiro);
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				meta.setDisplayName("§aFerreiro");
				meta.setLore(
						Arrays.asList(meta.getLore().toString().replace("§c", "§a").replace("[", "").replace("]", "")));
				e.getCurrentItem().setItemMeta(meta);
			}
		}
	}

}
