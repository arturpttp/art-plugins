package net.dev.art.aut2.objetos;

import net.dev.art.aut2.Main;

import java.util.List;
import java.util.stream.Collectors;

public class Conta {

    private String player,senha,ip;

    public Conta(String player,String senha,String ip) {
        setIp(ip);
        setPlayer(player);
        setSenha(senha);
    }

    public String getIp() {
        return ip;
    }

    public String getPlayer() {
        return player;
    }

    public String getSenha() {
        return senha;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void logar(){
        Main.getLogados().add(player);
    }

    public void deslogar(){
        Main.getLogados().remove(player);
    }

    public static List<Conta> getAll(){
        return Main.getInstance().contas.values().stream().collect(Collectors.toList());
    }

    public boolean isLogado() {
        return Main.getLogados().contains(player);
    }

    public boolean isRegistrado() {
        return Main.getInstance().config.contains("Contas."+player.toLowerCase());
    }

    public void registrar() {
        Main.getInstance().config.set("Contas."+player.toLowerCase()+".Senha",senha);
        Main.getInstance().config.set("Contas."+player.toLowerCase()+".IP",ip);
        this.logar();
    }

    public void save() {
        Main.getInstance().config.set("Contas."+player.toLowerCase()+".Senha",senha);
        Main.getInstance().config.set("Contas."+player.toLowerCase()+".IP",ip);
    }


}
