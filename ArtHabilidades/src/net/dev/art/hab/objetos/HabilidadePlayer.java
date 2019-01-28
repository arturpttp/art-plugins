package net.dev.art.hab.objetos;

public class HabilidadePlayer {

	private String name;
	private HabilidadeType hability;
	private int level;

	public HabilidadePlayer(String name, HabilidadeType hability, int level) {
		this.name = name;
		this.hability = hability;
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public HabilidadeType getHability() {
		return hability;
	}

}
