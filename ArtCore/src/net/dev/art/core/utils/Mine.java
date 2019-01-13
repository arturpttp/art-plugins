package net.dev.art.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Mine {

	private static Map<String, String> replacers = new LinkedHashMap<>();

	static {
		replacers.put("#b", "org.bukkit.");
		replacers.put("#s", "org.spigotmc.");
		replacers.put("#a", "net.eduard.api.");
		replacers.put("#e", "net.eduard.eduardapi.");
		replacers.put("#k", "net.eduard.api.kits.");
		replacers.put("#p", "#mPacket");
		replacers.put("#m", "net.minecraft.server.#v.");
		replacers.put("#c", "org.bukkit.craftbukkit.#v.");
		replacers.put("#s", "org.bukkit.");
	}

	public static void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}

	public static Object getValue(Object object, String name) throws Exception {
		return getField(object, name).get(object);
	}

	public static Class<?>[] getParameters(Object... parameters) throws Exception {
		Class<?>[] objects = new Class<?>[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			objects[i] = getClassFrom(parameters[i]);
		}
		return objects;

	}

	public static Method getMethod(Object object, String name, Object... parameters) throws Exception {
		Class<?> claz = getClassFrom(object);
		try {
			Method method = claz.getDeclaredMethod(name, getParameters(parameters));
			method.setAccessible(true);
			return method;
		} catch (Exception e) {
			Method method = claz.getMethod(name, getParameters(parameters));
			method.setAccessible(true);
			return method;
		}

	}

	public static boolean OPT_AUTO_RESPAWN = true;

	public static boolean startWith(String message, String text) {
		return message.toLowerCase().startsWith(text.toLowerCase());
	}

	public static Object getResult(Object object, String name, Object... values) throws Exception {

		return getMethod(object, name, values).invoke(object, values);
	}

	public static Object getResult(Object object, String name, Object[] parameters, Object... values) throws Exception {
		try {
			return getMethod(object, name, parameters).invoke(object, values);
		} catch (InvocationTargetException e) {
			return null;
		}

	}

	public static String classBukkitBukkit = "#bBukkit";

	public static List<Player> getPlayers() {
		List<Player> list = new ArrayList<>();
		try {

			Object object = getResult(Mine.classBukkitBukkit, "getOnlinePlayers");
			if (object instanceof Collection) {
				Collection<?> players = (Collection<?>) object;
				for (Object obj : players) {
					if (obj instanceof Player) {
						Player p = (Player) obj;
						list.add(p);
					}
				}
			} else if (object instanceof Player[]) {
				Player[] players = (Player[]) object;
				for (Player p : players) {
					list.add(p);
				}
			}
		} catch (Exception e) {
		}

		return list;
	}

	public static void broadcast(String message) {
		Bukkit.broadcastMessage(message);
	}

	public static void broadcast(String message, String permission) {
		for (Player player : Mine.getPlayers()) {
			if (player.hasPermission(permission))
				player.sendMessage(message);
		}
	}

	public static boolean createCommand(Plugin plugin, Command... cmds) {
		try {
			Class<?> serverClass = getClassFrom(Bukkit.getServer());
			Field field = serverClass.getDeclaredField("commandMap");
			field.setAccessible(true);
			CommandMap map = (CommandMap) field.get(Bukkit.getServer());
			for (Command cmd : cmds) {
				map.register(plugin.getName(), cmd);
			}
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return OPT_AUTO_RESPAWN;
	}

	public static Class<?> getClassFrom(Object object) throws Exception {
		if (object instanceof Class) {
			return (Class<?>) object;
		}
		if (object instanceof String) {
			String string = (String) object;
			if (string.startsWith("#")) {
				for (Entry<String, String> entry : replacers.entrySet()) {
					string = string.replace(entry.getKey(), entry.getValue());
				}
				return Class.forName(string);
			}
		}
		try {
			return (Class<?>) object.getClass().getField("TYPE").get(0);
		} catch (Exception e) {
		}
		return object.getClass();
	}

	public static Field getField(Object object, String name) throws Exception {
		Class<?> claz = getClassFrom(object);
		try {
			Field field = claz.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (Exception e) {
			Field field = claz.getField(name);
			field.setAccessible(true);
			return field;
		}

	}

	@SuppressWarnings("unchecked")
	public static Map<String, Command> getCommands() {
		try {
			Object map = getValue(Bukkit.getServer().getPluginManager(), "commandMap");

			return (Map<String, Command>) getValue(map, "knownCommands");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void removeCommand(String name) {
		if (getCommands().containsKey(name)) {
			PluginCommand cmd = Bukkit.getPluginCommand(name);
			String pluginName = cmd.getPlugin().getName();
			String cmdName = cmd.getName();
			for (String aliase : cmd.getAliases()) {
				removeAliaseFromCommand(cmd, aliase);
				removeAliaseFromCommand(cmd, pluginName.toLowerCase() + ":" + aliase);
			}
			try {
				getCommands().remove(cmd.getName());
			} catch (Exception e) {
			}
			console("§bCommandAPI §fremovendo o comando §a" + cmdName + "§f do Plugin §b" + pluginName);
		} else {
			console("§bCommandAPI §fnao foi encontrado a commando §a" + name);
		}

	}

	public static void removeAliaseFromCommand(PluginCommand cmd, String aliase) {
		String cmdName = cmd.getName().toLowerCase();
		if (getCommands().containsKey(aliase)) {
			getCommands().remove(aliase);
			console("§bCommandAPI §fremovendo aliase §a" + aliase + "§f do comando §b" + cmdName);
		} else {
			console("§bCommandAPI §fnao foi encontrado a aliase §a" + aliase + "§f do comando §b" + cmdName);
		}
	}

}
