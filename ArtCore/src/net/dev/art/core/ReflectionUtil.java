package net.dev.art.core;

import org.bukkit.*;
import java.lang.reflect.*;
import org.bukkit.entity.*;

public class ReflectionUtil
{
    private static String versionRaw;
    private static int versionMajor;
    private static int versionMinor;
    private static int versionRelease;
    
    static {
        ReflectionUtil.versionRaw = Bukkit.getServer().getClass().getPackage().getName().substring(23);
        ReflectionUtil.versionMajor = Integer.valueOf(getVersionRawPart(0).substring(1));
        ReflectionUtil.versionMinor = Integer.valueOf(getVersionRawPart(1));
        ReflectionUtil.versionRelease = Integer.valueOf(getVersionRawPart(2).substring(1));
    }
    
    public static Class<?> getNMSClass(final String nmsClassName) {
        try {
            return Class.forName("net.minecraft.server." + ReflectionUtil.versionRaw + "." + nmsClassName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Class<?> getOBCClass(final String obcClassName) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + ReflectionUtil.versionRaw + "." + obcClassName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Method getMethod(final Class<?> clazz, final String methodName, final Class<?>... params) {
        try {
            return clazz.getMethod(methodName, params);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void sendPacket(final Player player, final Object packet) {
        try {
            final Object handle = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(player, new Object[0]);
            final Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getVersionRaw() {
        return ReflectionUtil.versionRaw;
    }
    
    public static String getVersionRawPart(final int index) {
        final String versionRaw = getVersionRaw();
        final String[] parts = versionRaw.split("_");
        return parts[index];
    }
    
    public static int getVersionMajor() {
        return ReflectionUtil.versionMajor;
    }
    
    public static int getVersionMinor() {
        return ReflectionUtil.versionMinor;
    }
    
    public static int getVersionRelease() {
        return ReflectionUtil.versionRelease;
    }
}

