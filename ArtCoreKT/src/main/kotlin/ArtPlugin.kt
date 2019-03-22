package net.dev.art.core.artktcore

import org.bukkit.plugin.java.JavaPlugin

abstract class ArtPlugin : JavaPlugin() {

    override fun onLoad() {
        super.onLoad()
    }

    override fun onEnable() {
    }

    override fun onDisable() {
        super.onDisable()
    }

    abstract fun aoHabilitar()
    abstract fun aoCarregar()
    abstract fun aoDesablilitar()

}
