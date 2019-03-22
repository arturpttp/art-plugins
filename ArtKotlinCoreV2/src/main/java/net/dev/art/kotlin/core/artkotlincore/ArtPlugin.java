package net.dev.art.kotlin.core.artkotlincore;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class ArtPlugin extends JavaPlugin {

    private static ArtPlugin artPlugin;

    public static ArtPlugin getArtPlugin() {
        return artPlugin;
    }

    @Override
    public void onLoad() {
        artPlugin=this;
        load();
    }

    @Override
    public void onEnable() {
enable();
    }

    @Override
    public void onDisable() {
disable();
   }

    public abstract void load();
    public abstract void enable();
    public abstract void disable();



}