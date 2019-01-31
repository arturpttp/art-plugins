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
				mensagem(p, "�cDigite: �f/f�7 -> �cdepois clique em �f`Ajuda`");
				return true;
			}
			if (sc.equalsIgnoreCase("info")) {
				FactionPlayer fp = Main.players.get(p.getName());
				if (!fp.hasFaction()) {
					p.sendMessage("�cVoce precisa estar em uma fac��o!");
					return true;
				}
				p.sendMessage("�eSua fac��o: �b" + fp.getFaction());
				p.sendMessage("�eFac��o sua: �b" + fp.getFac().getNome());
				return true;
			}
			if (sc.equalsIgnoreCase("criar")) {
				FactionPlayer fp = Main.players.get(p.getName());
				if (fp.hasFaction()) {
					p.sendMessage("�cVoc� j� est� em uma fac��o!");
					return true;
				}
				FactionsMenu.criar_fac.put(p.getName(), 0);
				p.sendMessage("�7Digite o �f`nome`�7 Da sua fac��o!");
				p.sendMessage("�7Digite �c`cancelar` �7Para cancelar a opera��o!");
				p.closeInventory();
				return true;
			}
			if (sc.equalsIgnoreCase("depositar")) {
				if (args.length < 2) {
					p.sendMessage("�c/f depositar <qnt>");
					return true;
				}
				if (args[1].equalsIgnoreCase("NaN")) {
					p.sendMessage("�cOpa, Opa, Opa, tentando roubar maninho...?!");
					return true;
				}
				FactionPlayer fp = Main.players.get(p.getName());
				double qnt = 0D;
				try {
					qnt = Double.valueOf(args[1]);
				} catch (NumberFormatException e) {
					p.sendMessage("�cUse um numero correto");
				}
				if (!fp.hasFaction()) {
					p.sendMessage("�cVoce precisa estar em uma fac��o!");
					return true;
				}

				if (CoinsAPI.getCoins(p.getName()) < qnt) {
					p.sendMessage("�cVoc� n�o tem `�f" + args[1] + "�c` coins suficientes.");
					return true;
				}

				fp.getFac().depositar(qnt);

				return true;
			}

			if (sc.equalsIgnoreCase("retirar")) {
				if (args.length < 2) {
					p.sendMessage("�c/f depositar <qnt>");
					return true;
				}
				if (args[1].equalsIgnoreCase("NaN")) {
					p.sendMessage("�cOpa, Opa, Opa, tentando roubar maninho...?!");
					return true;
				}
				FactionPlayer fp = Main.players.get(p.getName());
				if (fp.getCargo() == Cargo.Membro || fp.getCargo() == Cargo.Recruta) {
					p.sendMessage("�cApenas jogadores com cargo acima de CAPIT�O podem retorar coins da fac��o.");
					return true;
				}
				double qnt = 0D;
				try {
					qnt = Double.valueOf(args[1]);
				} catch (NumberFormatException e) {
					p.sendMessage("�cUse um numero correto");
				}
				if (!fp.hasFaction()) {
					p.sendMessage("�cVoce precisa estar em uma fac��o!");
					return true;
				}

				if (fp.getFac().getBanco() == 0) {
					p.sendMessage("�cVoc� n�o pode deixar o banco da fac��o em menos de zero!");
					return true;
				}

				fp.getFac().retirar(qnt);

				return true;
			}
			if (sc.equalsIgnoreCase("desfazer")) {
				FactionPlayer fp = Main.players.get(p.getName());
				if (!fp.hasFaction()) {
					p.sendMessage("�cVoce precisa estar em uma fac��o!");
					return true;
				}
				if (fp.getCargo() != Cargo.Lider) {
					p.sendMessage("�cVoce n�o � o lider da fac��o!");
					return true;
				}

				if (fp.getFac().getBanco() > 0) {
					p.sendMessage("�cRetire todo o dinheiro do banco de sua fac��o!");
					return true;
				}
				
				fp.getFac().broadcast("�cFac��o Deletada!");
				p.closeInventory();
				p.sendMessage("�aFac��o deletada com sucesso!");
				broadcast("�cFac��o `�f" + fp.getFaction() + "�c` foi deletada.");

				MySQLManager.deleteFaction(fp.getFaction());
				fp.setFaction(null);

				return true;
			}
		}
		return false;
	}

}
