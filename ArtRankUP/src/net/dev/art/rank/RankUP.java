package net.dev.art.rank;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.dev.art.api.APIs.ArtItem;

public class RankUP extends Mensagens implements CommandExecutor {

	public static void AbrirRankUP(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 4, "§6§lRank§5§lUP");
		Rank r = RanksAPI.getNextRank(p.getName());
		ArtItem ore = new ArtItem(Material.LAPIS_ORE, 1, (short) 0).nome("§eUpar para proximo Rank:")
				.lore("§eProximo Rank: " + r.getPrefix().replace("&", "§"), "§ePreço: " + r.getPrice());
		ArtItem sim = new ArtItem(Material.WOOL, 1, (short) 5).nome("§aSim").lore("§aQuero upar de rank");
		ArtItem nao = new ArtItem(Material.WOOL, 1, (short) 14).nome("§cNão").lore("§ccancelar operação!");

		inv.setItem(13, ore);
		inv.setItem(20, nao);
		inv.setItem(24, sim);

		p.openInventory(inv);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("rankup")) {
			Rank r = RanksAPI.getNextRank(p.getName());
			if (r == null) {
				mensagem(p, "§cVocê já alcançou o ultimo RANK!");
				return true;
			}

			AbrirRankUP(p);

		} else if (cmd.getName().equalsIgnoreCase("rankupnpc")) {
			NPCRegistry registry = CitizensAPI.getNPCRegistry();
			NPC npc = registry.createNPC(EntityType.PLAYER, p.getUniqueId(), 1, "§6§lRank§5§lUP");
			npc.spawn(p.getLocation());
		}
		return false;
	}

}
