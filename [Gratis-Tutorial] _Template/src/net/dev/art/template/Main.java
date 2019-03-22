package net.dev.art.template;

import net.dev.art.tuto.ArtPlugin;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoAbilitar() {
		instance = this;
		autoRegister(instance, "net.dev.art.template");
	}

	@Override
	public void aoDesabilitar() {

	}

	@Override
	public void register() {

	}

	@Override
	public String getPrefix() {
		return "§bArtTemplate§8 » ";
	}

}
