package net.dev.art.clans.apis;

import java.util.List;

import org.bukkit.entity.Player;

import net.dev.art.clans.Main;

public class ClansAPI {

	/*
	 * TranslatableComponent giveMessage = new TranslatableComponent(
	 * "commands.give.success" ); TranslatableComponent item = new
	 * TranslatableComponent( "item.swordDiamond.name" ); item.setColor(
	 * ChatColor.GOLD ); giveMessage.addWith( item ); giveMessage.addWith( "32" );
	 * TextComponent username = new TextComponent( "Thinkofdeath" );
	 * username.setColor( ChatColor.AQUA ); giveMessage.addWith( username );
	 * player.sendMessage( giveMessage );
	 */

	public static boolean hasClan(Player p) {
		return Main.players.contains("Players." + p.getName() + ".Clan.Nome");
	}

	public static String getClan(Player p) {
		return Main.players.getString("Players." + p.getName() + ".Clan.Nome");
	}
	
	public static String getClanTag(Player p) {
		return Main.clans.getString("Clans." + getClan(p) + ".Tag");
	}

	public static boolean isOwner(Player p) {
		if (Main.players.getString("Players." + p.getName() + ".Clan.Cargo").equalsIgnoreCase("Lider")) {
			return true;
		}
		return false;
	}

	public static List<String> getMembers(String clan) {
		return Main.clans.getStringList("Clans." + clan + ".Membros");
	}
	
	

	public static boolean isMember(String p, String clan) {
		for (String member : getMembers(clan)) {
			if (member.equalsIgnoreCase(member)) {
				return true;
			}
		}
		return false;
	}

}
