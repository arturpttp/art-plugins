package net.dev.art.core;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class API {

    public static ItemStack toGlass(final Boolean rainbow, final int type) {
        final int randomNum = 1 + (int)(Math.random() * 6.0);
        ItemStack glass;
        if (rainbow) {
            glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)randomNum);
        }
        else {
            glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)type);
        }
        final ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName("§lGlass");
        glass.setItemMeta(glassMeta);
        return glass;
    }
	
}
