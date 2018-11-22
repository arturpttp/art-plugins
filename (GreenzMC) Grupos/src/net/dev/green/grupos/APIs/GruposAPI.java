package net.dev.green.grupos.APIs;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.dev.green.grupos.Main;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;

public class GruposAPI {

	public enum GruposTipos {

		Dono, Diretor, Gerente, Admin, Moderador, Ajudante, Youtube, MiniYT, Folhagem, Leaf, Tree, Green, Membro;

	}

	public static void disguisePlayer(Player p, String newName) {
		EntityHuman eh = ((CraftPlayer) p).getHandle();
		PacketPlayOutEntityDestroy p29 = new PacketPlayOutEntityDestroy(new int[] { p.getEntityId() });
		PacketPlayOutNamedEntitySpawn p20 = new PacketPlayOutNamedEntitySpawn(eh);
		try {
			Field profileField = p20.getClass().getDeclaredField("b");
			profileField.setAccessible(true);
			profileField.set(p20, new GameProfile(p.getUniqueId(), newName));
		} catch (Exception e) {
			Bukkit.broadcastMessage("Not Work!");
		}
		for (Player o : Bukkit.getOnlinePlayers()) {
			if (!o.getName().equals(p.getName())) {
				((CraftPlayer) o).getHandle().playerConnection.sendPacket(p29);
				((CraftPlayer) o).getHandle().playerConnection.sendPacket(p20);
			}
		}
	}

	public static HashMap<String, GruposTipos> grupos = new HashMap<>();

	public static void setNamePlayer(Player p, String prefix) {
//		setHeadTag(p, prefix);
		p.setCustomName(prefix + " " + p.getName());
		p.setPlayerListName(prefix + " " + p.getName());
//		disguisePlayer(p, prefix);
	}

	public static List<GruposTipos> gruposl = new ArrayList<>();

	public static int getPremissionLevel(GruposTipos g) {
		switch (g) {
		case Dono:
		case Gerente:
			return 4;
		case Admin:
		case Ajudante:
		case Diretor:
		case Moderador:
			return 3;
		case MiniYT:
		case Youtube:
			return 2;
		case Folhagem:
		case Green:
		case Leaf:
		case Tree:
			return 1;
		case Membro:
			return 0;
		default:
			break;
		}
		return 0;
	}

	// for (GruposTipos g : getPermissions(1)) {
	// System.out.println(g.toString());
	// }

	public static String getPrefix(GruposTipos g) {
		if (g == GruposTipos.Dono) {
			return "§4§lDONO§4";
		} else if (g == GruposTipos.Diretor) {
			return "§4§lDIRETOR§4";
		} else if (g == GruposTipos.Admin) {
			return "§c§lADMIN§c";
		} else if (g == GruposTipos.Ajudante) {
			return "§e§lAJUDANTE§e";
		} else if (g == GruposTipos.Folhagem) {
			return "§aFOLHAGEM§a";
		} else if (g == GruposTipos.Gerente) {
			return "§6§lGERENTE§6";
		} else if (g == GruposTipos.Green) {
			return "§2§lGREEN§2";
		} else if (g == GruposTipos.Leaf) {
			return "§3§lLEAF§3";
		} else if (g == GruposTipos.Membro) {
			return "§8§lMEMBRO§7";
		} else if (g == GruposTipos.MiniYT) {
			return "§b§lMiniYT§b";
		} else if (g == GruposTipos.Moderador) {
			return "§2§lMODERADOR§2";
		} else if (g == GruposTipos.Tree) {
			return "§a§lTREE§a";
		} else if (g == GruposTipos.Youtube) {
			return "§b§lYouTuber§b";
		}
		return "";
	}

	public static String getPrefix(Player p) {
		return getPrefix(getGrupo(p));
	}

	public static String getGrupoName(GruposTipos g) {
		if (g == GruposTipos.Dono) {
			return "DONO";
		} else if (g == GruposTipos.Diretor) {
			return "DIRETOR";
		} else if (g == GruposTipos.Admin) {
			return "ADMIN";
		} else if (g == GruposTipos.Ajudante) {
			return "AJUDANTE";
		} else if (g == GruposTipos.Folhagem) {
			return "FOLHAGEM";
		} else if (g == GruposTipos.Gerente) {
			return "GERENTE";
		} else if (g == GruposTipos.Green) {
			return "GREEN";
		} else if (g == GruposTipos.Leaf) {
			return "LEAF";
		} else if (g == GruposTipos.Membro) {
			return "MEMBRO";
		} else if (g == GruposTipos.MiniYT) {
			return "MiniYT";
		} else if (g == GruposTipos.Moderador) {
			return "MODERADOR";
		} else if (g == GruposTipos.Tree) {
			return "TREE";
		} else if (g == GruposTipos.Youtube) {
			return "YouTuber";
		}
		return "";
	}

	public static GruposTipos getGrupo(Player p) {
		if (Main.getGruposConfig().contains(p.getName())) {
			return getGrupoByName(Main.getGruposConfig().getString(p.getName()));
		} else {
			return GruposTipos.Membro;
		}
	}

	public static GruposTipos getGrupo(String p) {
		if (Main.getGruposConfig().contains(p)) {
			return getGrupoByName(Main.getGruposConfig().getString(p));
		} else {
			return GruposTipos.Membro;
		}
	}

	public static GruposTipos getGrupoByName(String name) {

		if (name.equalsIgnoreCase("DIRETOR")) {
			return GruposTipos.Diretor;

		} else if (name.equalsIgnoreCase("DONO")) {
			return GruposTipos.Dono;
		} else if (name.equalsIgnoreCase("ADMIN")) {
			return GruposTipos.Admin;
		} else if (name.equalsIgnoreCase("AJUDANTE")) {
			return GruposTipos.Ajudante;
		} else if (name.equalsIgnoreCase("FOLHAGEM")) {
			return GruposTipos.Folhagem;
		} else if (name.equalsIgnoreCase("GERENTE")) {
			return GruposTipos.Gerente;
		} else if (name.equalsIgnoreCase("GREEN")) {
			return GruposTipos.Green;
		} else if (name.equalsIgnoreCase("LEAF")) {
			return GruposTipos.Leaf;
		} else if (name.equalsIgnoreCase("MEMBRO")) {
			return GruposTipos.Membro;
		} else if (name.equalsIgnoreCase("MiniYT")) {
			return GruposTipos.MiniYT;
		} else if (name.equalsIgnoreCase("MODERADOR")) {
			return GruposTipos.Moderador;
		} else if (name.equalsIgnoreCase("TREE")) {
			return GruposTipos.Tree;
		} else if (name.equalsIgnoreCase("YouTuber")) {
			return GruposTipos.Youtube;
		}
		return null;
	}

	public static GruposTipos getGrupo(CommandSender sender) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (Main.getGruposConfig().contains(p.getName())) {
				return getGrupoByName(Main.getGruposConfig().getString(p.getName()));
			} else {
				return GruposTipos.Membro;
			}
		} else {
			return GruposTipos.Dono;
		}
	}

	public static void setGrupo(Player p, GruposTipos g) {
		grupos.put(p.getName(), g);
		setNamePlayer(p, getPrefix(g));
		Main.getGruposConfig().set(p.getName(), getGrupoName(g));
		Main.saveGruposConfig();
	}

	public static void setGrupo(Player p, String g) {
		grupos.put(p.getName(), getGrupoByName(g));
		setNamePlayer(p, getPrefix(getGrupoByName(g)));
		Main.getGruposConfig().set(p.getName(), getGrupoName(getGrupoByName(g)));
		Main.saveGruposConfig();
	}

}
