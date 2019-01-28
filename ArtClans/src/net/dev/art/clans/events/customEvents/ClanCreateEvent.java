package net.dev.art.clans.events.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ClanCreateEvent extends Event implements Cancellable {

	public HandlerList handlers = new HandlerList();

	private Player owner;
	private String clanName;
	private String tag;
	private boolean canceled;

	public ClanCreateEvent(Player owner, String clanName, String tag, boolean canceled) {
		this.owner = owner;
		this.clanName = clanName;
		this.canceled = canceled;
		this.tag=tag;
	}

	public Player getOwner() {
		return owner;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
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

}
