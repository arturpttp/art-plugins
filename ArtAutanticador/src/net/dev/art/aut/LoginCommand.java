package net.dev.art.aut;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.TitleAPI;
import net.dev.art.core.utils.ArtLib;
import net.dev.art.grupos.api.GruposAPI;

public class LoginCommand implements CommandExecutor, ArtLib {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			noPlayer(sender);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("login")) {
			if (AutAPI.isRegistrado(p.getName())) {
				if (args.length == 0) {
					if (AutAPI.isLogado(p.getName())) {
						Main.getInstance().mensagem(p, "§cVocê Já Está Logado!");
						return false;
					}
					Main.getInstance().mensagem(p, "§eUse:§6 /login <senha>");
				} else {
					String senha = args[0];
					if (AutAPI.getSenha(p.getName()).equalsIgnoreCase(senha)) {
						AutAPI.logar(p.getName());
						TitleAPI.sendTitle(p, "§bArtAutenticador", "§aLogado Com Sucesso");
						TitleAPI.sendActionBar("§aLogado Com Sucesso", p);
						AutAPI.sendThis(p.getName());
						if (GruposAPI.hasPermission(p, "dono")) {
							p.setGameMode(GameMode.CREATIVE);
						}
					} else {
						Main.getInstance().mensagem(p, "§cSenha Errada, Digite Uma Senha Valida!");
					}
				}
			} else {
				Main.getInstance().mensagem(p, "§cVocê deve se Registrar antes de logar!");
			}
		}
		return false;
	}

}
