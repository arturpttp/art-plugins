package net.dev.art.utils;

import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.ArtPlugin;
import net.dev.art.core.objects.Config;
import net.dev.art.utils.apis.LaggAPI;
import net.dev.art.utils.commands.AdminCommand;
import net.dev.art.utils.commands.RemoveStands;
import net.dev.art.utils.events.AdminEvents;
import net.dev.art.utils.events.MobstackEvent;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	Config config = new Config("config.yml", this);

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		config.saveConfig();
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, new BukkitRunnable() {
			@Override
			public void run() {
				LaggAPI.anuncio(5);
			}
		}, 20 * 60 * 10, 20 * 10 * 60);

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
		setCommand("clearlagg", new RemoveStands());
		setCommand("reiniciar", new RemoveStands());
		setCommand("admin", new AdminCommand());
		setEvent(new AdminEvents());
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public String getPrefix() {
		return null;
	}

}
