package net.dev.art.aut2.eventos;

import net.dev.art.aut2.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;

public class bloqueios implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!Main.getInstance().weiting.contains(p.getName())) {
            Main.getInstance().weiting.add(p.getName());
        }
        int tempoLogin = 15;
        int tempoRegistrar = 20;
        int interaveloMensagens = 3;
        String mensagemKick = "�cVoc� demorou muito para se autenticar.";
        String mensagemChatLogar = "�eUse /login <senha> para se logar.";
        String mensagemChatRegistrar = "�eUse /register <senha> para se registrar.";
        String tituloLogin = "�6�lBACON�f�lMC";
        String subtituloLogin = "�fUtilize /register <senha>.";
        String tituloRegister = "�6�lBACON�f�lMC";
        String subtituloRegister = "�fUtilize /login <senha>.";

        if (Main.getInstance().weiting.contains(p.getName())) {
            if (Main.getInstance().contas.containsKey(p.getName())) {
                // registrar
                p.sendTitle(tituloRegister,subtituloRegister);

                new BukkitRunnable() {

                        @Override
                        public void run() {
                            if (Main.getInstance().weiting.contains(p.getName())) {
                                p.sendMessage(mensagemChatLogar);
                            }
                        }
                    }.runTaskTimer(Main.getInstance(), 0L, interaveloMensagens * 20);
                new BukkitRunnable() {

                        @Override
                    public void run() {
                        if (Main.getInstance().weiting.contains(p.getName())) {
                            Main.getInstance().weiting.remove(p.getName());
                            p.kickPlayer(mensagemKick);

                        }
                    }
                }.runTaskLater(Main.getInstance(), tempoRegistrar * 20);

            } else {
                // login
                p.sendTitle(tituloLogin,subtituloLogin);
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (Main.getInstance().weiting.contains(p.getName())) {
                            p.sendMessage(mensagemChatRegistrar);
                        }
                    }
                }.runTaskTimer(Main.getInstance(), 0L, interaveloMensagens * 20);
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if (Main.getInstance().weiting.contains(p.getName())) {
                            Main.getInstance().weiting.remove(p.getName());
                            p.kickPlayer(mensagemKick);
                        }
                    }
                }.runTaskLater(Main.getInstance(), tempoLogin * 20);

            }
        }
    }

    @EventHandler
    public void sair(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (Main.getInstance().weiting.contains(p.getName())) {
            Main.getInstance().weiting.remove(p.getName());
        }
    }

    @EventHandler
    public void teleport(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (Main.getInstance().weiting.contains(p.getName())) {
            p.teleport(p);
        }
    }

    @EventHandler
    public void block(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (Main.getInstance().weiting.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void block2(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (Main.getInstance().weiting.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void dropitem(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (Main.getInstance().weiting.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void comando(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (Main.getInstance().weiting.contains(p.getName())) {
            if (e.getMessage().toLowerCase().startsWith("/login")
                    || e.getMessage().toLowerCase().startsWith("/register")) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void inv(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (Main.getInstance().weiting.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void interact(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (Main.getInstance().weiting.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (Main.getInstance().weiting.contains(p.getName())) {
            if (e.isCancelled()) {
                e.setCancelled(true);
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    void bloqueio(PlayerMoveEvent e){
        e.getPlayer().teleport(e.getFrom());
    }

    @EventHandler
    void bloqueio(PlayerCommandPreprocessEvent e){
        String msg=e.getMessage();
    }

}
