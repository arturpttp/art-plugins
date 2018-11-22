package net.dev.art.rc.server;

import java.io.IOException;
import java.net.Socket;

import org.bukkit.Bukkit;

import net.dev.art.rc.clients.Client;
import net.dev.art.rc.utils.Utils;

public class WaitForConnection extends Thread {

	public void run() {
		while (!Server.getServer().isClosed()) {
			try {
				Socket client = Server.getServer().accept();
				Bukkit.getConsoleSender().sendMessage("§b["+client.getInetAddress().getHostName()+"]§e Cliente conectado §8»§e Tentando Autenticar... ");
				Client clientClass = new Client(client);
				
			} catch (IOException e) {
				Bukkit.getConsoleSender()
						.sendMessage("§cErro no §fSOCKET §cda classe §fWaitForConnection§c no metodo§f run()§c!");
			}

		}
	}

}
