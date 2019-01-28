package net.dev.art.clans.events.customEvents;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ClanQuitEvent extends Event implements Cancellable {

	public HandlerList handlers = new HandlerList();

	private Player player;
	private String clanName;
	private List<String> members;
	private String tag;
	private boolean canceled;

	public ClanQuitEvent(Player owner, String clanName, String tag, List<String> members, boolean canceled) {
		this.player = owner;
		this.clanName = clanName;
		this.canceled = canceled;
		this.members = members;
		this.tag = tag;
	}


	public Player getPlayer() {
		return player;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	@Override
	public boolean isCancelled() {
		return canceled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		canceled = arg0;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

}
