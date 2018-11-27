package net.dev.art.core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import net.dev.art.core.objects.ArtSQL;
import net.dev.art.core.objects.Config;
import net.dev.art.core.utils.LetterHead;

public class ArtCore extends ArtPlugin {

	public static ArtCore instance;

	public static ArtCore getInstance() {
		return instance;
	}

	ArtSQL sql;

	@Override
	public void aoCarregar() {
	}

	public Config cf;

	@Override
	public void aoIniciar() {
		instance = this;
		sendIniciando(instance);
		mensagem("§eIniciando core....");
		cf = new Config("config.yml", this);
		cf.saveDefaultConfig();
		startMysql();
		autoRegister(instance, "net.dev.art.core");
	}

	private void startMysql() {
		sql = new ArtSQL();
		sql.ativarDebug();
	}

	private void stopMysql() {
		sql.close();
	}

	@Override
	public void aoDisabilitar() {
		stopMysql();
		cf.saveConfig();
	}

	@Override
	public void Register() {

	}

	@Override
	public String getPrefix() {
		return "§b" + "ArtCore" + "§8 » ";
	}

}
