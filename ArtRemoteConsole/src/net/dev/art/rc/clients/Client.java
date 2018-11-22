package net.dev.art.rc.clients;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.bukkit.Bukkit;

import net.dev.art.rc.enums.Commands;
import net.dev.art.rc.enums.Permissions;
import net.dev.art.rc.enums.Users;
import net.dev.art.rc.utils.Crypter;
import net.dev.art.rc.utils.Utils;

public class Client {

	private Socket client;
	private boolean isAuth = false;
	private Users user;

	public Client(Socket client) {
		this.client = client;

		Thread readData = new Thread(new ReadData(this));
		readData.start();
	}

	public void readData(String data) {
		if (isAuth) {
			for (Commands cmd : Commands.values()) {
				if (data.startsWith(cmd.getCommand())) {
					Permissions perm = cmd.getPermissions();
					if (user.hasPermission(perm)) {
						Bukkit.getConsoleSender().sendMessage(
								"§b[" + user.getUsername() + "]§e Usou o comando §8» §e	[" + cmd.getCommand() + "]");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), data);
						return;
					}else {
						sendData("PERM!");
					}
				}
			}
			sendData("INVALID_CMD!");
		} else {
			Bukkit.getConsoleSender().sendMessage("§eAutenticando Usuario com senha: §b" + Crypter.decode(data));
//			auth(data);
		}
	}

	public void auth(String data) {
		try {

			for (Client clients : Utils.connectedClients) {
				if (clients.getClient().getInetAddress().equals(client.getInetAddress())) {
					Bukkit.getConsoleSender()
							.sendMessage("§c[" + client.getInetAddress().getHostName() + "] Já está conectado!");
					sendData("ALREADY!");
					client.close();
					return;
				}
			}

			String decondedKey = data;
			// #authNOMEDOUSUARIO;SENHADOUSUARIO
			if (decondedKey.startsWith("#auth")) {
				String[] splitter = decondedKey.replace("#auth", "").split(";");
				String name = splitter[0];
				String pass = splitter[1];

				for (Users user : Users.values()) {
					if (user.getUsername().equals(name) && user.getPassword().equals(pass)) {
						Bukkit.getConsoleSender().sendMessage("§b[" + client.getInetAddress().getHostName()
								+ "]§e Conectado com sucesso! §8» §e" + user.getUsername());
						setUser(user);
						Utils.connectedClients.add(this);
						setAuth(true);
						sendData("OK!");
						sendData("#Login" + user.getUsername() + ";" + user.getPermission().toString());
					}
				}

				Utils.connectedClients.remove(this);
				setAuth(false);
				sendData("FAIL!");
				Bukkit.getConsoleSender().sendMessage("§f[" + client.getInetAddress().getHostName()
						+ "]§c Falaha ao tentar concetar-se! §8» §fFecahda");
				client.close();

			}
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cErro ID:§f #4");
		}
	}

	public void sendData(String data) {
		data = Crypter.encode(data);

		try {
			OutputStream out = client.getOutputStream();
			PrintWriter writer = new PrintWriter(out);

			writer.write(data + "\n");
			writer.flush();
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage(
					"§cErro na classe Client no metodo sendData() ao §fEncodar a Data§c reporte usando ID:§f#2");
		}
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Users getUser() {
		return user;
	}

	public Socket getClient() {
		return client;
	}

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}

}
