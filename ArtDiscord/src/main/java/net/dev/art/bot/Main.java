package net.dev.art.bot;

import net.dev.art.bot.listeners.Chat;
import net.dev.art.bot.listeners.Discord;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    private static String token = "NTE3MTE1MDg3MTgyNDk1NzQ1.Dt9mTQ.fT4XQjdWwNrt-k_8wfGT4l99lKQ";
    private static JDA bot;
    public static void main(String[] args) {
        startBOT();
    }

    static void startBOT(){
        JDABuilder jda = new JDABuilder(AccountType.BOT).setToken(token).setAutoReconnect(true);
        try {
            bot = jda.buildBlocking();
            bot.addEventListener(new Chat());
            bot.addEventListener(new Discord());
            System.out.println("Bot iniciado com sucesso");
        } catch (LoginException e) {
            System.out.println("Bot ERROR: LoginException");
        } catch (InterruptedException e) {
            System.out.println("Bot ERROR: InterruptedException");
        }
    }

}
