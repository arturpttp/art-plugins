package net.dev.art.chat.Comandos;

import net.dev.art.api.APIs.CalendarioAPI;
import net.dev.art.chat.APIs.ChatAPI;
import net.dev.art.chat.Utils.Mensagens;
import net.dev.art.eco.apis.CashAPI;
import net.dev.art.eco.apis.CoinsAPI;
import net.dev.art.facs.manager.PlayersManager;
import net.dev.art.facs.objects.Faction;
import net.dev.art.facs.objects.FactionPlayer;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.punir.PunimentosAPI;
import net.dev.art.rank.RanksAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GlobalChat extends Mensagens implements CommandExecutor {

	public static String format(Player p) {
		String fc = "";
		Faction fac = null;
		FactionPlayer fp = null;
		try {
			fp = PlayersManager.getPlayer(p.getName());
			fac = PlayersManager.getPlayer(p.getName()).getFac();
			String tag = "[" + fp.getCargo().getSimbolo() + fac.getTag() + "]";
			String nome = fac.getNome();
			fc = " " + tag + " " + nome;
		} catch (Exception e) {
			fc = "Sem Facção";
		}
		return "§eNick: " + GruposAPI.getGrupo(p.getName()).getPrefix() + " " + p.getName() + "\n" + "§eCoins: §b"
				+ CoinsAPI.getCoinsFormatado(p) + "\n" + "§eCash: §b" + CashAPI.getCashFormatado(p) + "\n"
				+ "§eFaction: §f" + fc + "\n" + "§eCargo: §f<" + fp.getCargo().getSimbolo() + "> "
				+ fp.getCargo().getNome();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("g")) {

			String path = "Mutes." + p.getName().toLowerCase();
			FileConfiguration config = net.dev.art.punir.Main.getInstance().getConfig();

			if (PunimentosAPI.checkMute(p) == true) {
				String punidor = config.getString(path + ".Punidor");
				String mstring = config.getString(path + ".Motivo").replace("_", " ");
				long millis = config.getLong(path + ".Milliseconds");
				if (millis < System.currentTimeMillis()) {
					PunimentosAPI.Despunir(p, "mute");
					return true;
				}

				p.sendMessage("          §cArtPunir     ");
				p.sendMessage("          ");
				p.sendMessage("§c§l♦ §c§f" + p.getName() + " §cfoi mutado permanetemente");

				p.sendMessage("§c§l♦ §c§cMutado por §f" + punidor);
				p.sendMessage("§c§l♦ §c§cMutado §f" + mstring);
				p.sendMessage("§c§l♦ §c§cMutado até: §f" + CalendarioAPI.getData(millis) + " as "
						+ CalendarioAPI.getHoras(millis));
				p.sendMessage("    ");
				return true;
			}

			if (ChatAPI.isMutado("global")) {
				if (GruposAPI.hasPermission(p, "dono")) {
					p.sendMessage("§cO chat §7Global§c está MUTADO!");
					p.playSound(p.getLocation(), Sound.CAT_MEOW, 1.0F, 0.5F);
					return false;
				}
			}

			String mensagem = ChatAPI.append(args);
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

			String fc = "";
			Faction fac = null;
			FactionPlayer fp = null;
			if ((fac = PlayersManager.getPlayer(p.getName()).getFac()) != null) {
				fp = PlayersManager.getPlayer(p.getName());
				String tag = "[" + fp.getCargo().getSimbolo() + fac.getTag() + "]";
				fc = " " + tag;
			} else {
				fc = "";
			}

			String grupo = GruposAPI.getGrupo(p.getName()).getPrefix();
			String formato = "§7(G) " + fc + " " + grupo + " "
					+ RanksAPI.getCurrentRank(p).getPrefix().replace("&", "§") + " §f" + p.getName() + "§8 » §7"
					+ ChatAPI.mensagem(p, mensagem);

			TextComponent tc = new TextComponent("");
			TextComponent pmsg = new TextComponent("§e" + ChatAPI.mensagem(p, mensagem));
			TextComponent chatprefix = new TextComponent("§7(G) ");
			TextComponent playerinfos = new TextComponent(fc + " " + grupo + " "
					+ RanksAPI.getCurrentRank(p).getPrefix().replace("&", "§") + " §f" + p.getName() + " ");
			playerinfos.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/tell " + p.getName()));
			playerinfos.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
					new BaseComponent[] { new TextComponent(format(p)) }));
			TextComponent separador = new TextComponent("§8» §e");
			tc.addExtra(chatprefix);
			tc.addExtra(playerinfos);
			tc.addExtra(separador);
			tc.addExtra(pmsg);

			ChatAPI.sendGlobal(tc);

		}
		return false;
	}

}
