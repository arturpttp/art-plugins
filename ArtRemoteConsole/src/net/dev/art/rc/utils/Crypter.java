package net.dev.art.rc.utils;

import java.util.Base64;

import org.bukkit.Bukkit;

public class Crypter {

	public static String encode(String value) {
		try {
			return Base64.getEncoder().encodeToString(value.getBytes());
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cErro ao tentar encriptar:§f " + value);
			return null;
		}
	}

	public static String decode(String value) {
		try {
			byte[] decoded = Base64.getDecoder().decode(value.getBytes());
			return new String(decoded);
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cErro ao tentar codificar:§f " + value);
			return null;
		}
	}

}
