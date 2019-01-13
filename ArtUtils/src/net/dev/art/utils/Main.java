package net.dev.art.utils;

import net.dev.art.core.ArtPlugin;
import net.dev.art.core.objects.Config;
import net.dev.art.core.utils.Configs;
import net.dev.art.utils.apis.LaggAPI;
import net.dev.art.utils.apis.RestartAPI;
import net.dev.art.utils.commands.AdminCommand;
import net.dev.art.utils.commands.RemoveStands;
import net.dev.art.utils.drops.DropsManager;
import net.dev.art.utils.events.AdminEvents;
import net.dev.art.utils.events.MobstackEvent;
import net.dev.art.utils.events.PickUP;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	Config config = new Config("config.yml", this);
	public Configs drops = new Configs("drops.yml", this);

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		config.saveConfig();
		drops.saveDefaultConfig();
		drops.saveConfig();
		LaggAPI.anuncio(5);

		getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
			@Override
			public void run() {
				LaggAPI.anuncio(5);
			};
		}, 20 * 60 * 10, 20 * 60 * 10);

		getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
			@Override
			public void run() {
				RestartAPI.anuncio(3);
			};
		}, 20 * 60 * 60 * 2, 20 * 60 * 60 * 2);

	}

	@Override
	public void Register() {
		send.sendMessage("");
		if (getConfig().getBoolean("Options.Mobstack")) {
			setEvent(new MobstackEvent());
			mensagem("§eRegistrando Mobstack... Carregando Sistema...");
		} else {
			mensagem("§cMobstack desabilitado va na §fconfig.yml§c e abilite");
		}

		if (getConfig().getBoolean("Options.DropsInv")) {
			setEvent(new DropsManager());
			mensagem("§eRegistrando Drops... Carregando Sistema...");
		} else {
			mensagem("§cDropsdesabilitado va na §fconfig.yml§c e abilite");
		}

		setCommand("clearlagg", new RemoveStands());
		setCommand("reiniciar", new RemoveStands());
		setCommand("admin", new AdminCommand());
		setEvent(new AdminEvents());
		setEvent(new PickUP());
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public String getPrefix() {
		return null;
	}

}
