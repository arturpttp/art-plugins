package net.dev.art.rc.clients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;

import net.dev.art.rc.utils.Crypter;
import net.dev.art.rc.utils.Utils;

public class ReadData extends Thread {

	Client client;

	public ReadData(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	@Override
	public void run() {
		while (!client.getClient().isClosed()) {
			try {
				InputStream in = client.getClient().getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				String data = null;

				while (!client.getClient().isClosed() && (data = reader.readLine()) != null) {
					client.readData(Crypter.decode(data));
				}

			} catch (Exception e) {
				Bukkit.getConsoleSender().sendMessage("§cErro ao §fLer a Data§c reporte usando ID:§f#1");
				Utils.connectedClients.remove(this.client);
				try {
					client.getClient().close();
					this.start();
				} catch (Exception e2) {
					Bukkit.getConsoleSender().sendMessage("§cErro ID:§f #3");
				}
			}
		}
	}

}
