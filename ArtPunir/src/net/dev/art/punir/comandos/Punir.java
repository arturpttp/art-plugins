package net.dev.art.punir.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.punir.Main;
import net.dev.art.punir.Motivos;
import net.dev.art.punir.PunimentosAPI;

public class Punir implements CommandExecutor {

	String prefix = "§b" + Main.getInstance().getDescription().getName() + "§8 » ";

	public static String append(String[] args) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < args.length; x++) {
			stringBuilder.append(args[x] + " ");
		}
		return stringBuilder.toString();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (cmd.getName().equalsIgnoreCase("punir")) {
			if (sender.hasPermission("punir")) {
				if (args.length <= 1) {
					sender.sendMessage("§b" + Main.getInstance().getDescription().getName()
							+ "§8 » §cUse: §f/punir {Player} <Motivo>!");
				} else {
					if (args[0].equalsIgnoreCase("")) {
						sender.sendMessage("§b" + Main.getInstance().getDescription().getName()
								+ "§8 » §cUse: §f/punir {Player} <Motivo>!");
					}
					Player p = Bukkit.getPlayer(args[0]);
					if (p == null) {
						sender.sendMessage(prefix + "§cEsse Player Não Está Online!");
						return true;
					}

					if (args[1].equalsIgnoreCase("abuso") && args[2].equalsIgnoreCase("de")
							&& args[3].equalsIgnoreCase("capslock")) {
						if (PunimentosAPI.checkMute(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Abuso_De_Capslock);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("Desinformacao")) {
						if (PunimentosAPI.checkMute(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Desinformação);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("Divulgacao") && args[2].equalsIgnoreCase("Grave")) {
						if (PunimentosAPI.checkMute(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Divulgação_Grave);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("Flood")) {
						if (PunimentosAPI.checkMute(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Flood);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("ofença") && args[2].equalsIgnoreCase("em")
							&& args[3].equalsIgnoreCase("geral")) {
						if (PunimentosAPI.checkMute(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Ofensa_Em_Geral);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("ofença") && args[2].equalsIgnoreCase("a")
							&& args[3].equalsIgnoreCase("staff")) {
						if (PunimentosAPI.checkMute(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Ofensa_À_Staff);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("Spam")) {
						if (PunimentosAPI.checkMute(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Spam);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("AntiJogo")) {
						if (PunimentosAPI.checkBan(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.AntiJogo);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("Abuso") && 
							args[2].equalsIgnoreCase("de") && 
							args[3].equalsIgnoreCase("bugs")) {
						if (PunimentosAPI.checkBan(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Bugs);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("Abuso") && 
							args[2].equalsIgnoreCase("de") && 
							args[3].equalsIgnoreCase("bugs")) {
						if (PunimentosAPI.checkBan(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Bugs);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("conta") && 
							args[2].equalsIgnoreCase("fake")) {
						if (PunimentosAPI.checkBan(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Fake);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("falsificacao") && 
							args[2].equalsIgnoreCase("de") && 
							args[3].equalsIgnoreCase("provas")) {
						if (PunimentosAPI.checkBan(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Falsificação_De_Provas);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("uso") && 
							args[2].equalsIgnoreCase("de") && 
							args[3].equalsIgnoreCase("hack")) {
						if (PunimentosAPI.checkBan(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Hack);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("nick") && 
							args[2].equalsIgnoreCase("inadequado")) {
						if (PunimentosAPI.checkBan(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Nick_Inadequado);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("roubo") && 
							args[2].equalsIgnoreCase("em") && 
							args[3].equalsIgnoreCase("geral")) {
						if (PunimentosAPI.checkBan(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Roubo);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					} else if (args[1].equalsIgnoreCase("roubo") && 
							args[2].equalsIgnoreCase("de") && 
							args[3].equalsIgnoreCase("contas")) {
						if (PunimentosAPI.checkBan(p) == false) {
							PunimentosAPI.Punir(p, sender, Motivos.Roubo_De_Contas);
						} else {
							sender.sendMessage(prefix + "§cEsse Player Ja Está Punido Por Esse Motivo!");
						}
					}else{
						String motivo = append(args);
						sender.sendMessage(prefix + "§cMotivo: §f"+motivo.replace(p.getName(), "")+"§c Não Existe!");
					}

				}
			} else {
				sender.sendMessage("§b" + Main.getInstance().getDescription().getName() + "§8 » §cSem Permissao!");
			}
		}
		return false;
	}

}
