package net.dev.art.facs.enums;

public enum Cargo {

	Nenhum("", "Nenhum"), Recruta("-", "Recruta"), Membro("+", "Membro"), Capitão("*", "Capitão"), Lider("#", "Lider");

	private String simbolo, nome;

	private Cargo(String simbolo, String nome) {
		this.simbolo = simbolo;
		this.nome = nome;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	
	public String getNome() {
		return nome;
	}

}
