package net.dev.art.chat.Comandos;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.dev.art.api.APIs.CalendarioAPI;
import net.dev.art.chat.APIs.ChatAPI;
import net.dev.art.chat.Utils.Mensagens;
import net.dev.art.grupos.api.GruposAPI;
import net.dev.art.punir.PunimentosAPI;
import net.dev.art.rank.RanksAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class StaffChatCommand extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("sc")) {

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

			if (ChatAPI.isMutado("staff")) {
				if (GruposAPI.hasPermission(p, "dono")) {
					p.sendMessage("§cO chat §5Staff§c está MUTADO!");
					p.playSound(p.getLocation(), Sound.CAT_MEOW, 1.0F, 0.5F);
					return false;
				}
			}

			if (GruposAPI.hasPermission(p, "dono")) {
				p.sendMessage(NoPerm);
				return true;
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

			String grupo = GruposAPI.getGrupo(p.getName()).getPrefix();
			String formato = "§5(STAFF) " + grupo + " " + RanksAPI.getCurrentRank(p).getPrefix().replace("&", "§")
					+ "§f " + p.getName() + "§8 » §5" + ChatAPI.mensagem(p, mensagem);

			TextComponent tc = new TextComponent("");
			TextComponent pmsg = new TextComponent("§e" + ChatAPI.mensagem(p, mensagem));
			TextComponent chatprefix = new TextComponent("§5(STAFF) ");
			TextComponent playerinfos = new TextComponent(
					grupo + " " + RanksAPI.getCurrentRank(p).getPrefix().replace("&", "§") + " §f" + p.getName() + " ");
			playerinfos.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/tell " + p.getName()));
			playerinfos.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
					new BaseComponent[] { new TextComponent(GlobalChat.format(p)) }));
			TextComponent separador = new TextComponent("§8» §e");
			tc.addExtra(chatprefix);
			tc.addExtra(playerinfos);
			tc.addExtra(separador);
			tc.addExtra(pmsg);

			ChatAPI.sendStaff(tc);
		}
		return false;
	}

}
