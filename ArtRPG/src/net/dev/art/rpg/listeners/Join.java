package net.dev.art.rpg.listeners;

import net.dev.art.rpg.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public class Join implements Listener {

    @EventHandler
    void event(PlayerJoinEvent e){
        Player p = e.getPlayer();
//        if (!Main.getInstance().racas.contains(p.getName())) {
//            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new BukkitRunnable() {
//
//                @Override
//                public void run() {
//                    Inventory inv = Bukkit.createInventory(null, 9, "§6Classes");
//                    p.openInventory(inv);
//                }
//            }, 0L);
//        }
    }

}
