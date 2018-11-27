package net.dev.art.grupos.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.core.managers.ArtCommand;
import net.dev.art.grupos.Main;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.grupos.objetos.Grupo;

public class SetGrupo extends ArtCommand {

	public SetGrupo() {
		super("setGrupo");
		setDescription("setar o grupo de alguem");
	}

	@Override
	public boolean execute(CommandSender sender, String lb, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Main.getInstance().getPrefix() + "§cUtilize: §7/setGrupo §r<Grupo> (Player)");
			if (sender instanceof Player) {
				Player player = (Player) sender;
				GruposAPI.sendHelp(player);
			}
		}
		if (args.length == 1) {

			if (sender instanceof Player) {

				Player p = (Player) sender;
				Grupo r = null;
				try {
					r = GruposAPI.getGrupoByName(args[0]);

				} catch (Exception e) {
					mensagem(p, "§cGrupo §r" + args[0].toUpperCase() + " §cNão Existe");
				}
				if (r == null) {
					sender.sendMessage(
							Main.getInstance().getPrefix() + "§cGrupo §r" + args[0].toUpperCase() + " §cNão Existe");
					return false;
				}
				String rprefix = r.getPrefix().replace("&", "§");

				try {
					GruposAPI.setGrupo(p.getName(), r);
					GruposAPI.setNames(p, r);
					mensagem(p, "§aSeu Grupo Foi Alterado Para " + rprefix + " §aCom Sucesso");
				} catch (Exception e) {
					mensagem(p, "§cGrupo §r" + args[0].toUpperCase() + " §cNão Existe");
				}
			} else {
				sender.sendMessage(Main.getInstance().getPrefix() + "§cUtilize: §7/setGrupo §r<Grupo> (Player)");
			}
		}

		if (args.length > 1) {
			Grupo r = null;
			try {
				r = GruposAPI.getGrupoByName(args[0]);

			} catch (Exception e) {
				sender.sendMessage(
						Main.getInstance().getPrefix() + "§cGrupo §r" + args[0].toUpperCase() + " §cNão Existe");
			}
			if (r == null) {
				sender.sendMessage(
						Main.getInstance().getPrefix() + "§cGrupo §r" + args[0].toUpperCase() + " §cNão Existe");
				return false;
			}
			String rprefix = r.getPrefix().replace("&", "§");
			Player t = Bukkit.getPlayer(args[1]);
			if (t == null) {
				sender.sendMessage(Main.getInstance().getPrefix() + "§cEsse Jogador Não Está Online");
				return true;
			}
			try {
				GruposAPI.setGrupo(t.getName(), r);
				GruposAPI.setNames(t, r);
				sender.sendMessage(Main.getInstance().getPrefix() + "§aVocê Alterou o Grupo De " + t.getName()
						+ " §2Para " + rprefix + " §aCom Sucesso");
			} catch (Exception e) {
				sender.sendMessage(
						Main.getInstance().getPrefix() + "§cGrupo §r" + args[0].toUpperCase() + " §cNão Existe");
			}
			if (t.isOnline()) {
				mensagem(t, "§aSeu Grupo Foi Alterado Para " + rprefix);
			}
		}

		return false;
	}

}
