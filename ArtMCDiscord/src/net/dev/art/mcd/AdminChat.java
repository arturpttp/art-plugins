package net.dev.art.mcd;

import org.bukkit.Bukkit;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class AdminChat extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if (e.getAuthor().isBot() || e.getAuthor().isFake())
			return;
		if (e.getChannel().getName().equalsIgnoreCase("admin-mc-chat")) {
			Bukkit.broadcastMessage("§9[MCDiscord] §a" + e.getMember().getRoles().get(0).getName() + " "
					+ e.getAuthor().getName() + " §8» §f" + e.getMessage().getContentRaw());
		}
	}

}
