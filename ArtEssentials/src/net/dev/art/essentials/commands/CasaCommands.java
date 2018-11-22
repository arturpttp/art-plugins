package net.dev.art.essentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.dev.art.essentials.Main;
import net.dev.art.essentials.apis.CasasAPI;
import net.dev.art.essentials.manager.CasasManager;
import net.dev.art.essentials.manager.CasasManager.Help;
import net.dev.art.essentials.objetos.Casa;
import net.dev.art.essentials.utils.Mensagens;
import net.dev.green.grupos.APIs.GruposAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class CasaCommands extends Mensagens implements CommandExecutor {

	public void send(Help comand, Player p) {
		String comando = comand.getCommand().replace("§6", "").replace("§e", "").split("<")[0];
		TextComponent cmdd = new TextComponent(comand.getCommand());
		cmdd.setHoverEvent(
				new HoverEvent(Action.SHOW_TEXT, new BaseComponent[] { new TextComponent(comand.getDescription()) }));
		TextComponent tc = new TextComponent("§b * §e");

		cmdd.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, comando));
		tc.addExtra(cmdd);
		p.spigot().sendMessage(tc);
	}

	public void teleportar(Player p, Casa c, String proprietario) {
		new BukkitRunnable() {
			int tempo = 3;

			public void run() {
				if (tempo >= 1) {
					if (p.getName().equalsIgnoreCase(proprietario)) {
						p.sendTitle("§aCasas",
								"§eVocê sera teleportado para casa `§b" + c.getNome() + "§e` em §b" + tempo);

					} else {
						p.sendTitle("§aCasas", "§eVocê sera teleportado para casa `§b" + c.getNome()
								+ "§e` do proprietario `§b" + proprietario + "§e` em §b" + tempo);
					}

					tempo--;
				} else if (tempo == 0) {
					p.sendTitle("§aCasas", "§eTeleportando...");

					try {
						p.teleport(c.getLocation());
					} catch (Exception e) {
						mensagem(p, "§cO spawn não foi setado!");

					}
					tempo--;
				}

			}
		}.runTaskTimer(Main.getInstance(), 20, 20);
	}

	protected boolean justNumbersLetters(String casa) {
		for (char c : casa.toCharArray()) {
			if (!Character.isLetterOrDigit(c)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(NoPlayer);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("casa")) {
			if (!(GruposAPI.getPremissionLevel(GruposAPI.getGrupo(p)) > 2)) {
				p.sendMessage(NoPerm);
				return true;
			}

			if (args.length == 0) {
				CasasManager.sendhelp(p);
			} else {
				if (args[0].equalsIgnoreCase("criar")) {
					if (args.length > 1) {
						String nome = args[1];
						if(justNumbersLetters(nome)) {
							Casa casa = CasasAPI.criarCasa(p, nome);
							mensagem(p, "§eA sua casa foi criada com sucesso acesse-a digitando §e/casa ir §6" + nome);
						}else {
							mensagem(p, "§cO nome não pode conter numeros e letras");
						}
					} else {
						send(Help.criar, p);
					}
				} else if (args[0].equalsIgnoreCase("listar")) {
					if (CasasAPI.getCasas(p.getName()).size() == 0) {
						mensagem(p, "§cNenhuma casa disponivel, use " + Help.criar.getCommand());
						return true;
					}

					List<Casa> casas = new ArrayList<>();
					List<Casa> publicas = new ArrayList<>();
					for (Casa casa : CasasAPI.getCasas(p.getName())) {
						if (casa.isPublica()) {
							casas.add(casa);
						} else {
							publicas.add(casa);
						}
					}
					if (casas.size() != 0) {
						p.sendMessage("");
						TextComponent tc = new TextComponent("");
						tc.addExtra(new TextComponent("§eCasas: §b"));
						for (Casa cp : casas) {
							String virgula = ",";
							if (casas.lastIndexOf(cp) == (casas.size() - 1))
								virgula = ".";
							tc.addExtra(new TextComponent(" "));
							TextComponent casa = new TextComponent(cp.getNome());
							casa.setHoverEvent(new HoverEvent(Action.SHOW_TEXT,
									new BaseComponent[] { new TextComponent(CasasAPI.format(cp)) }));
							casa.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND,
									"/casa ir " + cp.getNome()));
							tc.addExtra("§b" + casa + "§f");
							tc.addExtra(new TextComponent(virgula));
						}
						p.spigot().sendMessage(tc);
					}

					if (publicas.size() != 0) {
						TextComponent tc = new TextComponent("");
						tc.addExtra(new TextComponent("§ePublicas: §b"));
						for (Casa cp : publicas) {
							String virgula = ",";
							if (publicas.lastIndexOf(cp) == (publicas.size() - 1))
								virgula = ".";
							tc.addExtra(new TextComponent(" "));
							TextComponent casa = new TextComponent(cp.getNome());
							casa.setHoverEvent(new HoverEvent(Action.SHOW_TEXT,
									new BaseComponent[] { new TextComponent(CasasAPI.format(cp)) }));
							casa.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND,
									"/casa ir " + cp.getNome()));
							tc.addExtra(casa);
							tc.addExtra(new TextComponent(virgula));
						}
						p.spigot().sendMessage(tc);
						p.sendMessage("");
					}

				} else if (args[0].equalsIgnoreCase("ir")) {
					if (args.length > 1) {
						String dados = args[1];
						String nome = "";
						Casa casa = null;
						String player = p.getName();
						if (dados.contains(":")) {
							nome = dados.split(":")[1];
							player = dados.split(":")[0];
						} else
							nome = dados;
						casa = CasasAPI.getCasaPeloDono(player, nome);
						if (casa != null) {
							if (player.equals(p.getName()) || casa.isPublica()) {
								teleportar(p, casa, player);
							} else {
								mensagem(p, "§cEstá casa é privada!");
							}
						} else {
							mensagem(p, "§cEstá casa não existe!");
						}
					} else {
						send(Help.teleportar, p);
					}
				} else if (args[0].equalsIgnoreCase("remover")) {
					if (args.length > 1) {
						String dados = args[1];
						Casa casa = CasasAPI.getCasaPeloNome(p.getName(), dados);
						if (casa == null) {
							mensagem(p, "Essa casa não existe!");
							return true;
						}
						mensagem(p, "§cVocê deletou a sua casa: " + casa.getNome());
						casa.delete();
					} else {
						send(Help.remover, p);
					}
				} else if (args[0].equalsIgnoreCase("publica")) {
					if (args.length > 1) {
						String dados = args[1];
						Casa casa = CasasAPI.getCasaPeloNome(p.getName(), dados);
						if (casa == null) {
							mensagem(p, "Essa casa não existe!");
							return true;
						}

						casa.setPublica(!casa.isPublica());
						String privacidade = "privada";
						if (casa.isPublica()) {
							privacidade = "publica";
						}
						String adicional = "§cAgora ninguem podera acessa-la, apenas você!";
						if (casa.isPublica())
							adicional = "§eAgora todos poderão acessa-la digitando: §b/casa ir " + p.getName() + ":"
									+ casa.getNome();
						mensagem(p, "§eA privacidade da sua casa foi alterada para `§b" + privacidade + "§e`");
						mensagem(p, adicional);

					} else {
						send(Help.publica, p);
					}
				} else if (args[0].equalsIgnoreCase("ajuda")) {
					CasasManager.sendhelp(p);
				}

			}

		}
		return false;
	}

}
