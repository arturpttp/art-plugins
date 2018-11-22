package net.dev.art.essentials.commands;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.essentials.utils.Mensagens;

public class WorldCommand extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (command.getName().equalsIgnoreCase("mundo")) {
				if (!p.hasPermission("luamundos.admin")) {
					p.sendMessage("§cVocê não tem permissão para executar este comando.");
					return true;
				}
				if (args.length == 0) {
					p.sendMessage(" ");
					p.sendMessage("§c/mundo criar <nome> §fCrie um novo mundo.");
					p.sendMessage("§c/mundo deletar <nome> §fDelete um mundo.");
					p.sendMessage("§c/mundo teleportar <nome> §fTeleportar até um mundo.");
					p.sendMessage(" ");
					return true;
				}
				if (args.length == 1) {
					p.sendMessage(" ");
					p.sendMessage("§c/mundo criar <nome> §fCrie um novo mundo.");
					p.sendMessage("§c/mundo deletar <nome> §fDelete um mundo.");
					p.sendMessage("§c/mundo teleportar <nome> §fTeleportar até um mundo.");
					p.sendMessage(" ");
					return true;
				}
				if (args.length > 2) {
					p.sendMessage("§cOs nomes não devem ter espaços.");
					return true;
				}
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("criar")) {

						if (Bukkit.getWorld(args[1]) != null) {
							p.sendMessage("§cJá existe um mundo com este nome.");
							return true;
						}

						Bukkit.createWorld(new WorldCreator(args[1]));
						p.sendMessage("§eMundo §f" + args[1] + " §ecriado com sucesso.");
					} else if (args[0].equalsIgnoreCase("deletar")) {

						if (Bukkit.getWorld(args[1]) == null) {
							p.sendMessage("§cNão existe um mundo com este nome.");
							return true;
						}

						World delete = Bukkit.getWorld(args[1]);
						Bukkit.unloadWorld(delete, false);
						try {
							FileUtils.deleteDirectory(new File(delete.getName()));
						} catch (IOException e) {
						}

						p.sendMessage("§eMundo §f" + args[1] + " §edeletado com sucesso.");

					} else if (args[0].equalsIgnoreCase("teleportar")) {

						if (Bukkit.getWorld(args[1]) == null) {
							p.sendMessage("§cNão existe um mundo com este nome.");
							return true;
						}

						p.teleport(new Location(Bukkit.getWorld(args[1]), 0, 100, 0));

					} else {
						p.sendMessage("§cSub comando não encontrado.");
					}
					return true;
				}
			}
		}
		return true;
	}

}
