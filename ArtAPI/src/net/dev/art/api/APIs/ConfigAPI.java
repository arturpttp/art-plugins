package net.dev.art.api.APIs;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public final class ConfigAPI {

	String name;
	Plugin plugin;
	File file;
	YamlConfiguration config;

	public ConfigAPI(String name, Plugin plugin) {
		this.name = name;
		this.plugin = plugin;

		this.file = new File(plugin.getDataFolder(), name);
		if (!this.file.exists()) {
			plugin.saveResource(name, false);
			Bukkit.getConsoleSender().sendMessage(MensagensAPI.Tag + "§eCriando `" + name + "`");
		}
		this.file = new File(plugin.getDataFolder(), name );
		this.config = YamlConfiguration.loadConfiguration(this.file);
		Bukkit.getConsoleSender().sendMessage("§eCarregando `" + name + "`");

	}

	public File getFile() {
		return file;
	}
	
	public YamlConfiguration getConfig() {
		return config;
	}
	
	public String getName() {
		return name;
	}
	
	public void saveConfig() {
		try {
			this.config.save(file);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§cNão Foi Possivel Salvar A Config `" + getName() + "`");
		}
	}
	
	public String getString(String string) {
		return getConfig().getString(string);
	}
	
}
