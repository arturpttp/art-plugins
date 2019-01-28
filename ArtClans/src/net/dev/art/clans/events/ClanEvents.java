package net.dev.art.clans.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.dev.art.clans.Main;
import net.dev.art.clans.apis.ClansAPI;
import net.dev.art.clans.events.customEvents.ClanCreateEvent;
import net.dev.art.clans.events.customEvents.ClanJoinEvent;
import net.dev.art.clans.events.customEvents.ClanQuitEvent;
import net.dev.art.core.utils.ArtLib;
import net.dev.art.core.utils.Configs;

public class ClanEvents implements Listener, ArtLib {

	@EventHandler
	void onCrete(ClanCreateEvent e) {
		Player p = e.getOwner();
		String name = e.getClanName();
		String tag = e.getTag();
		List<String> members = new ArrayList<>();
		members.add(p.getName());
		broadcast("§eO Jogador: §b" + p.getName() + " §eCriou o clan: §b" + tag);

		Configs config = Main.config;
		Configs clans = Main.clans;
		Configs players = Main.players;

		clans.set("Clans." + name + ".Lider", p.getName());
		clans.set("Clans." + name + ".Tag", tag);
		clans.set("Clans." + name + ".Membros", members);

		players.set("Players." + p.getName() + ".Clan.Nome", name);
		players.set("Players." + p.getName() + ".Clan.Tag", name);
		players.set("Players." + p.getName() + ".Clan.Cargo", "Lider");

	}

	@EventHandler
	void onJoin(ClanJoinEvent e) {
		Player p = e.getPlayer();
		String name = e.getClanName();
		List<String> members = e.getMembers();
		members.add(p.getName());
		String tag = e.getTag();
		broadcast("§eO Jogador: §b" + p.getName() + " §eEntou para o clan: §b" + tag);

		Configs config = Main.config;
		Configs clans = Main.clans;
		Configs players = Main.players;

		clans.set("Clans." + name + ".Membros", members);

		players.set("Players." + p.getName() + ".Clan.Nome", name);
		players.set("Players." + p.getName() + ".Clan.Tag", name);
		players.set("Players." + p.getName() + ".Clan.Cargo", "Recruta");

	}

	@EventHandler
	void onQuit(ClanQuitEvent e) {
		Player p = e.getPlayer();
		String name = e.getClanName();
		List<String> members = e.getMembers();
		members.remove(p.getName());
		String tag = e.getTag();
		Configs config = Main.config;
		Configs clans = Main.clans;
		Configs players = Main.players;

		for (String member : ClansAPI.getMembers(name)) {
			Bukkit.getPlayer(member).sendMessage("§f" + p.getName() + " §cSaiu do clan!");
			;
		}

		clans.set("Clans." + name + ".Membros", members);

		players.set("Players." + p.getName() + ".Clan", null);

	}

}
