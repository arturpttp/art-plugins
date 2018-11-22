package net.dev.art.essentials;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import net.dev.art.essentials.utils.Mensagens;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class BuildEvents extends Mensagens implements Listener {

	@EventHandler
	void onClickInv(InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
			return;
		if (e.getInventory().getTitle().equalsIgnoreCase("§bPlayers online")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!Main.builds.contains(p)) {
			e.setCancelled(true);
			mensagem(p, "§cVocê está com o modo build desativado");
		}
	}

	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (!Main.builds.contains(p)) {
			e.setCancelled(true);
			mensagem(p, "§cVocê está com o modo build desativado");
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.teleport(Main.spawn.getLocation("Spawn"));
	}

	@EventHandler
	public void onRespawn(PlayerDeathEvent ev) {
		final Player plr = ev.getEntity();
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				PacketPlayInClientCommand packet = new PacketPlayInClientCommand();
				try {
					Field a = PacketPlayInClientCommand.class.getDeclaredField("a");
					a.setAccessible(true);
					a.set(packet, EnumClientCommand.PERFORM_RESPAWN);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				((CraftPlayer) plr).getHandle().playerConnection.a(packet);
				plr.teleport(Main.spawn.getLocation("Spawn"));
			}
		}, 2L);

	}

	@EventHandler
	void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String warp = e.getMessage().replace("/", "").toLowerCase();
		if (Main.warpsHash.containsKey(warp)) {
			e.setMessage("/warp " + warp);
		}
	}

}
