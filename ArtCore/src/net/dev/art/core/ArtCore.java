package net.dev.art.core;

import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.core.commands.ReloadCommand;

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
		new ReloadCommand(instance);
	}

	private void startMysql() {
		sql = new ArtSQL();
		sql.ativarDebug();
	}

	private void stopMysql() {
		sql = new ArtSQL();
		sql.close();
		sql.ativarDebug();
	}

	public void reload() {
		debug("§cDesligando Plugin §bArtCore");
		aoDisabilitar();

		new BukkitRunnable() {
			public void run() {
				debug("Habilitando Plugin §bArtCore");
				aoCarregar();
				aoIniciar();
			}
		}.runTaskTimer(instance, 20 * 3, 20 * 3);

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

//	@EventHandler
//	void onJoin(PlayerJoinEvent e) {
//		Player p = e.getPlayer();
//		ItemStack i = null;
//		try {
//			i = TexturedHead.getColoredHead(ColorsHeads.Dark_Sky_Blue,p);
//
//		} catch (Exception e2) {
//			Bukkit.broadcastMessage("AA");
//		}
//		if (i == null) {
//			Bukkit.broadcastMessage("BB");
//			return;
//		}
//		p.getInventory().addItem(i);
//	}

}
