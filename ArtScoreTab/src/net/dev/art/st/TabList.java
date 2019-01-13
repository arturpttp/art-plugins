package net.dev.art.st;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.dev.art.core.utils.Mine;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public class TabList {

	String header, footer;

	public TabList(String header, String footer) {
		this.footer = footer;
		this.header = header;
	}

	public String getHeader() {
		return header;
	}

	public String getFooter() {
		return footer;
	}

	public void sendTab() {
		String header = getHeader();
		String footer = getFooter();
		try {
			PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(
					IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + header + "\"}"));
			Field field = packet.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(packet, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footer + "\"}"));
			for (Player p : Bukkit.getOnlinePlayers()) {
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void send() {
		ConsoleCommandSender send = Bukkit.getConsoleSender();
		new BukkitRunnable() {
			@Override
			public void run() {
				PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
				int on = Bukkit.getOnlinePlayers().size();

				Object header = new ChatComponentText(getHeader());

				Object footer = new ChatComponentText(getFooter());
				try {
					Field a = packet.getClass().getDeclaredField("a");
					a.setAccessible(true);

					Field b = packet.getClass().getDeclaredField("b");
					b.setAccessible(true);

					a.set(packet, header);
					b.set(packet, footer);
					;

					if (Bukkit.getOnlinePlayers().size() == 0)
						return;
					for (Player p : Bukkit.getOnlinePlayers()) {
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
					}

				} catch (NoSuchFieldException | IllegalAccessException e) {

					send.sendMessage("§cErro No TAB Arruma Isso Menino");

				}

			}
		}.runTaskTimer(Main.getInstance(), 0, 20);

	}

}
