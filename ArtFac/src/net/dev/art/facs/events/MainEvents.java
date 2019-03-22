package net.dev.art.facs.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.dev.art.core.managers.Menu;
import net.dev.art.core.managers.Menu.MenuItemClick;
import net.dev.art.core.managers.Menu.MenuSize;
import net.dev.art.core.objects.ArtItem;
import net.dev.art.core.utils.ArtLib;
import net.dev.art.core.utils.Configs;
import net.dev.art.facs.Main;
import net.dev.art.facs.manager.ArtFacManager;
import net.dev.art.facs.manager.FactionsManager;
import net.dev.art.facs.manager.MySQLManager;
import net.dev.art.facs.manager.PlayersManager;
import net.dev.art.facs.menus.FactionsMenu;
import net.dev.art.facs.objects.Faction;
import net.dev.art.facs.objects.FactionPlayer;

public class MainEvents implements Listener, ArtLib {

	@EventHandler
	void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!Main.players.containsKey(p.getName())) {
			if (!MySQLManager.contains("factionplayerdb", "nick", p.getName())) {
				int poder = Main.getInstance().config.getInt("Padrao.PoderInicial");
				int poderMax = Main.getInstance().config.getInt("Padrao.PoderMaxInicial");
				FactionPlayer fp = new FactionPlayer("", p.getName(), "", 0, 0, poder, poderMax);
				Main.players.put(p.getName(), fp);
				mensagem("§eNovo player `§b" + p.getName() + "§e` foi inserido na database.");
			}
		} else {
			FactionPlayer fp = Main.players.get(p.getName());
			fp.setLast();
			mensagem(fp.getOnline() + "");
			if (fp.hasFaction()) {
				fp.getFac().addOnline(fp.getName());
				fp.getFac().broadcast("§b" + fp.getFaction() + "§8 » §eO jogador §b" + fp.getName() + " §eentrou.");
			}
		}
	}

	@EventHandler
	void quit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (Main.players.containsKey(p.getName())) {
			FactionPlayer fp = Main.players.get(p.getName());
			mensagem(fp.getOnline() + "");
			if (fp.hasFaction()) {
				fp.getFac().removeOnline(fp.getName());
				fp.getFac().broadcast("§b" + fp.getFaction() + "§8 » §cO jogador §f" + fp.getName() + " §csaiu.");
			}
		} else {

		}

	}

	@EventHandler
	void chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		int tamanho = e.getMessage().length();
		if (FactionsMenu.criar_fac.containsKey(p.getName())) {
			FactionPlayer fp = Main.players.get(p.getName());
			e.setCancelled(true);
			if (FactionsMenu.criar_fac.get(p.getName()) == 0) {
				if (e.getMessage().contains("cancelar")) {
					p.sendMessage("§cOperação cancelada com sucesso!");
					FactionsMenu.criar_fac.remove(p.getName());
					return;
				}
				if (tamanho > 16) {
					p.sendMessage("§cO nome da facção deve conter no maximo 16 letras!");
					return;
				} else if (tamanho <= 3) {
					p.sendMessage("§cO nome da facção deve conter no minimo 4 letras!");
					return;
				}
				MySQLManager.getAllFactions().forEach(f -> {
					if (f.getNome().equalsIgnoreCase(e.getMessage())) {
						p.sendMessage("§cA Facção §f`" + e.getMessage() + "` §cJá existe!");
						p.sendMessage(" ");
						p.sendMessage(" ");
						p.sendMessage("§7Digite o §f`nome`§7 Da sua facção!");
						p.sendMessage("§7Digite §c`cancelar` §7Para cancelar a operação!");
						return;
					}
				});

				FactionsMenu.nome.put(p.getName(), e.getMessage());
				FactionsMenu.criar_fac.put(p.getName(), 1);
				p.sendMessage("§7Agora digite a §f`tag`§7 Da sua facção!");
				p.sendMessage("§7Digite §c`cancelar` §7Para cancelar a operação!");
			} else if (FactionsMenu.criar_fac.get(p.getName()) == 1) {
				if (e.getMessage().contains("cancelar")) {
					p.sendMessage("§cOperação cancelada com sucesso!");
					FactionsMenu.criar_fac.remove(p.getName());
					return;
				}
				if (tamanho != 3) {
					FactionsMenu.nome.put(p.getName(), e.getMessage());
					p.sendMessage("§cA tag da facção deve conter no 3 letras!");
					return;
				}
				MySQLManager.getAllFactions().forEach(fac -> {
					if (fac.getTag().equalsIgnoreCase(e.getMessage())) {
						p.sendMessage("§cA Tag §f`" + e.getMessage() + "` §cJá existe!");
						p.sendMessage(" ");
						p.sendMessage("§7Agora digite a §f`tag`§7 Da sua facção!");
						p.sendMessage("§7Digite §c`cancelar` §7Para cancelar a operação!");
						return;
					}
				});
				FactionsMenu.tag.put(p.getName(), e.getMessage());

				Menu inv = new Menu("§fCriar a facção", MenuSize.THREE_LINES);
				ArtItem confirmar = new ArtItem(Material.WOOL).setData((short) 5).nome("§2Confirmar")
						.lore("§aSim desejo criar a facção", "§f[" + FactionsMenu.tag.get(p.getName()).toUpperCase()
								+ "] §f" + FactionsMenu.nome.get(p.getName()) + "");
				ArtItem negar = new ArtItem(Material.WOOL).setData((short) 14).nome("§4Cancelar operação")
						.lore("§cNão, não quero criar a facção");
				inv.setItem(11, confirmar, new MenuItemClick() {
					public void onClick(InventoryClickEvent e) {
						FactionsMenu.criar_fac.remove(p.getName());
						FactionsManager.newFac(FactionsMenu.nome.get(p.getName()),
								FactionsMenu.tag.get(p.getName()).toUpperCase(), p.getName());
						e.getWhoClicked().closeInventory();
						p.sendMessage("§aFacção criada com sucesso!");
						Main.getInstance().playersCF.saveConfig();
					}
				});
				inv.setItem(15, negar, new MenuItemClick() {
					public void onClick(InventoryClickEvent e) {
						e.getWhoClicked().closeInventory();
						p.sendMessage("§cOperação cancelada com sucesso!");
						FactionsMenu.criar_fac.remove(p.getName());
					}
				});
				inv.open(p);
			}
		}
	}

}
