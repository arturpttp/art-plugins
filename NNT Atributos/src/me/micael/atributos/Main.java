package me.micael.atributos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.micael.atributos.comandos.Atributos;
import me.micael.atributos.eventos.Events;
import me.micael.atributos.manager.ConfigAPI;
import me.micael.atributos.manager.DBManager;

public class Main extends JavaPlugin {

	public static DBManager db;
	public static ConfigAPI config;
	public static HashMap<String, Integer> forca = new HashMap<>();
	public static HashMap<String, Integer> vida = new HashMap<>();
	public static HashMap<String, Integer> defesa = new HashMap<>();

	@Override
	public void onEnable() {
		config = new ConfigAPI("config.yml", this);

		Bukkit.getPluginManager().registerEvents(new Events(), this);
		getCommand("atributos").setExecutor(new Atributos());

		DBManager.setDebug(false);
		db = new DBManager();
//		db.setHost(config.getString("MYSQL.ip"));
//		db.setPort(config.getString("MYSQL.port"));
//		db.setUser(config.getString("MYSQL.user"));
//		db.setPass(config.getString("MYSQL.password"));
		config.saveDefaultConfig();
		db = new DBManager("root", "", "localhost", "artplugins");
		db.openConnection();
		try {
			if (db.getConnection() == null || db.getConnection().isClosed()) {
				db.openConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.createTable("Atributos", "Nome VARCHAR(16),  Forca INTEGER(30), Vida INTEGER(30), Defesa INTEGER(30)");
		db.openConnection();
	}

//	public static List<FactionPlayer> getAllPlayers() {

	void loadAtributos() {
		try {
			if (db.getConnection() == null || db.getConnection().isClosed()) {
				db.openConnection();
			}
			PreparedStatement stm = db.getConnection().prepareStatement("select * from `Atributos`");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				try {
					if (db.getConnection() == null || db.getConnection().isClosed()) {
						db.openConnection();
					}
					defesa.put(rs.getString("Nome"), rs.getInt("Defesa"));
					vida.put(rs.getString("Nome"), rs.getInt("Vida"));
					forca.put(rs.getString("Nome"), rs.getInt("Forca"));

				} catch (Exception e) {
					System.out.println("Falha ao carregar ATRIBUTOS: " + e);
					continue;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void saveAtributos() {

	}

	@Override
	public void onDisable() {
		config.saveConfig();
		db.closeConnection();
	}
}
