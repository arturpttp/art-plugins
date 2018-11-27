package net.dev.art.grupos.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import net.dev.art.grupos.Main;
import net.dev.art.grupos.objetos.Grupo;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.HoverEvent.Action;

public class GruposAPI {

	public static void setGrupo(String player, String grupo) {
		Grupo g = getGrupoByName(grupo);
		Main.gruposPlayers.put(player, g);
		Main.getInstance().getConfig().set(player, g.getName());
		Main.getInstance().saveConfig();
	}

	public static void setNames(Player p, Grupo g) {
		NamesAPI.GRUPOS.put(p, g);
	}

	public static boolean hasPermission(Player p, String permission) {
		boolean b = GruposAPI.getGrupo(p.getName()).getPermissions().contains(permission) == true ? true : false;
		return b;
	}

	public static void setGrupo(String player, Grupo grupo) {
		Main.gruposPlayers.put(player, grupo);
		Main.getInstance().getConfig().set(player, grupo.getName());
		Main.getInstance().saveConfig();
	}

	public static Grupo getGrupo(String player) {
		return Main.gruposPlayers.get(player);
	}

	public static Grupo getGrupoByName(String name) {
		for (Grupo g : getGrupos()) {
			if (g.getName().equalsIgnoreCase(name)) {
				return g;
			}
		}
		return null;
	}

	public static List<Grupo> getGrupos() {
		return Main.grupos;
	}

	public static void sendHelp(Player p) {
		p.sendMessage("           §eGRUPOS - §bAJUDA");
		p.sendMessage(" ");
		for (GruposHelp help : GruposHelp.values()) {
			String comando = help.getCommand().replace("§6", "").replace("§e", "").replace("[grupo] {jogador}", "");
			TextComponent cmd = new TextComponent(help.getCommand());
			cmd.setHoverEvent(
					new HoverEvent(Action.SHOW_TEXT, new BaseComponent[] { new TextComponent(help.getDescription()) }));
			TextComponent tc = new TextComponent("§b * §e");

			cmd.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, comando));
			tc.addExtra(cmd);
			p.spigot().sendMessage(tc);
		}
		p.sendMessage(" ");
	}

	public static String format(Grupo g) {
		String permission = "";
		String virgula = ",";

		for (String perm : g.getPermissions()) {
			permission = perm + virgula;
			if (g.getPermissions().lastIndexOf(perm) == (g.getPermissions().size() - 1))
				virgula = ".";
		}
		return "§eNome: §f" + g.getName() + " \n " + " \n " + "§ePermissions: §f" + permission;
	}

	public static void sendGrupos(Player p) {
		List<Grupo> grupos = new ArrayList<>();
		TextComponent tc = new TextComponent("");
		for (Grupo g : getGrupos()) {
			grupos.add(g);
			String virgula = ",";
			if (grupos.lastIndexOf(g) == (grupos.size() - 1))
				virgula = ".";
			tc.addExtra(new TextComponent(" "));
			TextComponent grupo = new TextComponent(g.getPrefix());
			grupo.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new BaseComponent[] { new TextComponent(format(g)) }));
			grupo.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND,
					"/setgrupo " + g.getName() + " " + p.getName()));
			tc.addExtra("§b" + grupo + "§f");
			tc.addExtra(new TextComponent(virgula));
		}
	}

	public enum GruposHelp {
		setGrupo;

		public String getCommand() {
			switch (this) {
			case setGrupo:
				return "§e/setGrupo§6 [grupo] {jogador}";
			}
			return null;
		}

		public String getDescription() {
			switch (this) {
			case setGrupo:
				return "§8 » §bSetar o grupo de um jogador.";
			}
			return null;
		}

	}

}
