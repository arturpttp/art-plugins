package net.dev.art.trab;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

public class TrabalhoCommand extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("trabalhonpc")) {
			NPCRegistry registry = CitizensAPI.getNPCRegistry();
			NPC npc = registry.createNPC(EntityType.PLAYER, p.getUniqueId(), 1, "§c§lTrabalhos");
			npc.spawn(p.getLocation());
		}
		return false;
	}

}
