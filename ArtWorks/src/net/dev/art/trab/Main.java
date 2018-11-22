package net.dev.art.trab;

import org.bukkit.entity.Player;

import net.dev.art.api.APIs.CooldownAPI;
import net.dev.art.core.ArtPlugin;
import net.dev.art.core.Config;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	Config config = new Config("playersTrabalhos.yml", this);

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		config.saveConfig();
		new TItem(this);
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public void Register() {
		setCommand("trabalhonpc", new TrabalhoCommand());
		setEvent(new TrabalhosManager());
		
	}

	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

}
