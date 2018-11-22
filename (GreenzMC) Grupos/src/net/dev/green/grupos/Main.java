package net.dev.green.grupos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.dev.green.grupos.APIs.GruposAPI;
import net.dev.green.grupos.APIs.GruposAPI.GruposTipos;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{

	public static Main instance;
	public static ConsoleCommandSender send = Bukkit.getConsoleSender();

	public String prefix = "§a§lGreenzMC§8 » ";
	private Scoreboard s;
	public static Main getInstance() {
		return instance;
	}

	public static File grupos;
	public static YamlConfiguration g;

	@Override
	public void onLoad() {
		
	}

	void registerHealthBar() {
		if (s.getObjective("health") != null) {
			s.getObjective("health").unregister();
		}
		String heart = "♥";
		Objective o = s.registerNewObjective("health", "health");
		o.setDisplayName("§c"+heart);
		o.setDisplaySlot(DisplaySlot.BELOW_NAME);
	}
	
	void registerNameTag() {
		if (s.getObjective("nesim") != null) {
			s.getObjective("nesim").unregister();
		}
		Team t = s.registerNewTeam("nesim");
			t.setPrefix(ChatColor.AQUA + "" + ChatColor.BOLD + "");
		
		
	}
	
	@EventHandler
	void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		s.getTeam("nesim").addPlayer(p);
	}
	@Override
	public void onEnable() {
		instance = this;
		new Register(this);
		
		s = Bukkit.getScoreboardManager().getMainScoreboard();
		registerHealthBar();
		registerNameTag();
		for (Player p : Bukkit.getOnlinePlayers()) {s.getTeam("nesim").addPlayer(p);}
		grupos = new File(getDataFolder(), "grupos.yml");
		if (!grupos.exists()) {
			saveResource("grupos.yml", false);
			send.sendMessage(prefix + "§eCriando `grupos.yml`");
		}
		grupos = new File(getDataFolder(), "grupos.yml");
		g = YamlConfiguration.loadConfiguration(grupos);
		send.sendMessage(prefix + "§eCarregando `grupos.yml`");

		loadGruops();
		carregarGrupos();
	}

	public void getGrupos(GruposTipos g) {
		Mensagens.sendToConsole("§eCarregando Grupo `" + GruposAPI.getPrefix(g) + "`" , send);
	}

	public void carregarGrupos() {
		getGrupos(GruposTipos.Gerente);
		getGrupos(GruposTipos.Dono);
		getGrupos(GruposTipos.Admin);
		getGrupos(GruposTipos.Diretor);
		getGrupos(GruposTipos.Ajudante);
		getGrupos(GruposTipos.Folhagem);
		getGrupos(GruposTipos.Green);
		getGrupos(GruposTipos.Leaf);
		getGrupos(GruposTipos.Membro);
		getGrupos(GruposTipos.MiniYT);
		getGrupos(GruposTipos.Moderador);
		getGrupos(GruposTipos.Tree);
		getGrupos(GruposTipos.Youtube);

	}
	
	public void loadGruops() {
		new BukkitRunnable() {
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					GruposAPI.setGrupo(p, GruposAPI.getGrupo(p));
				}
			}
		}.runTaskTimer(instance, 0, 40);
	}

	@Override
	public void onDisable() {
		s.getTeam("nesim").unregister();
	}

	public static YamlConfiguration getGruposConfig() {
		return g;
	}

	public static void saveGruposConfig() {
		try {
			g.save(grupos);
		} catch (IOException e) {
			Mensagens.sendToConsole("§cNão Foi Possivel Salvar a Config `grupos.yml`", send);
		}
	}

}
