package net.dev.green.grupos.Comandos;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.dev.green.grupos.Mensagens;
import net.dev.green.grupos.APIs.GruposAPI;
import net.dev.green.grupos.APIs.GruposAPI.GruposTipos;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;

public class setGrupo extends Mensagens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setgrupo")) {
//			if (sender instanceof Player) {
//				Player p = (Player) sender;
//				if (GruposAPI.getGrupo(p) != GruposTipos.Diretor || GruposAPI.getGrupo(p) != GruposTipos.Dono && p.isOp() == false) {
//					sendPerm(GruposAPI.getPrefix(GruposTipos.Diretor), p);
//					return true;
//				}
//			}
//			
			if (!(sender.hasPermission("greenz.grupo.set"))) {
				sendPerm(GruposAPI.getPrefix(GruposTipos.Diretor), sender);
				return true;
			}

			if (args.length == 0) {
				sendToConsole("§cUtilize: §7/setGrupo §r<Grupo> (Player)", sender);
			}
			if (args.length == 1) {
				String grupo = args[0];
				if (sender instanceof Player) {
					Player p = (Player) sender;
					try {
						GruposAPI.setGrupo(p, grupo);
						sendToPlayer("§2Seu Grupo Foi Alterado Para " + GruposAPI.getPrefix(GruposAPI.getGrupoByName(grupo)) + " §2Com Sucesso", p);
					} catch (Exception e) {
						sendToConsole("§cGrupo §r" + grupo.toUpperCase() + " §cNão Existe", sender);
					}
				}else{
					sendToConsole("§cUtilize: §7/setGrupo §r<Grupo> (Player)", sender);
				}
			}

			if (args.length > 1) {
				String grupo = args[0];
				Player t = Bukkit.getPlayer(args[1]);
				if (t == null) {
					sendToConsole("§cEsse Jogador Não Está Online", sender);
					return true;
				}
				try {
					GruposAPI.setGrupo(t, grupo);
					sendToConsole("§2Você Alterou o Grupo De " + t.getName() + " §2Para " + GruposAPI.getPrefix(GruposAPI.getGrupoByName(grupo)) + " §2Com Sucesso", sender);
				} catch (Exception e) {
					sendToConsole("§cGrupo §r" + grupo.toUpperCase() + " §cNão Existe", sender);
				}
				if(t.isOnline()) {
					sendToPlayer("§aSeu Grupo Foi Alterado Para " + GruposAPI.getPrefix(GruposAPI.getGrupoByName(grupo)), t);
				}
			}

		}
		return false;
	}

}
