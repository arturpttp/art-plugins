package net.dev.rpg.Entidades;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class CriarEntidade {

	 private EntityType tipo;
	    private String nome;
	    private Entity en;
	    private Location loc;

	    
	    public CriarEntidade(EntityType tipo, String nome, Location loc) {
	        en = loc.getWorld().spawnEntity(loc, tipo);
	        en.setCustomName(nome);
	        
	    }

	    public EntityType getTipo() {
	        return tipo;
	    }

	    public void setTipo(EntityType tipo) {
	        this.tipo = tipo;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public Entity getEn() {
	        return en;
	    }

	    public void setEn(Entity en) {
	        this.en = en;
	    }

	    public Location getLoc() {
	        return loc;
	    }

	    public void setLoc(Location loc) {
	        this.loc = loc;
	    }
	
}
