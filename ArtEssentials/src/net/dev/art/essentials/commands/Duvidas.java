package net.dev.art.essentials.commands;

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
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.dev.art.api.APIs.ItemsAPI;
import net.dev.art.essentials.Main;
import net.dev.art.essentials.apis.DuvidasAPI;
import net.dev.art.essentials.apis.Inventario;
import net.dev.art.essentials.objetos.Duvida;
import net.dev.art.essentials.utils.Mensagens;

public class Duvidas extends Mensagens implements CommandExecutor, Listener {

	HashMap<Player, String> chat = new HashMap<>();
	HashMap<Player, ItemStack> item = new HashMap<>();

	public static String append(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < args.length; x++) {
			stringBuilder.append(args[x] + " ");
		}
		return stringBuilder.toString();
	}

	void AbrirDuvidas(Player p) {
		Inventario inv = new Inventario(45, "§eDuvidas");
		Inventory invt = inv.getInventory();

		for (String ID : Main.getDuvidas().getConfigurationSection("Duvidas").getKeys(false)) {

			String duvida = Main.getDuvidas().getString("Duvidas." + ID + ".Duvida").replace("_", " ");
			String player = Main.getDuvidas().getString("Duvidas." + ID + ".Nick");
			Duvida d = new Duvida(player, duvida, ID);

			if (duvida.equalsIgnoreCase("") || player.equalsIgnoreCase("")) {
				inv.AbrirInv(p);
				return;
			}

			ItemStack skull = ItemsAPI.head("§eID: §b#" + ID + 1, player, "§eNick:§b " + player,
					"§eDuvida:§b " + duvida);

			invt.addItem(skull);

		}

		inv.AbrirInv(p);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return false;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("duvidas")) {

			if (args.length == 0) {
				if (p.hasPermission("duvidas.use") || p.hasPermission(perm)) {
					AbrirDuvidas(p);

					p.sendMessage("§e=-=-=-=-=-=-=-=-=-=");
					p.sendMessage("§3/duvida enviar <DUVIDA>");
					p.sendMessage("§e=-=-=-=-=-=-=-=-=-=");

				} else {
					p.sendMessage("§e=-=-=-=-=-=-=-=-=-=");
					p.sendMessage("§3/duvida enviar <DUVIDA>");
					p.sendMessage("§e=-=-=-=-=-=-=-=-=-=");

				}
			} else {
				if (args[0].equalsIgnoreCase("enviar")) {
					if (args.length <= 1) {
						p.sendMessage("§e=-=-=-=-=-=-=-=-=-=");
						p.sendMessage("§3/duvida enviar <DUVIDA>");
						p.sendMessage("§e=-=-=-=-=-=-=-=-=-=");
						return true;
					}
					String duvida = append(args);
					String ID = DuvidasAPI.gerarID();
					Duvida d = new Duvida(p.getName(), duvida, ID);

					Main.getDuvidas().set("Duvidas." + ID + ".Nick", p.getName());
					Main.getDuvidas().set("Duvidas." + ID + ".Duvida", duvida.replace("enviar ", ""));
					Main.getInstance().saveDuvidas();
					mensagem(p, "§eDuvida: §a" + duvida.replace("enviar ", "") + " §eEnviada Com Sucesso!");
				}
			}
		}
		return false;
	}

	@EventHandler
	public void onClickStaff(InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getTitle().equalsIgnoreCase("§eDuvidas")) {
			if (chat.containsKey(p)) {
				p.sendMessage("§cResponda A Duvida Anterior");
				p.closeInventory();
				return;
			}
			if (e.getCurrentItem().hasItemMeta()) {
				ItemStack i = e.getCurrentItem();
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				String t = meta.getLore().get(0).replace("§eNick:§b ", "");
				chat.put(p, t);
				item.put(p, i);
				String ID = meta.getDisplayName().replace("ID: §b", "");
				p.sendMessage("§7Você Esta Respondendo A Duvida De ID `§e" + ID + "§7`");
				p.sendMessage("§7Digite `§ccancelar§7` Para Cancelar");
				p.sendMessage("§7Ou Digite A Resposta No Chat");
				p.closeInventory();

			}
		}

	}

	@EventHandler
	void onChat(AsyncPlayerChatEvent e) {
		String msg = e.getMessage();
		Player p = e.getPlayer();
		if (chat.containsKey(p)) {
			e.setCancelled(true);
			Player t = Bukkit.getPlayer(chat.get(p));
			if (msg.equalsIgnoreCase("cancelar")) {
				p.sendMessage("§cVocê cancelou a resposta");
				chat.remove(p, chat.get(p));
				item.remove(p, item.get(p));
			} else {
				t.sendMessage("§b(RESPOSTA) -§e " + msg);
				p.sendMessage("§b(RESPONDIDA) -§e " + msg);
				chat.remove(p, chat.get(p));
				item.remove(p, item.get(p));
			}
		}
	}

}
