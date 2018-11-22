package net.dev.green.grupos;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

import net.dev.green.grupos.Comandos.setGrupo;
import net.dev.green.grupos.Eventos.GrupoEvent;

public class Register {

	public Register(Main pl) {
		Commands(pl);
		Events(pl);
	}

	private void Events(Main pl) {
		E(new GrupoEvent(), pl);
		E(pl, pl);
	}

	private void Commands(Main pl) {
		C("setGrupo", new setGrupo(), pl);
	}
	
	public void C(String cmd, CommandExecutor c, Main pl) {
		pl.getCommand(cmd).setExecutor(c);
	}
	
	public void E(Listener c, Main pl) {
		Bukkit.getPluginManager().registerEvents(c, pl);
	}
	
	
	
}
