package net.dev.art.mina;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();

	public static Main getInstance() {
		return instance;
	}

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onLoad() {
		mensagem("§eCarregando...");
	}

	@Override
	public void onEnable() {
		instance = this;

		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + getDescription().getName());
		send.sendMessage(
				"§eCriado Por: §b§l" + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + getDescription().getVersion());
		send.sendMessage("");
		Register();
		updater();
		getCommand("mina").setExecutor(new minaCommand());
	}

	public static int gerarID() {
		return ThreadLocalRandom.current().nextInt(0, 5000);
	}

	private void updater() {
		new BukkitRunnable() {
			public void run() {
				for (Player on : Bukkit.getOnlinePlayers()) {
					if (on.getWorld().getName().equalsIgnoreCase("mina")) {
						on.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 10, 1));
					}
				}
			}
		}.runTaskTimer(this, 20, 20);
	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		e.registerEvents(new Evento(), this);
	}

	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

	@Override
	public void onDisable() {

	}

}
