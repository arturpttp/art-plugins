package net.dev.art.facs.commands;

import net.dev.art.core.managers.*;
import net.dev.art.core.managers.Menu.MenuItemClick;
import net.dev.art.core.managers.Menu.MenuSize;
import net.dev.art.core.objects.ArtItem;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.*;
import net.dev.art.facs.objects.*;
import net.dev.art.facs.Main;
import net.dev.art.facs.*;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.facs.menus.*;
import net.dev.art.eco.apis.*;
import net.dev.art.facs.enums.*;
import net.dev.art.facs.manager.*;

public class FactionsCommand extends ArtCommand {
	public FactionsCommand() {
		super("faction");
		addAliases("f", "facs", "fac", "factions");
	}

	public boolean execute(final CommandSender sender, final String lb, final String[] args) {
		if (!isPlayer(sender)) {
			return true;
		}
		final Player p = (Player) sender;
		if (args.length == 0) {
			final FactionPlayer fp = Main.players.get(p.getName());
			if (fp.hasFaction()) {
				new HasFactionMenu(fp).open((HumanEntity) p);
			} else {
				new FactionsMenu().open((HumanEntity) p);
			}
		} else {
			final String sc = args[0];
			if (!sc.equalsIgnoreCase("info") && !sc.equalsIgnoreCase("criar") && !sc.equalsIgnoreCase("depositar")
					&& !sc.equalsIgnoreCase("retirar") && !sc.equalsIgnoreCase("desfazer")
					&& !sc.equalsIgnoreCase("convidar") && !sc.equalsIgnoreCase("expulsar")
					&& !sc.equalsIgnoreCase("sair")) {
				this.mensagem((CommandSender) p, "§cDigite: §f/f§7 -> §cdepois clique em §f`Ajuda`");
				return true;
			}
			if (sc.equalsIgnoreCase("sair")) {
				FactionPlayer fp = Main.players.get(p.getName());
				if (!fp.hasFaction()) {
					p.sendMessage("§cVocê não tem uma facção!");
					return true;
				}
				if (fp.getCargo() == Cargo.Lider) {
					p.sendMessage("§cUtilize /f desfazer!");
					return true;
				}

				if (fp.getFac().getRecrutas().contains(fp.getName())) {
					fp.getFac().getRecrutas().remove(fp.getName());
					fp.getFac().getAllMembers().remove(fp.getName());
				} else if (fp.getFac().getMembros().contains(fp.getName())) {
					fp.getFac().getMembros().remove(fp.getName());
					fp.getFac().getAllMembers().remove(fp.getName());
				} else if (fp.getFac().getCapitoes().contains(fp.getName())) {
					fp.getFac().getCapitoes().remove(fp.getName());
					fp.getFac().getAllMembers().remove(fp.getName());
				}
				fp.getFac().broadcast("§c`§f" + p.getName() + "§c` Saiu da facção!");
				fp.setFaction(null);
				return true;
			}
			if (sc.equalsIgnoreCase("convidar")) {
				if (args.length < 2) {
					p.sendMessage("§cDigite o nome de quem você quer convidar!");
					return true;
				}
				if (!Main.players.containsKey(args[1])) {
					p.sendMessage("§cEsse jogador nunca logou em nossa rede!");
					return true;
				}
				if (Bukkit.getPlayer(args[1]) == null) {
					p.sendMessage("§cO Jogador está offline!");
					return true;
				}

				FactionPlayer fp = Main.players.get(p.getName());
				FactionPlayer fp2 = Main.players.get(args[1]);

				if (fp.getCargo() != Cargo.Capitão && fp.getCargo() != Cargo.Lider) {
					p.sendMessage("§cVocê não pode convidar um jogador. seu cargo é muito baixo.");
					return true;
				}

				if (fp2.hasFaction()) {
					p.sendMessage("§cEsse jogador já tem facção!");
					return true;
				}

				if (fp2.getConvites().contains(fp.getFaction())) {
					p.sendMessage("§cSua facção já enviou um convite a esse jogador");
					return true;
				}

				fp2.getConvites().add(fp.getFaction());
				p.sendMessage("§aConvite enviado com sucesso!");
				Menu menuConvite = new Menu("§7Convites", MenuSize.THREE_LINES);
				menuConvite.setItem(13, new ArtItem(Material.WATCH).nome("§aTempo para aceitar").setAmout(60));

				menuConvite.setItem(11, new ArtItem(Material.WOOL).setData((short) 5).nome("§aAceitar o convite"),
						new MenuItemClick() {

							@Override
							public void onClick(InventoryClickEvent e) {
								fp2.setFaction(fp.getFaction());
								fp.getFac().getRecrutas().add(fp2.getName());
								fp2.getConvites().remove(fp.getFaction());
								p.sendMessage("§aPedido aceito");
								fp.getFac().broadcast("§aNovo membro `§f" + args[1] + "§a`.");
								fp2.getPlayer().closeInventory();
							}
						});
				menuConvite.setItem(15, new ArtItem(Material.WOOL).setData((short) 14).nome("§cNegar"),
						new MenuItemClick() {

							@Override
							public void onClick(InventoryClickEvent e) {
								fp2.getPlayer().closeInventory();
								fp2.getPlayer().sendMessage("§cVocênegou o convite da facção:§f " + fp.getFaction());
								p.sendMessage("§cO jogador " + args[1] + " negou o seu convite!");
								fp2.getPlayer().closeInventory();
								fp2.getConvites().remove(fp.getFaction());
							}
						});
				new BukkitRunnable() {
					int time = 60;

					@Override
					public void run() {
						time--;
						if (fp2.getConvites().contains(fp.getFaction())) {
							if (time > 0) {
								menuConvite.setItem(13,
										new ArtItem(Material.WATCH).nome("§aTempo para aceitar").setAmout(time));
								menuConvite.open(fp2.getPlayer());
							} else if (time == 30) {
								menuConvite.open(fp2.getPlayer());
							} else if (time == 0) {
								fp2.getPlayer().closeInventory();
								fp2.getPlayer()
										.sendMessage("§cVocê não aceitou o convite da facção:§f " + fp.getFaction());
								p.sendMessage("§cO jogador: §f" + args[1] + "§c não aceitou seu convite!");
								fp2.getConvites().remove(fp.getFaction());
								time--;
							}
						}
					}

				}.runTaskTimer(Main.getInstance(), 20, 20);
				menuConvite.open(fp2.getPlayer());
				return true;
			}
			if (sc.equalsIgnoreCase("expulsar")) {

				if (args.length < 2) {
					p.sendMessage("§cDigite o nome de quem você quer expulsar!");
					return true;
				}
				if (!Main.players.containsKey(args[1])) {
					p.sendMessage("§cEsse jogador nunca logou em nossa rede!");
					return true;
				}
				if (Bukkit.getPlayer(args[1]) == null) {
					p.sendMessage("§cO Jogador está offline!");
					return true;
				}

				FactionPlayer fp = Main.players.get(p.getName());
				FactionPlayer fp2 = Main.players.get(args[1]);

				if (fp.getCargo() != Cargo.Lider) {
					p.sendMessage("§cVocê não pode expulsar um jogador. seu cargo é muito baixo.");
					return true;
				}

				if (!fp.getFaction().equalsIgnoreCase(fp2.getFaction())) {
					p.sendMessage("§cEsse jogador nãp faz parte de sua facção!");
					return true;
				}

				if (fp.getFac().getRecrutas().contains(fp2.getName())) {
					fp.getFac().getRecrutas().remove(fp2.getName());
					fp.getFac().getAllMembers().remove(fp2.getName());
				} else if (fp.getFac().getMembros().contains(fp2.getName())) {
					fp.getFac().getMembros().remove(fp2.getName());
					fp.getFac().getAllMembers().remove(fp2.getName());
				} else if (fp.getFac().getCapitoes().contains(fp2.getName())) {
					fp.getFac().getCapitoes().remove(fp2.getName());
					fp.getFac().getAllMembers().remove(fp2.getName());
				}
				fp2.setFaction(null);
				fp.getFac().broadcast("§c`§f" + args[1] + "§c` foi expulso da facção!");

				return true;
			}
			if (sc.equalsIgnoreCase("info")) {
				// transformar em um info com menuGUI!
				if (args.length == 1) {
					FactionPlayer fp = Main.players.get(p.getName());
					if (!fp.hasFaction()) {
						p.sendMessage("§cVoce não tem facção");
						p.sendMessage("§cUtilize: /f info <tag>");
						return true;
					}
					Faction fc = fp.getFac();
					String membros = ArtFacManager.stripList(fc.getAllMembers()).replace(fc.getLider() + ",", "");
					String inimigos = ArtFacManager.stripList(fc.getInimigos());
					String aliados = ArtFacManager.stripList(fc.getAliados());
					Menu m = new Menu(" §b" + fp.getFaction() + " [" + fp.getFac().getTag() + "]", MenuSize.FIVE_LINES);
					
					p.sendMessage(" ");
					p.sendMessage(" §b" + fp.getFaction() + " [" + fp.getFac().getTag() + "]");
					p.sendMessage(" ");
					p.sendMessage(" §e» §bBanco§f:§e " + fc.getBanco());
					p.sendMessage(" §e» §bLider§f:§e " + fc.getLider());
					p.sendMessage(" §e» §bMembros§f:§e " + membros.replaceAll(fc.getLider(), ""));
					p.sendMessage(" §e» §bAliados§f:§e " + aliados);
					p.sendMessage(" §e» §bInimigos§f:§e " + inimigos);
					p.sendMessage(" ");
					m.open(p);

				} else if (args.length == 2) {
					String tag = args[1];
					Faction fac = null;
					for (Entry<String, Faction> entry : Main.factions.entrySet()) {
						if ((entry.getValue().getTag()).equalsIgnoreCase(tag)) {
							fac = entry.getValue();
						}
					}
					if (fac == null) {
						p.sendMessage("§cfacção não encontrada.");
						return true;
					}
					String membros = ArtFacManager.stripList(fac.getAllMembers()).replace(fac.getLider() + ",", "");
					String inimigos = ArtFacManager.stripList(fac.getInimigos());
					String aliados = ArtFacManager.stripList(fac.getAliados());
					p.sendMessage(" ");
					p.sendMessage(" §b" + fac.getNome() + " [" + fac.getTag() + "]");
					p.sendMessage(" ");
					p.sendMessage(" §e» §bBanco§f:§e " + fac.getBanco());
					p.sendMessage(" §e» §bLider§f:§e " + fac.getLider());
					p.sendMessage(" §e» §bMembros§f:§e " + membros.replaceAll(fac.getLider(), ""));
					p.sendMessage(" §e» §bAliados§f:§e " + aliados);
					p.sendMessage(" §e» §bInimigos§f:§e " + inimigos);
					p.sendMessage(" ");

				} else if (args.length >= 3) {
					p.sendMessage("§cUtilize: /f info <tag>");
				}

				return true;
			} else if (sc.equalsIgnoreCase("criar")) {
				final FactionPlayer fp2 = Main.players.get(p.getName());
				if (fp2.hasFaction()) {
					p.sendMessage("§cVocê já está em uma facção!");
					return true;
				}
				FactionsMenu.criar_fac.put(p.getName(), 0);
				p.sendMessage("§7Digite o §f`nome`§7 Da sua facção!");
				p.sendMessage("§7Digite §c`cancelar` §7Para cancelar a operação!");
				p.closeInventory();
				return true;
			} else if (sc.equalsIgnoreCase("depositar")) {
				if (args.length < 2) {
					p.sendMessage("§c/f depositar <qnt>");
					return true;
				}
				if (args[1].equalsIgnoreCase("NaN")) {
					p.sendMessage("§cOpa, Opa, Opa, tentando roubar maninho...?!");
					return true;
				}
				final FactionPlayer fp2 = Main.players.get(p.getName());
				double qnt = 0.0;
				try {
					qnt = Double.valueOf(args[1]);
				} catch (NumberFormatException e) {
					p.sendMessage("§cUse um numero correto");
				}
				if (!fp2.hasFaction()) {
					p.sendMessage("§cVoce precisa estar em uma facção!");
					return true;
				}
				if (CoinsAPI.getCoins(p.getName()) < qnt) {
					p.sendMessage("§cVocê não tem `§f" + args[1] + "§c` coins suficientes.");
					return true;
				}
				if (qnt < 0.0) {
					p.sendMessage("§cDigite um numero maior que zero!");
					return true;
				}
				fp2.getFac().depositar(qnt);
				CoinsAPI.removeCoins(p, qnt);
				p.sendMessage("§eVocê depositou `§b" + qnt + "§e` no banco da sua facção");
				return true;
			} else if (sc.equalsIgnoreCase("retirar")) {
				if (args.length < 2) {
					p.sendMessage("§c/f retirar <qnt>");
					return true;
				}
				if (args[1].equalsIgnoreCase("NaN")) {
					p.sendMessage("§cOpa, Opa, Opa, tentando roubar maninho...?!");
					return true;
				}
				final FactionPlayer fp2 = Main.players.get(p.getName());
				if (fp2.getCargo() == Cargo.Membro || fp2.getCargo() == Cargo.Recruta) {
					p.sendMessage("§cApenas jogadores com cargo acima de CAPIT\u00c3O podem retorar coins da facção.");
					return true;
				}
				double qnt = 0.0;
				try {
					qnt = Double.valueOf(args[1]);
				} catch (NumberFormatException e) {
					p.sendMessage("§cUse um numero correto");
				}
				if (!fp2.hasFaction()) {
					p.sendMessage("§cVoce precisa estar em uma facção!");
					return true;
				}
				if (qnt < 0.0) {
					p.sendMessage("§cDigite um numero maior que zero!");
					return true;
				}
				if (fp2.getFac().getBanco() - qnt < 0.0) {
					p.sendMessage("§cVocê não pode deixar o banco da facção em menos de zero!");
					return true;
				}
				fp2.getFac().retirar(qnt);
				CoinsAPI.addCoins(p, qnt);
				p.sendMessage("§eVocê retirou `§b" + qnt + "§e` do banco de sua facção");
				return true;
			} else if (sc.equalsIgnoreCase("desfazer")) {
				final FactionPlayer fp2 = Main.players.get(p.getName());
				if (!fp2.hasFaction()) {
					p.sendMessage("§cVoce precisa estar em uma facção!");
					return true;
				}
				if (fp2.getCargo() != Cargo.Lider) {
					p.sendMessage("§cVoce não é o lider da facção!");
					return true;
				}
				if (fp2.getFac().getBanco() > 0.0) {
					p.sendMessage("§cRetire todo o dinheiro do banco de sua facção!");
					return true;
				}
				fp2.getFac().broadcast("§cFacção Deletada!");
				p.closeInventory();
				p.sendMessage("§aFacção deletada com sucesso!");
				this.broadcast("§cFacção `§f" + fp2.getFaction() + "§c` foi deletada.");
				MySQLManager.deleteFaction(fp2.getFaction());
				fp2.setFaction(null);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		if (args[0].equalsIgnoreCase("f"))
			return Arrays.asList("info", "depositar", "retirar", "desfazer", "sair", "convidar", "expulsar");
		return Arrays.asList("");
	}
}