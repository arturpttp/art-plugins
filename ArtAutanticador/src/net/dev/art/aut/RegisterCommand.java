package net.dev.art.aut;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.TitleAPI;
import net.dev.art.core.utils.ArtLib;

public class RegisterCommand implements CommandExecutor, ArtLib {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			noPlayer(sender);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("register")) {
			if (args.length < 1) {
				if (!AutAPI.isRegistrado(p.getName())) {

					Main.getInstance().mensagem(p,"§eUse:§6 /register <senha> <confirmasenha>");
				} else {
					Main.getInstance().mensagem(p,"§cVocê já está Registrado!");
				}
			} else {
				String senha = args[0];
				String confirmasenha = args[1];

				if (senha.equalsIgnoreCase(confirmasenha)) {
					AutAPI.registrar(p.getName(), senha);
					AutAPI.logar(p.getName());
					TitleAPI.sendTitle(p, "§bArtAutenticador", "§aRegistrado Com Sucesso");
					TitleAPI.sendActionBar("§aRegistrado Com Sucesso", p);
					AutAPI.sendThis(p.getName());
					Bukkit.broadcastMessage("§6SEJA BEM-VINDO: §b" + p.getName());
				} else {
					Main.getInstance().mensagem(p,"§cSenha diferentes, use senhas iguais para se registrar");
				}

			}
		}
		return false;
	}

}
