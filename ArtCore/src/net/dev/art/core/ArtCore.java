package net.dev.art.core;

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

	@Override
	public void aoDisabilitar() {
		stopMysql();
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
