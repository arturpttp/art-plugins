package net.dev.art.facs.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;

import net.dev.art.core.utils.ArtLib;
import net.dev.art.facs.Main;
import net.dev.art.facs.objects.Faction;
import net.dev.art.facs.objects.FactionPlayer;

public class MySQLManager implements ArtLib {

	public static Connection con = Main.getInstance().db.getConnection();

	public static void deleteFaction(String fac) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("DELETE FROM `factionfacdb` WHERE name='" + fac + "'");
			stm.setString(1, fac);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				Main.factions.remove(fac);
				if (debug) {
					Bukkit.getConsoleSender().sendMessage("§a[DEBUG] §8» §cDeletando facção: §e" + fac);
				}
			}
		} catch (SQLException e) {
			if (debug) {
				Bukkit.getConsoleSender().sendMessage("§a[DEBUG] §8» §cERRO ao tentar deletar facção: §e" + fac);
			}
		}
	}

	public static boolean contains(String from, String where, String obj) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement("SELECT * FROM `" + from + "` WHERE `" + where + "` = ?");
			stm.setString(1, obj);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public static List<FactionPlayer> getAllPlayers() {
		PreparedStatement stm = null;
		List<FactionPlayer> tops = new ArrayList<FactionPlayer>();
		try {
			stm = con.prepareStatement("SELECT * FROM `factionplayerdb`");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				try {
					if (con == null || con.isClosed()) {
						Main.getInstance().db.openConnection();
					}
					FactionPlayer p = new FactionPlayer(rs.getString("ID"), rs.getString("nick"), rs.getString("fac"),
							rs.getInt("kills"), rs.getInt("deaths"), rs.getInt("power"), rs.getInt("maxpower"));
					tops.add(p);
				} catch (Exception e) {
					System.out.println("Falha ao carregar jogador: " + e);
					continue;
				}
			}
		} catch (SQLException e) {
		}
		return tops;
	}

	public static List<Faction> getAllFactions() {
		PreparedStatement stm = null;
		List<Faction> tops = new ArrayList<>();
		try {
			if (con == null || con.isClosed()) {
				Main.getInstance().db.openConnection();
			}
			stm = con.prepareStatement("SELECT * FROM `factionfacdb`");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				try {
					List<String> membros = ArtFacManager.unstripString(rs.getString("membros"));
					List<String> aliados = ArtFacManager.unstripString(rs.getString("aliados"));
					List<String> inimigos = ArtFacManager.unstripString(rs.getString("inimigos"));
					List<String> capitoes = ArtFacManager.unstripString(rs.getString("capitoes"));
					List<String> recrutas = ArtFacManager.unstripString(rs.getString("recrutas"));
					Faction p = new Faction(rs.getString("ID"), aliados, inimigos,
							Double.valueOf(rs.getString("money")), capitoes, membros, recrutas, rs.getString("name"),
							rs.getString("lider"), rs.getString("tag"));
					tops.add(p);
				} catch (Exception e) {
					System.out.println("Falha ao carregar jogador: " + e);
					continue;
				}
			}
		} catch (SQLException e) {
		}
		return tops;
	}

}
