package net.dev.art.chat.Comandos;

import net.dev.art.chat.APIs.ChatAPI;
import net.dev.art.chat.Utils.Mensagens;
import net.dev.art.grupos.api.GruposAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand extends Mensagens implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(NoPlayer);
        }
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("chat")) {
            if (GruposAPI.hasPermission(p, "dono")) {
                p.sendMessage(NoPerm);
                p.playSound(p.getLocation(), Sound.CAT_MEOW, 1.0F, 0.5F);
                return false;
            }
            if (args.length == 0) {
                ChatAPI.sendHelp(p);
            } else {
                if (args.length == 1) {
                    String sb = args[0];
                    if (!sb.equalsIgnoreCase("limpar") && !sb.equalsIgnoreCase("info") && !sb.equalsIgnoreCase("mutar")
                            && !sb.equalsIgnoreCase("desmutar") && !sb.equalsIgnoreCase("help")) {
                        mensagem(p, "§cAgurmento Invalido `§r" + sb + "§c` Digite §c/chat help");
                        return true;
                    }
                    if (sb.equalsIgnoreCase("limpar")) {
                        ChatAPI.LimparChat();
                    }
                    if (sb.equalsIgnoreCase("help")) {
                        ChatAPI.sendHelp(p);
                    }
                    if (sb.equalsIgnoreCase("info")) {
                        ChatAPI.sendInfo(p);
                    }
                    if (sb.equalsIgnoreCase("mutar")) {
                        p.sendMessage("§e/chat mutar (local|staff|global) §8 § §6mutar um canal");
                    }
                    if (sb.equalsIgnoreCase("desmutar")) {
                        p.sendMessage("§e/chat desmutar (local|staff|global) §8 § §6desmutar um canal mutado");
                    }
                }
                if (args.length == 2) {
                    String sb = args[0];
                    String canal = args[1];
                    if (sb.equalsIgnoreCase("mutar")) {
                        if (!canal.equalsIgnoreCase("staff") && !canal.equalsIgnoreCase("global")
                                && !canal.equalsIgnoreCase("local")) {
                            mensagem(p, "§cAgurmento Invalido `§r" + canal + "§c` Digite §c/chat help");
                            return true;
                        }
                        if (canal.equalsIgnoreCase("global")) {
                            if (ChatAPI.isMutado("global")) {
                                mensagem(p, "§cO Chat §7Global§c Foi Mutado!");
                                return true;
                            }

                            ChatAPI.Mutar("global");
                            Bukkit.broadcastMessage(prefix + "§cO Chat §7Global§c Foi Mutado");

                        }

                        if (canal.equalsIgnoreCase("local")) {
                            if (ChatAPI.isMutado("local")) {
                                mensagem(p, "§cO Chat §eLocal§c Foi Desutado!");
                                return true;
                            }

                            ChatAPI.Mutar("local");
                            Bukkit.broadcastMessage(prefix + "§cO Chat §eLocal§c Foi Mutado");

                        }

                        if (canal.equalsIgnoreCase("staff")) {
                            if (ChatAPI.isMutado("staff")) {
                                mensagem(p, "§cO Chat §5Staff§c Foi Desutado!");
                                return true;
                            }

                            ChatAPI.Mutar("staff");
                            Bukkit.broadcastMessage(prefix + "§cO Chat §5Staff§c Foi Mutado");

                        }

                    }

                    if (sb.equalsIgnoreCase("desmutar")) {
                        if (!canal.equalsIgnoreCase("staff") && !canal.equalsIgnoreCase("global")
                                && !canal.equalsIgnoreCase("local")) {
                            mensagem(p, "§cAgurmento Invalido `§r" + canal + "§c` Digite §c/chat help");
                            return true;
                        }
                        if (canal.equalsIgnoreCase("global")) {
                            if (!ChatAPI.isMutado("global")) {
                                mensagem(p, "§cO Chat §7Global§c N§o Est§ Mutado!");
                                return true;
                            }

                            ChatAPI.DesMutar("global");
                            Bukkit.broadcastMessage(prefix + "§cO Chat §7Global§c Foi Desmutado");

                        }

                        if (canal.equalsIgnoreCase("local")) {
                            if (!ChatAPI.isMutado("local")) {
                                mensagem(p, "§cO Chat §eLocal§c N§o Est§ Mutado!");
                                return true;
                            }

                            ChatAPI.DesMutar("local");
                            Bukkit.broadcastMessage(prefix + "§cO Chat §eLocal§c Foi Desmutado");

                        }

                        if (canal.equalsIgnoreCase("staff")) {
                            if (!ChatAPI.isMutado("staff")) {
                                mensagem(p, "§cO Chat §5Staff§c N§o Est§ Mutado!");
                                return true;
                            }

                            ChatAPI.DesMutar("staff");
                            Bukkit.broadcastMessage(prefix + "§cO Chat §5Staff§c Foi Desmutado");

                        }

                    }
                }
            }
        }
        return false;
    }

}
