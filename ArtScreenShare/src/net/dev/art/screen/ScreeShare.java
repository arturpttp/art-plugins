package net.dev.art.screen;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

import net.dev.green.grupos.APIs.GruposAPI;

public class ScreeShare implements CommandExecutor, Listener {

	String prefix = Main.getInstance().prefix;

	void mensagem(String msg, Player p) {
		p.sendMessage(prefix + msg);
	}

	void erro(String msg, Player p) {
		p.sendMessage(prefix + "�c" + msg);
	}

	void mensagem(String msg) {
		Bukkit.getConsoleSender().sendMessage(prefix + msg);
	}

	void erro(String msg) {
		Bukkit.getConsoleSender().sendMessage(prefix + "�c" + msg);
	}

	public ArrayList<Player> telados = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			erro("Voc� precisa ser um player para fazer isso!");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("ss")) {
			if (GruposAPI.getPremissionLevel(GruposAPI.getGrupo(p)) < 3) {
				erro("�cVoc� n�o tem permiss�o para executar esse comando!", p);
				return true;
			}
			if (args.length == 0) {
				mensagem("�cUse:�f /ss (Jogador)", p);
			} else {
				Player t = Bukkit.getPlayer(args[0]);
				if (t == null) {
					erro("Esse jogador n�o est� online!", p);
					return true;
				}
				if (telados.contains(t)) {
					telados.remove(t);
					mensagem("�aVoc� liberou o jogador: �b"+t.getName()+"�a!", p);
					mensagem("�aVoc� est� limpo... e foi liberado da telagem!", t);
				} else {
					telados.add(t);
					erro("Voc� est� telando o jogador: �b"+t.getName()+"�c!", p);
					erro("Voc� est� com suspeita de hack! N�o deslogue ou sera banido!", t);
				}
			}
		}

		return false;
	}

	@EventHandler
	void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Player p = e.getPlayer();
		if (telados.contains(p)) {
			Main.getInstance().getConfig().set("Telados", p.getName());
		}
	}

	@EventHandler
	void onLogin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Main.getInstance().getConfig().contains("Telados." + p.getName())) {
			String msg = ("�cVoc� foi banido permanentemente por sair em uma telagem!\n Compre desbanimento em: �fexemplo.com.br");
			p.kickPlayer(msg);
			
		}
	}

}
