package net.dev.art.shop;

import net.dev.art.core.ArtPlugin;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		
	}

	@Override
	public void aoDisabilitar() {
		
	}

	@Override
	public void Register() {
		autoRegister(this, "net.dev.art.shop");
	}

	@Override
	public String getPrefix() {
		return "§bArtShop§8 » ";
	}

}
