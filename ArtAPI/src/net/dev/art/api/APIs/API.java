package net.dev.art.api.APIs;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class API {

	public static int getReamingSlot(Player p) {
		PlayerInventory inv = p.getInventory();
		int slot = 0;
		if (inv.firstEmpty() == -1) {
			slot = 0;
			return slot;
		} else {
			for (ItemStack is : inv.getContents()) {
				if (is == null || is.getType() == Material.AIR) {
					slot++;
				}
			}
		}
		return slot;
	}

	public static void CommandRegister(JavaPlugin instance, String cmd) {
		Command command = instance.getCommand(cmd);
		((CraftServer) instance.getServer()).getCommandMap().register(cmd, command);

	}

}
