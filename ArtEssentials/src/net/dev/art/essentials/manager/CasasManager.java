package net.dev.art.essentials.manager;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class CasasManager {

	public enum Help {
		teleportar, remover, ajuda, criar, publica, listar;

		public String getCommand() {
			switch (this) {
			case ajuda:
				return "§e/casa ajuda";
			case criar:
				return "§e/casa criar §6<nome>";
			case remover:
				return "§e/casa remover §6<nome>";
			case teleportar:
				return "§e/casa ir §6<(<jogador>:) casa>";
			case publica:
				return "§e/casa publica §6<nome>";
			case listar:
				return "§e/casa listar";
			}
			return "";
		}

		public String getDescription() {
			switch (this) {
			case ajuda:
				return "§blista de comandos com suas utilidades.";
			case criar:
				return "§bcriar uma casa com uma localização propria.";
			case remover:
				return "§bdeletar uma de suas casas.";
			case teleportar:
				return "§bir para sua casa ou para de outros jogadores.";
			case publica:
				return "§bpublicar sua casa e assim todos os jogador poderam ir até ela.";
			case listar:
				return "§blista de casas.";
			}
			return "";
		}

	}

	public static void sendhelp(Player p) {
		p.sendMessage("");
		p.sendMessage("       §eAJUDA - §bArtCasas       ");
		p.sendMessage("");
		for (Help help : Help.values()) {
			String comando = help.getCommand().replace("§6", "").replace("§e", "").split("<")[0];
			TextComponent cmd = new TextComponent(help.getCommand());
			cmd.setHoverEvent(
					new HoverEvent(Action.SHOW_TEXT, new BaseComponent[] { new TextComponent(help.getDescription()) }));
			TextComponent tc = new TextComponent("§b * §e");

			cmd.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, comando));
			tc.addExtra(cmd);
			p.spigot().sendMessage(tc);
		}
		p.sendMessage("");
	}

}
