package net.dev.art.rank;

import java.util.List;

import org.bukkit.entity.Player;

public class RanksAPI {

	public static Rank getRankByName(String name) {
		for (Rank r : getRanks()) {
			if (r.getName().equalsIgnoreCase(name)) {
				return r;
			}
		}
		return null;
	}

	public static void setRank(String p, int position) {
		if (getRankByPosition(position) == null)
			return;
		Main.getInstance().rankplayer.put(p, getRankByPosition(position));
		Main.getInstance().getConfig().set(p, getRankByPosition(position).getName());
		Main.getInstance().saveConfig();
	}

	public static void setRank(String p, Rank r) {
		Main.getInstance().rankplayer.put(p, r);
		Main.getInstance().getConfig().set(p, r.getName());
		Main.getInstance().saveConfig();

	}

	public static Rank getCurrentRank(String p) {
		return Main.getInstance().rankplayer.get(p);
	}

	public static Rank getCurrentRank(Player p) {
		return getRankByName(Main.getInstance().getConfig().getString(p.getName()));
	}

	public static Rank getNextRank(String p) {
		return getRankByPosition(getCurrentRank(p).getPosition() + 1);
	}

	public static void RankUP(String p) {
		setRank(p, getNextRank(p));
	}

	public static Rank getRankByPosition(int position) {
		for (Rank r : getRanks()) {
			if (r.getPosition() == position) {
				return r;
			}
		}
		return null;
	}

	public static List<Rank> getRanks() {
		return Main.getInstance().ranks;
	}

}
