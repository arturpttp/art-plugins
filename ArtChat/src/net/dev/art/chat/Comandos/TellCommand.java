package net.dev.art.chat.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.dev.art.chat.APIs.ChatAPI;
import net.dev.art.core.utils.ArtLib;
import net.dev.art.grupos.api.GruposAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class TellCommand implements CommandExecutor, ArtLib {

	public void Tell(Player p, Player t, String[] args) {

		String mensagem = ChatAPI.append(args).replace(p.getName(), "").replace(t.getName(), "");
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (char a : mensagem.toCharArray()) {
			if (count == 0) {
				sb.append(("" + a).toUpperCase());
			} else {
				sb.append(("" + a).toLowerCase());
			}
			count += 1;
		}

		String grupo = GruposAPI.getGrupo(p.getName()).getPrefix();

		TextComponent tc1 = new TextComponent("");
		TextComponent tc = new TextComponent("");
		TextComponent pmsg = new TextComponent("§e" + ChatAPI.mensagem(p, mensagem));
		TextComponent chatprefix = new TextComponent("§6[TELL] ");
		TextComponent playerinfos = new TextComponent("§f" + p.getName() + " ");
		playerinfos.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/tell " + p.getName()));
		playerinfos.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
				new BaseComponent[] { new TextComponent(GlobalChat.format(p)) }));
		
		TextComponent targetinfos = new TextComponent("§f" + t.getName() + " ");
		targetinfos.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/tell " + t.getName()));
		targetinfos.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
				new BaseComponent[] { new TextComponent(GlobalChat.format(t)) }));
		
		TextComponent separador = new TextComponent("§8» §e");
		tc.addExtra(chatprefix);
		tc.addExtra(new TextComponent("§6De: "));
		tc.addExtra(playerinfos);
		tc.addExtra(separador);
		tc.addExtra(pmsg);
		
		tc1.addExtra(chatprefix);
		tc1.addExtra(new TextComponent("§6Para: "));
		tc1.addExtra(targetinfos);
		tc1.addExtra(separador);
		tc1.addExtra(pmsg);

		p.spigot().sendMessage(tc1);
		t.spigot().sendMessage(tc);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			noPlayer(sender);
			return true;
		}
		Player p = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("tell")) {
			if (args.length <= 1) {
				mensagem(p, "§eUse:§b /tell <Player> (MENSAGEM)§3 - Enviar Uma Mensagem Privada!");
			} else {

				Player t = Bukkit.getPlayer(args[0]);

				if (t == null) {
					mensagem(p, "§cPlayer Offline!");
					return true;
				}
				
				Tell(p, t, args);

			}

		}

		return false;
	}

}
