package net.dev.rpg.Enums;

public enum Cargos {

	NENHUM("NENHUM"), MEMBRO("MEMBRO"), CAPITAO("CAPITAO"), LIDER("LIDER");

	private String nome;

	Cargos(String nome) {
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
