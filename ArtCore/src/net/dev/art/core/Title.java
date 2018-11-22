package net.dev.art.core;

import java.lang.reflect.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class Title {
	private Object titleSender;
	private Object title;
	private Object subtitle;

	public Title(final String title, final String subtitle) {
		this(15, 70, 20, title, subtitle);
	}

	public Title(final Integer fadeIn, final Integer stay, final Integer fadeOut, final String title,
			final String subtitle) {
		try {
			final Object fieldTimes = ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0]
					.getField("TIMES").get(null);
			final Object chatSenderTitle = ReflectionUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
			final Constructor<?> titleSender = ReflectionUtil.getNMSClass("PacketPlayOutTitle").getConstructor(
					ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
					ReflectionUtil.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
			this.titleSender = titleSender.newInstance(fieldTimes, chatSenderTitle, fadeIn, stay, fadeOut);
			final Object fieldTitle = ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0]
					.getField("TITLE").get(null);
			final Object chatTitle = ReflectionUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
			final Constructor<?> titleConstructor = ReflectionUtil.getNMSClass("PacketPlayOutTitle").getConstructor(
					ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
					ReflectionUtil.getNMSClass("IChatBaseComponent"));
			this.title = titleConstructor.newInstance(fieldTitle, chatTitle);
			final Object fieldSubTitle = ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0]
					.getField("SUBTITLE").get(null);
			final Object chatSubtitle = ReflectionUtil.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
					.getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");
			final Constructor<?> subTitleConstructor = ReflectionUtil.getNMSClass("PacketPlayOutTitle").getConstructor(
					ReflectionUtil.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
					ReflectionUtil.getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE);
			this.subtitle = subTitleConstructor.newInstance(fieldSubTitle, chatSubtitle, fadeIn, stay, fadeOut);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void sendToPlayer(final Player p) {
		ReflectionUtil.sendPacket(p, this.titleSender);
		ReflectionUtil.sendPacket(p, this.title);
		ReflectionUtil.sendPacket(p, this.subtitle);
	}

	public void sendToAll() {
		Bukkit.getOnlinePlayers().forEach(Player -> {
			ReflectionUtil.sendPacket(Player, this.titleSender);
			ReflectionUtil.sendPacket(Player, this.title);
			ReflectionUtil.sendPacket(Player, this.subtitle);
		});
	}

	public void sendToAllInAList(final List<String> playersName) {
		playersName.forEach(playerName -> {
			Player player = Bukkit.getPlayer(playerName);
			ReflectionUtil.sendPacket(player, this.titleSender);
			ReflectionUtil.sendPacket(player, this.title);
			ReflectionUtil.sendPacket(player, this.subtitle);
		});
	}

	public void sendToAllWithPerm(final String permission) {
		Bukkit.getOnlinePlayers().stream().filter(Player -> Player.hasPermission(permission)).forEach(Player -> {
			ReflectionUtil.sendPacket(Player, this.titleSender);
			ReflectionUtil.sendPacket(Player, this.title);
			ReflectionUtil.sendPacket(Player, this.subtitle);
		});
	}
}
