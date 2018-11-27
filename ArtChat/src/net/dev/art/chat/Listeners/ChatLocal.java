package net.dev.art.chat.Listeners;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.dev.art.api.APIs.CalendarioAPI;
import net.dev.art.chat.APIs.ChatAPI;
import net.dev.art.core.objects.ArtPlayer;
import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.punir.PunimentosAPI;
import net.dev.art.rank.RanksAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatLocal implements Listener {

	public static String format(Player p) {
		return "§eNick: " + GruposAPI.getGrupo(p.getName()).getPrefix() + " " + p.getName() + "\n" + "§eCoins: §b"
				+ CoinsAPI.getCoinsFormatado(p) + "\n" + "§eCash: §b" + CashAPI.getCashFormatado(p) + "\n"
				+ "§eClan: §7Nenhum";
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		ArtPlayer ap = new ArtPlayer(p);
		String grupo = GruposAPI.getGrupo(p.getName()).getPrefix();
		String msg = e.getMessage();
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (char a : msg.toCharArray()) {
			if (count == 0) {
				sb.append(("" + a).toUpperCase());
			} else {
				sb.append(("" + a).toLowerCase());
			}
			count += 1;
		}

		e.setMessage(sb.toString());
		String formato = "§e(L) " + grupo + " " + RanksAPI.getCurrentRank(p).getPrefix().replace("&", "§") + " §f"
				+ p.getName() + "§8 » §e" + ChatAPI.mensagem(p, e.getMessage());
		TextComponent tc = new TextComponent("");
		TextComponent pmsg = new TextComponent("§e"+ChatAPI.mensagem(p, e.getMessage()));
		TextComponent chatprefix = new TextComponent("§e(L) ");
		TextComponent playerinfos = new TextComponent(
				grupo + " " + RanksAPI.getCurrentRank(p).getPrefix().replace("&", "§") + " §f" + p.getName() + " ");
		playerinfos.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/tell " + p.getName()));
		playerinfos.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
				new BaseComponent[] { new TextComponent(format(p)) }));
		TextComponent separador = new TextComponent("§8» §e");
		tc.addExtra(chatprefix);
		tc.addExtra(playerinfos);
		tc.addExtra(separador);
		tc.addExtra(pmsg);

		String path = "Mutes." + p.getName().toLowerCase();
		FileConfiguration config = net.dev.art.punir.Main.getInstance().getConfig();

		if (PunimentosAPI.checkMute(p) == true) {
			String punidor = config.getString(path + ".Punidor");
			String mstring = config.getString(path + ".Motivo").replace("_", " ");
			long millis = config.getLong(path + ".Milliseconds");
			if (millis < System.currentTimeMillis()) {
				PunimentosAPI.Despunir(p, "mute");
				e.setCancelled(false);
				return;
			}

			p.sendMessage("          §cArtPunir     ");
			p.sendMessage("          ");
			p.sendMessage("§c§l♦ §c§f" + p.getName() + " §cfoi mutado permanetemente");

			p.sendMessage("§c§l♦ §c§cMutado por §f" + punidor);
			p.sendMessage("§c§l♦ §c§cMutado §f" + mstring);
			p.sendMessage("§c§l♦ §c§cMutado até: §f" + CalendarioAPI.getData(millis) + " as "
					+ CalendarioAPI.getHoras(millis));
			p.sendMessage("    ");
			e.setCancelled(true);
			return;
		} else if (PunimentosAPI.checkMute(p) == false) {
			if (e.isCancelled()) {
				e.setCancelled(true);
				return;
			}

			if (ChatAPI.isMutado("local")) {
				if (GruposAPI.hasPermission(p, "dono")) {
					e.setCancelled(true);
					p.sendMessage("§cO chat §eLocal§c está MUTADO!");
					p.playSound(p.getLocation(), Sound.CAT_MEOW, 1.0F, 0.5F);
					return;
				}
			}

			e.setCancelled(true);

//			ChatAPI.sendLocal(p, formato);
			ChatAPI.sendLocal(p, tc);
		}

	}

}
