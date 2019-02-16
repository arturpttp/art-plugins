package net.dev.art.kits.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Kit {

	private String name, grupo;
	private List<ItemStack> itens = new ArrayList<>();
	private long delay;

	public Kit(String name, String grupo, List<ItemStack> itens, long delay) {
		this.name = name;
		this.grupo = grupo;
		this.itens = itens;
		this.delay = delay;
	}

	public String getName() {
		return name;
	}

	public String getGrupo() {
		return grupo;
	}

	public List<ItemStack> getItens() {
		return itens;
	}

	public long getDelay() {
		return delay;
	}

}
