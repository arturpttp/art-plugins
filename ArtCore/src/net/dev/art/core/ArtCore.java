package net.dev.art.core;

import org.bukkit.Bukkit;

import net.dev.art.core.objects.ArtSQL;
import net.dev.art.core.utils.Configs;

public class ArtCore extends ArtPlugin {

	public static ArtCore instance;

	public static ArtCore getInstance() {
		return instance;
	}

	@Override
	public void aoCarregar() {

	}

	Configs cf = new Configs("config.yml", this);

	@Override
	public void aoIniciar() {
		instance = this;
		sendIniciando(instance);
		mensagem("§eIniciando core....");
		cf.saveDefaultConfig();
		ArtSQL.ativarDebug();
		ArtSQL.iniciar();

		autoRegister(instance, "net.dev.art.core");

	}

	@Override
	public void aoDisabilitar() {
		ArtSQL.close();
//		cf.saveConfig();
	}

	@Override
	public void Register() {

	}

	@Override
	public String getPrefix() {
		return "§b" + "ArtCore" + "§8 » ";
	}

}
