package me.micael.atributos.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.micael.atributos.manager.AtributosAPI;

public class Atributos implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (cmd.getName().equalsIgnoreCase("atributos")) {
			Player p = (Player) sender;
			if (args.length < 1) {
				int forca = AtributosAPI.pegar_forca(p.getName());
				int vida = AtributosAPI.pegar_vida(p.getName());
				int defesa = AtributosAPI.pegar_defesa(p.getName());
				p.sendMessage("      §f§lSEUS ATRIBUTOS    ");
				p.sendMessage("§cFORÇA: §f" + forca);
				p.sendMessage("§cVIDA: §f" + vida);
				p.sendMessage("§cDEFESA: §f" + defesa);
				p.sendMessage("          ");
			} else {
				if (p.hasPermission("setar.atributos")) {
					String subcomando = args[0];
					if (subcomando.equalsIgnoreCase("setar")) {
						if (!p.hasPermission("pontos.staff")) {
							p.sendMessage("§f§lPONTOS: §cVocê não tem permissao!");
						} else if (args.length < 5) {
							p.sendMessage(
									"§f§lATRIBUTOS: §cUtilize /atributos setar <jogador> <forca> <vida> <defesa>");
						} else {
							Player jogador = Bukkit.getPlayer(args[1]);
							if (jogador == null) {
								p.sendMessage("§f§lPONTOS: §cEsse player não existe!");
							} else {
								Integer quantidade = new Integer(args[2]);
								Integer quantidade2 = new Integer(args[3]);
								Integer quantidade3 = new Integer(args[4]);
								AtributosAPI.setaratributos(jogador.getName(), quantidade, quantidade2, quantidade3);
								p.sendMessage("§f§lATRIBUTOS: §cVocê setou os atributos do jogador §f"
										+ jogador.getName() + " §cpra §f" + quantidade + " " + quantidade2 + " "
										+ quantidade3 + " ");
							}
						}
					} else if (subcomando.equalsIgnoreCase("adicionar")) {
						Player jogador = Bukkit.getPlayer(args[1]);
						if (jogador == null) {
							p.sendMessage("§f§lPONTOS: §cEsse player não existe!");
						} else {
							Integer quantidade = new Integer(args[2]);
							Integer quantidade2 = new Integer(args[3]);
							Integer quantidade3 = new Integer(args[4]);
							AtributosAPI.adicionaratributos(jogador.getName(),
									quantidade + AtributosAPI.pegar_forca(jogador.getName()),
									quantidade2 + AtributosAPI.pegar_vida(jogador.getName()),
									quantidade3 + AtributosAPI.pegar_defesa(jogador.getName()));
							p.sendMessage(
									"§f§lATRIBUTOS: §cVocê adicionou os atributos do jogador §f" + jogador.getName()
											+ " §cpra §f" + quantidade + " " + quantidade2 + " " + quantidade3 + " ");
						}
					}
				}
			}
		}
		return false;
	}
}
