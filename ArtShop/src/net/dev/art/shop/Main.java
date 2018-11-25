package net.dev.art.shop;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.core.ArtPlugin;
import net.dev.art.shop.menus.BlocosMenu;

public class Main extends ArtPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void aoCarregar() {

	}

	@Override
	public void aoIniciar() {
		instance = this;
		ShopMenu.register(instance);
		BlocosMenu.register(instance);
	}

	@Override
	public void aoDisabilitar() {

	}

	@Override
	public void Register() {
		setCommand("artshopreload", new ReloadCommand());
		setCommand("shop", new ShopCommand());
	}

	@Override
	public String getPrefix() {
		return "§bArtShop§8 » ";
	}

}
