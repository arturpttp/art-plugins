package net.dev.art.aut;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.MensagensAPI;

public class ChangePasswordCommand extends MensagensAPI implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPerm);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("changepassword")) {
			if (AutAPI.isRegistrado(p.getName())) {
				if (args.length < 1) {
					Main.getInstance().mensagem(p,"§eUse:§6 /changepassword <senhaantiga> <senhanova>");
				} else {
					String senhaantiga = args[0];
					String senhanova = args[1];

					if (!AutAPI.getSenha(p.getName()).equalsIgnoreCase(senhaantiga)) {
						Main.getInstance().mensagem(p,"§cSenha antiga errada, use a senha certa!");
						return true;
					}

					if (senhaantiga.equalsIgnoreCase(senhanova)) {
						Main.getInstance().mensagem(p,"§csenhas iguais");
					} else {
						AutAPI.trocarSenha(p.getName(), senhanova);
						p.sendMessage(
								Main.getInstance().prefix + "§aTroca De Senha Bem Sucedida Senha nova:§b " + senhanova);
					}

				}
			} else {
				Main.getInstance().mensagem(p,"§cVocê Não está Registrado!");
			}
		}
		return false;
	}

}
