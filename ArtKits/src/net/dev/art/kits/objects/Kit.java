package net.dev.art.kits.objects;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Kit {

	String name;
	int delay;
	List<ItemStack> itens;
	String grupo;
	
	public Kit(String name, int delay, List<ItemStack> itens, String grupo) {
		this.name = name;
		this.delay = delay;
		this.itens = itens;
		this.grupo = grupo;
	}

	public String getGrupo() {
		return grupo;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public List<ItemStack> getItens() {
		return itens;
	}
	
	public String getName() {
		return name;
	}
	
}
