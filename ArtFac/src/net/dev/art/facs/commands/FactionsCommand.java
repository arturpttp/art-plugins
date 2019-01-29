package net.dev.art.facs.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.managers.ArtCommand;
import net.dev.art.facs.Main;
import net.dev.art.facs.enums.Cargo;
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
			if (!sc.equalsIgnoreCase("info") && !sc.equalsIgnoreCase("criar") && !sc.equalsIgnoreCase("desfazer")) {
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

				return true;
			}
		}
		return false;
	}

}
