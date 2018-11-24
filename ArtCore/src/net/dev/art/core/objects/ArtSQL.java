package net.dev.art.core.objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;

import net.dev.art.api.Main;

public class ArtSQL {

	boolean debug;

	public void desativarDebug() {
		this.debug = false;
	}

	public void ativarDebug() {
		this.debug = true;
	}

	public ArtSQL() {
		Connection c = null;
		try {
			c = getConnection();
			Bukkit.getConsoleSender().sendMessage(prefix + "§eConexão com o banco de dados estabelecida com sucesso!");
			createTables();
		} catch (Exception e) {
			Bukkit.getConsoleSender()
					.sendMessage(prefix + "§cNão foi possivel estabelecer conexão com o banco de dados!");
		}

	}

	protected String prefix = "§aMySQL§8 » ";

	public Connection getConnection() throws SQLException {
		String user = Main.getInstance().getConfig().getString("MySQL.username");
		String senha = Main.getInstance().getConfig().getString("MySQL.password");
		String host = Main.getInstance().getConfig().getString("MySQL.host");
		String database = Main.getInstance().getConfig().getString("MySQL.database");
		String url = "jdbc:mysql://" + host + ":3306/" + database;
		return DriverManager.getConnection(url, user, senha);
	}

	public void close() {
		Connection c;
		try {
			c = getConnection();
			c.close();
			Bukkit.getConsoleSender().sendMessage(prefix + "§eConexão com o banco de dados fechada com sucesso!");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(prefix + "§eNão foi possivel fechar a conexão com o banco de dados!");
		}

	}

	public void execute(String comando) throws SQLException {
		Connection c = getConnection();
		PreparedStatement pst = c.prepareStatement(comando);
		pst.executeUpdate();
		pst.close();
		c.close();
	}

	protected void createTables() {
		try {
			List<String> tables = new ArrayList<>();

			execute("CREATE TABLE IF NOT EXISTS usuarios (" + "name VARCHAR(250) PRIMARY KEY, dados VARCHAR(250)"
					+ ");");
			execute("CREATE TABLE IF NOT EXISTS password (" + "name VARCHAR(250) PRIMARY KEY, dados VARCHAR(250)"
					+ ");");

			tables.add("usuarios");
			tables.add("password");
			Bukkit.getConsoleSender().sendMessage(prefix + "§aTabelas criadas com sucesso!");
			for (String table : tables) {
				Bukkit.getConsoleSender().sendMessage("§aTabelas§8 » " + "§8-> §b" + table);
			}
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(prefix + "§cErro ao tentar criar as tabelas!");
		}
	}

}
