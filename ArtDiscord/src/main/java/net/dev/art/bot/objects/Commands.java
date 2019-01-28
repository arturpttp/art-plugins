package net.dev.art.bot.objects;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public abstract class Commands extends ListenerAdapter {

    private String command;

    public Commands(String command){
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public abstract boolean exec(String[] args, TextChannel channel, User author, Member member);

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()||event.getAuthor().isFake()) return;
        TextChannel channel = event.getChannel();
        User author = event.getAuthor();
        Member member = event.getMember();
        String [] args = event.getMessage().getContentRaw().split(" ");
        exec(args,channel,author,member);
    }
}
