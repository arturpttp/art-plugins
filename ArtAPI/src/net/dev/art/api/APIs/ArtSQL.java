package net.dev.art.api.APIs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;

import net.dev.art.api.Main;

public class ArtSQL {

	public ArtSQL() {
		Connection c = null;
		try {
			c = getConnection();
			Bukkit.getConsoleSender().sendMessage(prefix + "�eConex�o com o banco de dados estabelecida com sucesso!");
			createTables();
		} catch (Exception e) {
			Bukkit.getConsoleSender()
					.sendMessage(prefix + "�cN�o foi possivel estabelecer conex�o com o banco de dados!");
		}

	}

	protected String prefix = "�aMySQL�8 � ";

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
			Bukkit.getConsoleSender().sendMessage(prefix + "�eConex�o com o banco de dados fechada com sucesso!");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(prefix + "�eN�o foi possivel fechar a conex�o com o banco de dados!");
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
			execute("CREATE TABLE IF NOT EXISTS teste (" + "name VARCHAR(250) PRIMARY KEY, dados VARCHAR(250)" + ");");
			execute("CREATE TABLE IF NOT EXISTS usuarios (" + "name VARCHAR(250) PRIMARY KEY, dados VARCHAR(250)"
					+ ");");

			Bukkit.getConsoleSender().sendMessage(prefix + "�aTabelas criadas com sucesso!");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(prefix + "�cErro ao tentar criar as tabelas!");
		}
	}

}
