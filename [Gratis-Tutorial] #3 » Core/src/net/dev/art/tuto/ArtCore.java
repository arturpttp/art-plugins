package net.dev.art.tuto;

import org.bukkit.plugin.java.JavaPlugin;

public class ArtCore extends ArtPlugin {

	public static ArtCore instance;

	public static ArtCore getInstance() {
		return instance;
	}

	@Override
	public void aoCarregar() {
		console(getPrefix() + "§aCarregando");
	}

	@Override
	public void aoAbilitar() {
		instance = this;
		autoRegister(this, "net.dev.art.tuto");
		console(getPrefix() + "§aIniciado");
	}

	@Override
	public void aoDesabilitar() {

	}

	@Override
	public String getPrefix() {
		return "§bArtCore §8» ";
	}

	@Override
	public void register() {

	}

}
