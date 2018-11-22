package net.dev.art.core;

import org.bukkit.plugin.Plugin;

import net.dev.art.api.APIs.*;
import net.dev.green.grupos.APIs.GruposAPI;

public final class APIsManager {

	private GruposAPI gruposAPI;
	private API MainCoreAPI;
	private net.dev.art.api.APIs.API MainAPI;
	private CalendarioAPI CalendarioAPI; 
	private FormatarAPI FormatarAPI;
	private ItemsAPI ItemsAPI;
	
	public APIsManager(Plugin artPlugin) {
	}

	public ItemsAPI getItemsAPI() {
		return ItemsAPI;
	}
	
	public FormatarAPI getFormatarAPI() {
		return FormatarAPI;
	}
	
	public CalendarioAPI getCalendarioAPI() {
		return CalendarioAPI;
	}
	
	public API getMainCoreAPI() {
		return MainCoreAPI;
	}
	
	public net.dev.art.api.APIs.API getMainAPI() {
		return MainAPI;
	}
	
	public GruposAPI getGruposAPI() {
		return gruposAPI;
	}
	
}
