package net.dev.art.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class API {
	
	public static void callEvent(Event event) {
		Bukkit.getPluginManager().callEvent(event);
	}

	public static ItemStack toGlass(final Boolean rainbow, final int type) {
		final int randomNum = 1 + (int) (Math.random() * 6.0);
		ItemStack glass;
		if (rainbow) {
			glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) randomNum);
		} else {
			glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) type);
		}
		final ItemMeta glassMeta = glass.getItemMeta();
		glassMeta.setDisplayName("§lGlass");
		glass.setItemMeta(glassMeta);
		return glass;
	}

	public static String progressPorcentagem(int valor1, int valor2) {
	      Integer total = (valor1 * 100) / valor2;
	      return total + "%";
	    }

	
	
	private static void changeName(Player player, String name) {
		try {
			Method getHandle = player.getClass().getMethod("getHandle", (Class<?>[]) null);
			try {
				Class.forName("com.mojang.authlib.GameProfile");
			} catch (ClassNotFoundException e) {
				return;
			}
			Object profile = getHandle.invoke(player).getClass().getMethod("getProfile")
					.invoke(getHandle.invoke(player));
			Field ff = profile.getClass().getDeclaredField("name");
			ff.setAccessible(true);
			ff.set(profile, name);
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.hidePlayer(player);
				players.showPlayer(player);
			}
		} catch (Exception e) {
		}
	}

	public static String ProgressBar(int tamanho, double valor1, double valor2, String caractere, String concluindocor,
			String faltandocor) {

		int amount = tamanho;
		double perChar = valor1 / amount;
		int perTotal = (int) (valor2 / perChar);

		StringBuilder progressBar = new StringBuilder();

		for (int i = 0; i < perTotal; i++)
			progressBar.append(concluindocor + caractere);

		if (perTotal < amount)
			for (int i = perTotal; i < amount; i++)
				progressBar.append(faltandocor + caractere);
		return progressBar.toString();
	}

}
