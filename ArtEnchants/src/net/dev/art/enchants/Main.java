package net.dev.art.enchants;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.enchants.commands.EncantarCommand;
import net.dev.art.enchants.enchants.ExplosionEnchantment;
import net.dev.art.enchants.enchants.SmeltingEnchantment;

public class Main extends JavaPlugin implements Listener {

	public static Main instance;
	public ConsoleCommandSender send = Bukkit.getConsoleSender();
	public ExplosionEnchantment enc = new ExplosionEnchantment(101);
	public SmeltingEnchantment smelting = new SmeltingEnchantment(102);

	public static Main getInstance() {
		return instance;
	}

	public static File config;
	public static YamlConfiguration c;

	public String prefix = "§b" + getDescription().getName() + "§8 » ";

	@Override
	public void onEnable() {
		instance = this;

		send.sendMessage("");
		send.sendMessage("§eLigando Plugin: §b§l" + getDescription().getName());
		send.sendMessage(
				"§eCriado Por: §b§l" + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
		send.sendMessage("§eVersao: §b§l" + getDescription().getVersion());
		send.sendMessage("");

		loadEnchantments();
		getCommand("artencantar").setExecutor(new EncantarCommand());
		sendConsoleInfos();
		Register();
	}

	@SuppressWarnings("all")
	@Override
	public void onDisable() {
		try {
			Field id = Enchantment.class.getDeclaredField("byId");
			Field name = Enchantment.class.getDeclaredField("byName");

			HashMap<Integer, Enchantment> idHash = (HashMap<Integer, Enchantment>) id.get(null);
			HashMap<Integer, Enchantment> nameHash = (HashMap<Integer, Enchantment>) id.get(null);

			if (idHash.containsKey(enc.getId())) {
				idHash.remove(enc.getId());
			}

			if (nameHash.containsKey(enc.getName())) {
				nameHash.remove(enc.getName());
			}

			if (idHash.containsKey(smelting.getId())) {
				idHash.remove(smelting.getId());
			}

			if (nameHash.containsKey(smelting.getName())) {
				nameHash.remove(smelting.getName());
			}

		} catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
			mensagem("§cErro ao tentar pegar o ID do encantamento §aEXPLOSIOVE");
		}
	}

	private void loadEnchantments() {
		try {
			try {
				Field f = Enchantment.class.getDeclaredField("acceptingNew");
				f.setAccessible(true);
				f.set(null, true);
			} catch (Exception e) {
				mensagem("§cErro ao tentar criar o encantamento §aEXPLOSIOVE");
			}

			try {
				Enchantment.registerEnchantment(enc);
				Enchantment.registerEnchantment(smelting);
			} catch (Exception e) {
				mensagem("§cErro ao tentar registrar o encantamento §aEXPLOSIOVE");
			}

		} catch (IllegalArgumentException e) {
			mensagem("§cErro ao tentar carregar o encantamento §aEXPLOSIOVE");
		}
	}

	private void Register() {
		PluginManager e = Bukkit.getPluginManager();
		e.registerEvents(this, this);
		e.registerEvents(enc, this);
		e.registerEvents(smelting, this);

	}

	private void sendConsoleInfos() {
	}

	void GerarConfig() {
		config = new File(getDataFolder(), "config.yml");
		if (!config.exists()) {
			saveResource("config.yml", false);
			send.sendMessage(prefix + "§eCriando `§bconfig.yml§e`");
		}
		config = new File(getDataFolder(), "config.yml");
		c = YamlConfiguration.loadConfiguration(config);
		send.sendMessage(prefix + "§eCarregando `§bconfig.yml§e`");
	}

	public void mensagem(String mensagem) {
		send.sendMessage(prefix + mensagem);
	}

}
