package net.dev.art.rc;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.dev.art.core.ArtPlugin;
import net.dev.art.rc.server.Server;

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
		Server.start();
	}

	@Override
	public void aoDisabilitar() {
		Server.stop();
	}

	@Override
	public void Register() {
		setCommand("cmd", new CommandTemplate());
	}

	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

}
