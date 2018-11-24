package net.dev.art.core.objects;

import java.lang.reflect.*;
import org.bukkit.entity.*;

import net.dev.art.core.utils.ReflectionUtil;

import org.bukkit.*;
import java.util.*;

public class ActionBar {
	private Object packet;

	public ActionBar(final String text) {
		try {
			final Constructor<?> constructor = ReflectionUtil.getNMSClass("PacketPlayOutChat")
					.getConstructor(ReflectionUtil.getNMSClass("IChatBaseComponent"), Byte.TYPE);
			final Object icbc = ReflectionUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", String.class).invoke(null, "{\"text\":\"" + text + "\"}");
			final Object packet = constructor.newInstance(icbc, (byte) 2);
			this.packet = packet;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendToPlayer(final Player p) {
		ReflectionUtil.sendPacket(p, this.packet);
	}

	public void sendToAll() {
		Bukkit.getOnlinePlayers().forEach(Player -> ReflectionUtil.sendPacket(Player, this.packet));
	}

	public void sendToAllInAList(final List<String> playersName) {
		playersName.forEach(playerName -> {
			Player player = Bukkit.getPlayer(playerName);
			if (player != null) {
				ReflectionUtil.sendPacket(player, this.packet);
			}
		});
	}

	public void sendToAllWithPerm(final String permission) {
		Bukkit.getOnlinePlayers().stream().filter(Player -> Player.hasPermission(permission))
				.forEach(Player -> ReflectionUtil.sendPacket(Player, this.packet));
	}
}
