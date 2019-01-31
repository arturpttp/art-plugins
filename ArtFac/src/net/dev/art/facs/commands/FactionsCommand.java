package net.dev.art.facs.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.managers.ArtCommand;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.facs.Main;
import net.dev.art.facs.enums.Cargo;
import net.dev.art.facs.manager.MySQLManager;
import net.dev.art.facs.menus.FactionsMenu;
import net.dev.art.facs.menus.HasFactionMenu;
import net.dev.art.facs.objects.FactionPlayer;

public class FactionsCommand extends ArtCommand {

	public FactionsCommand() {
		super("faction");
		addAliases("f", "facs", "fac", "factions");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (!isPlayer(sender))
			return true;
		Player p = (Player) sender;
		if (args.length == 0) {
			FactionPlayer fp = Main.players.get(p.getName());
			if (fp.hasFaction())
				new HasFactionMenu(fp).open(p);
			else
				new FactionsMenu().open(p);

		} else {
			String sc = args[0];
			if (!sc.equalsIgnoreCase("info") && !sc.equalsIgnoreCase("criar") && !sc.equalsIgnoreCase("depositar")
					&& !sc.equalsIgnoreCase("retirar") && !sc.equalsIgnoreCase("desfazer")) {
				mensagem(p, "§cDigite: §f/f§7 -> §cdepois clique em §f`Ajuda`");
				return true;
			}
			if (sc.equalsIgnoreCase("info")) {
				FactionPlayer fp = Main.players.get(p.getName());
				if (!fp.hasFaction()) {
					p.sendMessage("§cVoce precisa estar em uma facção!");
					return true;
				}
				p.sendMessage("§eSua facção: §b" + fp.getFaction());
				p.sendMessage("§eFacção sua: §b" + fp.getFac().getNome());
				return true;
			}
			if (sc.equalsIgnoreCase("criar")) {
				FactionPlayer fp = Main.players.get(p.getName());
				if (fp.hasFaction()) {
					p.sendMessage("§cVocê já está em uma facção!");
					return true;
				}
				FactionsMenu.criar_fac.put(p.getName(), 0);
				p.sendMessage("§7Digite o §f`nome`§7 Da sua facção!");
				p.sendMessage("§7Digite §c`cancelar` §7Para cancelar a operação!");
				p.closeInventory();
				return true;
			}
			if (sc.equalsIgnoreCase("depositar")) {
				if (args.length < 2) {
					p.sendMessage("§c/f depositar <qnt>");
					return true;
				}
				if (args[1].equalsIgnoreCase("NaN")) {
					p.sendMessage("§cOpa, Opa, Opa, tentando roubar maninho...?!");
					return true;
				}
				FactionPlayer fp = Main.players.get(p.getName());
				double qnt = 0D;
				try {
					qnt = Double.valueOf(args[1]);
				} catch (NumberFormatException e) {
					p.sendMessage("§cUse um numero correto");
				}
				if (!fp.hasFaction()) {
					p.sendMessage("§cVoce precisa estar em uma facção!");
					return true;
				}

				if (CoinsAPI.getCoins(p.getName()) < qnt) {
					p.sendMessage("§cVocê não tem `§f" + args[1] + "§c` coins suficientes.");
					return true;
				}

				fp.getFac().depositar(qnt);

				return true;
			}

			if (sc.equalsIgnoreCase("retirar")) {
				if (args.length < 2) {
					p.sendMessage("§c/f depositar <qnt>");
					return true;
				}
				if (args[1].equalsIgnoreCase("NaN")) {
					p.sendMessage("§cOpa, Opa, Opa, tentando roubar maninho...?!");
					return true;
				}
				FactionPlayer fp = Main.players.get(p.getName());
				if (fp.getCargo() == Cargo.Membro || fp.getCargo() == Cargo.Recruta) {
					p.sendMessage("§cApenas jogadores com cargo acima de CAPITÃO podem retorar coins da facção.");
					return true;
				}
				double qnt = 0D;
				try {
					qnt = Double.valueOf(args[1]);
				} catch (NumberFormatException e) {
					p.sendMessage("§cUse um numero correto");
				}
				if (!fp.hasFaction()) {
					p.sendMessage("§cVoce precisa estar em uma facção!");
					return true;
				}

				if (fp.getFac().getBanco() == 0) {
					p.sendMessage("§cVocê não pode deixar o banco da facção em menos de zero!");
					return true;
				}

				fp.getFac().retirar(qnt);

				return true;
			}
			if (sc.equalsIgnoreCase("desfazer")) {
				FactionPlayer fp = Main.players.get(p.getName());
				if (!fp.hasFaction()) {
					p.sendMessage("§cVoce precisa estar em uma facção!");
					return true;
				}
				if (fp.getCargo() != Cargo.Lider) {
					p.sendMessage("§cVoce não é o lider da facção!");
					return true;
				}

				if (fp.getFac().getBanco() > 0) {
					p.sendMessage("§cRetire todo o dinheiro do banco de sua facção!");
					return true;
				}
				
				fp.getFac().broadcast("§cFacção Deletada!");
				p.closeInventory();
				p.sendMessage("§aFacção deletada com sucesso!");
				broadcast("§cFacção `§f" + fp.getFaction() + "§c` foi deletada.");

				MySQLManager.deleteFaction(fp.getFaction());
				fp.setFaction(null);

				return true;
			}
		}
		return false;
	}

}
