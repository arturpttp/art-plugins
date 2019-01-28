package net.dev.art.bot.listeners;

import net.dev.art.bot.infos.CONFIG;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Chat extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if(e.getAuthor().isFake() || e.getAuthor().isBot()) return;
        if(args[0].equalsIgnoreCase(CONFIG.prefix+"online")){
            int on=0;
            for (Member m : e.getGuild().getMembers()) {
                if(m.getOnlineStatus()==OnlineStatus.ONLINE){
                    on++;
                }
            }
            e.getChannel().sendMessage(on + " Membros online").queue();
        }
    }
}