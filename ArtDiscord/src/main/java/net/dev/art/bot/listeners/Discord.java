package net.dev.art.bot.listeners;

import net.dev.art.bot.objects.Commands;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class Discord extends Commands {

    public Discord() {
        super("discord");
    }

    public boolean exec(String[] args, TextChannel channel, User author, Member member) {
        if (args[0].equalsIgnoreCase("$discord")){
            channel.sendMessage("Discord!");
        }
        return false;
    }
}
