package net.dev.art.rc.server;

import java.io.IOException;
import java.net.ServerSocket;

import org.bukkit.Bukkit;

public class Server {

	private static ServerSocket server = null;
	private static int port = 9999;

	public static void start() {
		try {
			ServerSocket server = new ServerSocket(getPort());
			setServer(server);
			Bukkit.getConsoleSender().sendMessage("§eServer Na Porta §b" + getPort() + " §eCriado com sucesso!");
			Thread WFC = new Thread(new WaitForConnection());
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§cErro ao tentar criar um server Na Porta §f" + getPort());
		}
	}

	public static void stop() {
		try {
			if (getServer() == null)
				return;
			getServer().close();
			Bukkit.getConsoleSender().sendMessage("§cServer Na Porta §f" + getPort() + " §cFechado com sucesso!");
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§4Erro ao tentar criar um server Na Porta §f" + getPort());
		}
	}

	public static ServerSocket getServer() {
		return server;
	}

	public static void setServer(ServerSocket server) {
		Server.server = server;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		Server.port = port;
	}

}
