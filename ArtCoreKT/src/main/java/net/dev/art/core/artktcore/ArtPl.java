package net.dev.art.core.artktcore;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class ArtPl extends JavaPlugin {

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public abstract void aoHabilitar();
    public abstract void aoCarregar();
    public abstract void aoDesablilitar();

}
