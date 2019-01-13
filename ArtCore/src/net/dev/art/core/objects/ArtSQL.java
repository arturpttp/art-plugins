package net.dev.art.core.objects;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;

import net.dev.art.core.ArtCore;

public class ArtSQL {

	static boolean debug;
	static SQLType type = (ArtCore.getInstance().getConfig().getBoolean("MySQL.enabled")) ? SQLType.MySQL
			: SQLType.SQLite;
	static Connection c = null;

	public void desativarDebug() {
		debug = false;
	}

	public static void ativarDebug() {
		debug = true;
	}

	public static void iniciar() {

		if (type == SQLType.MySQL) {
			try {
				c = getConnection();
				Bukkit.getConsoleSender()
						.sendMessage(prefix + "§eConexão com o banco de dados estabelecida com sucesso!");
				createTables();
			} catch (Exception e) {
				Bukkit.getConsoleSender()
						.sendMessage(prefix + "§cNão foi possivel estabelecer conexão com o banco de dados!");
				ArtCore.getInstance().getPluginLoader().disablePlugin(ArtCore.getInstance());
			}
		} else if (type == SQLType.SQLite) {
			sqliteCon();
		}

	}

	public static SQLType getType() {
		return type;
	}

	public static void setType(SQLType type) {
		ArtSQL.type = type;
	}

	protected static void sqliteCon() {
		File db = new File(ArtCore.getInstance().getDataFolder(), "database.db");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + db);
		} catch (SQLException | ClassNotFoundException e) {
			Bukkit.getConsoleSender().sendMessage(prefix + "§cNão foi possivel estabelecer conexão com o Sqlite!");
			ArtCore.getInstance().getPluginLoader().disablePlugin(ArtCore.getInstance());
		}
	}

	public ArtSQL() {

	}

	protected static String prefix = "§aMySQL§8 » ";

	public static Connection getConnection() throws SQLException {
		String user = ArtCore.getInstance().getConfig().getString("MySQL.username");
		String senha = ArtCore.getInstance().getConfig().getString("MySQL.password");
		String host = ArtCore.getInstance().getConfig().getString("MySQL.host");
		String database = ArtCore.getInstance().getConfig().getString("MySQL.database");
		String url = "jdbc:mysql://" + host + ":3306/" + database;
		return DriverManager.getConnection(url, user, senha);
	}

	public static void close() {
		try {
			c = getConnection();
			c.close();
			Bukkit.getConsoleSender().sendMessage(prefix + "§eConexão com o banco de dados fechada com sucesso!");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(prefix + "§eNão foi possivel fechar a conexão com o banco de dados!");
		}

	}

	public static void execute(String comando) throws SQLException {
		c = getConnection();
		PreparedStatement pst = c.prepareStatement(comando);
		pst.executeUpdate();
		pst.close();
		c.close();
	}

	protected static void createTables() {
		try {
			List<String> tables = new ArrayList<>();

			execute("CREATE TABLE IF NOT EXISTS usuarios (" + "name TEXT(250) PRIMARY KEY, dados TEXT(250)" + ");");
			execute("CREATE TABLE IF NOT EXISTS password (" + "name TEXT(250) PRIMARY KEY, dados TEXT(250)" + ");");

			tables.add("usuarios");
			tables.add("password");
			for (String table : tables) {
				Bukkit.getConsoleSender().sendMessage("§aCriando Tabela§8 » " + "§8-> §b" + table);
			}
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage(prefix + "§cErro ao tentar criar as tabelas!");
		}
	}

	public static enum SQLType {
		SQLite, MySQL;

	}

}
